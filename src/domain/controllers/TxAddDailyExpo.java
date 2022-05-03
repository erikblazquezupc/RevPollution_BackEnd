package domain.controllers;

import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.ExpoDataCtrl;
import domain.dataCtrl.UserDataCtrl;
import domain.dataCtrl.ConcentrationDataCtrl;
import domain.dataCtrl.StationDataCtrl;

import java.util.ArrayList;

import domain.Concentration;
import domain.StationStub;
import domain.User;

public class TxAddDailyExpo {
    private String token;
    private Double lat;
    private Double lon;
    private boolean result;

    public TxAddDailyExpo(String token, Double lat, Double lon){
        this.token = token;
        this.lat = lat;
        this.lon = lon;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        ExpoDataCtrl edc = dataCtrl.getExpoDataCtrl();
        UserDataCtrl udc = dataCtrl.getUserDataCtrl();
        User u = udc.selectByToken(token);

        ConcentrationDataCtrl cdc = dataCtrl.getConcentrationDataCtrl();
	    StationDataCtrl sdc = dataCtrl.getStationDataCtrl();
        ArrayList<StationStub> stats = sdc.selectAll();
        int closerId = -1;
        double Smallerdistance, distance;
        Smallerdistance = distance = -1;
        double EarthRadius = 6371;
        double max = -1.0;

        for (int i = 0; i < stats.size(); ++i) {
            StationStub current = stats.get(i);
            double dLat = Math.toRadians(lat - current.getLat());
            double dLon = Math.toRadians(lon - current.getLon());
            double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + 
                Math.sin(dLon / 2) * Math.sin(dLon / 2) *
                Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(current.getLat()));
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            distance = c * EarthRadius;
            if (i == 0) {
                Smallerdistance = distance;
                closerId = current.getId();
            }
            else if (distance < Smallerdistance) {
                Smallerdistance = distance;
                closerId = current.getId();
            }
        }

        if (closerId != -1) {
            StationStub closer = sdc.select(closerId);
            Integer idStation = closer.getId();
            ArrayList<Concentration> concs = cdc.selectMostRecentFromStation(idStation);
            double IPSO2, IPNO2, IPPM10, IPCO, IPO3;
            IPSO2 = IPNO2 = IPPM10 = IPCO = IPO3 = -1.0;
            for (int i = 0; i < concs.size(); ++i) {
                Concentration curConcentration = concs.get(i);
                if (curConcentration.getParticle().getName().equals("SO2")) {
                    IPSO2 = 0.286 * curConcentration.getValue();
                }
                else if (curConcentration.getParticle().getName().equals("NO2")) {
                    IPNO2 = 0.5 * curConcentration.getValue();
                }
                else if (curConcentration.getParticle().getName().equals("PM10")) {
                    IPPM10 = 1.0 * curConcentration.getValue();
                }
                else if (curConcentration.getParticle().getName().equals("CO")) {
                    IPCO = 10 * curConcentration.getValue();
                }
                else if (curConcentration.getParticle().getName().equals("O3")) {
                    IPO3 = 0.556 * curConcentration.getValue();
                }
            }

            max = Math.max( Math.max( Math.max( Math.max(IPSO2, IPNO2), IPPM10 ), IPCO), IPO3);
            if (max == -1.0) max = 0;
        }
        result = edc.insert(u.getId(), max);
    }

    public boolean getResult(){
        return result;
    }
}

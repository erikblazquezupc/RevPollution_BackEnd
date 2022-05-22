package domain.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import domain.Quality;
import domain.SystemState;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.QualityDataCtrl;

public class TxGetPastQualities {
    private int idStation;
    private ArrayList<Quality> result;

    public TxGetPastQualities(int idStation){
        this.idStation = idStation; 
    }

    public void execute(){
        SystemState sy = SystemState.getInstance();
        if(!sy.existsStationPastQuality(idStation)){
            DataCtrl dataCtrl = DataCtrl.getInstance();
            QualityDataCtrl qdc = dataCtrl.getQualityDataCtrl();
            result = qdc.selectPast(idStation);
            sy.addStationPastQualities(idStation, result);
        } 
        else {

            Date now = new Date(); 
            Calendar cal = Calendar.getInstance();
            cal.setTime(now);

            ArrayList<Quality> quals = sy.getStationPastQualities(idStation);

            if (quals.size() > 3) {
                Date qDate = quals.get(2).getInstant();
                Calendar cal2 = Calendar.getInstance();
                cal2.setTime(qDate);

                cal.add(Calendar.DAY_OF_YEAR, -1);

                if ((cal.get(Calendar.DAY_OF_MONTH) != cal2.get(Calendar.DAY_OF_MONTH)) &&
                    (cal.get(Calendar.HOUR_OF_DAY) < 5)) {
                    DataCtrl dataCtrl = DataCtrl.getInstance();
                    QualityDataCtrl qdc = dataCtrl.getQualityDataCtrl();

                    sy.deleteStationPastQualities(idStation);
                    result = qdc.selectPast(idStation);
                    sy.addStationPastQualities(idStation, result);
                }
                else result = sy.getStationPastQualities(idStation);
            }
            else result = sy.getStationPastQualities(idStation);
        }
    }

    public ArrayList<Quality> getResult(){
        return result;
    }
}

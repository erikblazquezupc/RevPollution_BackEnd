package domain.controllers;

import domain.StationStub;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.StationDataCtrl;

public class TxCreateStation {
    private String name;
    private String address;
    Double lat;
    Double lon;
    private boolean result;

    public TxCreateStation(String name, String address, Double lat, Double lon){
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.lon = lon;
        this.result = false;
    }

    public void execute(){
        StationStub s = new StationStub(name, address, lat, lon);
        DataCtrl dataCtrl = DataCtrl.getInstance();
        StationDataCtrl sdc = dataCtrl.getStationDataCtrl();
        sdc.insert(s);
        result = true;
    }

    public boolean getResult(){
        return result;
    }
}

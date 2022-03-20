package domain.controllers;

import java.util.ArrayList;

import domain.StationStub;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.StationDataCtrl;

public class TxGetStations {
    private ArrayList<StationStub> result;

    public TxGetStations() {
        this.result = null;
    }

    public void execute() {
        DataCtrl dataCtrl = DataCtrl.getInstance();
        StationDataCtrl stationCtrl = dataCtrl.getStationDataCtrl();
        ArrayList<StationStub> stations = stationCtrl.selectAll();
        if(stations.isEmpty()) return;
        result = stations;
    }

    public ArrayList<StationStub> getResult(){
        return result;
    }
}

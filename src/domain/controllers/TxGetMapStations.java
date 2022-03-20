package domain.controllers;

import java.util.ArrayList;

import domain.StationStub;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.StationDataCtrl;

public class TxGetMapStations {
    private boolean result;
    private ArrayList<StationStub> stations;

    public TxGetMapStations(ArrayList<StationStub> stations) {
        this.stations = stations;
        this.result = false;
    }

    public void execute() {
        DataCtrl dataCtrl = DataCtrl.getInstance();
        StationDataCtrl stationCtrl = dataCtrl.getStationDataCtrl();
        stations = stationCtrl.selectAll();
        if(stations.isEmpty()) return;
        result = true;
    }

    public boolean getResult(){
        return result;
    }
}

package domain.controllers;

import java.util.ArrayList;

import domain.StationStub;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.StationDataCtrl;

public class TxGetStations {
    private ArrayList<StationStub> result;

    public TxGetStations(){}

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        StationDataCtrl sdc = dataCtrl.getStationDataCtrl();
        result = sdc.selectAll();;
    }

    public ArrayList<StationStub> getResult(){
        return result;
    }
}

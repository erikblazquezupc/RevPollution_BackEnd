package domain.controllers;

import java.util.List;

import domain.StationStub;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.StationDataCtrl;

public class TxGetStations {
    private List<StationStub> result;

    public TxGetStations(){}

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        StationDataCtrl sdc = dataCtrl.getStationDataCtrl();
        result = sdc.selectAll();;
    }

    public List<StationStub> getResult(){
        return result;
    }
}

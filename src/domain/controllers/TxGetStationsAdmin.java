package domain.controllers;

import java.util.ArrayList;

import domain.StationStub;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.StationDataCtrl;

public class TxGetStationsAdmin {
    private ArrayList<StationStub> result;

    public TxGetStationsAdmin(){}

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        StationDataCtrl sdc = dataCtrl.getStationDataCtrl();
        result = sdc.selectAllAdmin();;
    }

    public ArrayList<StationStub> getResult(){
        return result;
    }
}

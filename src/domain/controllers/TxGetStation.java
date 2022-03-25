package domain.controllers;

import java.util.ArrayList;

import domain.StationStub;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.StationDataCtrl;

public class TxGetStation {
    private int id;
    private StationStub result;

    public TxGetStation(int id){
        this.id = id;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        StationDataCtrl sdc = dataCtrl.getStationDataCtrl();
        result = sdc.select(id);
    }

    public StationStub getResult(){
        return result;
    }
}

package domain.controllers;

import domain.StationStub;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.StationDataCtrl;

public class TxGetStationAdmin {
    private int id;
    private StationStub result;

    public TxGetStationAdmin(int id){
        this.id = id;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        StationDataCtrl sdc = dataCtrl.getStationDataCtrl();
        result = sdc.selectAdmin(id);
    }

    public StationStub getResult(){
        return result;
    }
}

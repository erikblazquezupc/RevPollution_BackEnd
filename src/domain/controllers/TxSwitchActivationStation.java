package domain.controllers;

import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.StationDataCtrl;

public class TxSwitchActivationStation {
    private int id;

    public TxSwitchActivationStation(int id){
        this.id = id;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        StationDataCtrl sdc = dataCtrl.getStationDataCtrl();
        sdc.switchActivation(id);
    }
}

package domain.controllers;

import java.util.ArrayList;
import java.util.Date;

import domain.Quality;
import domain.SystemState;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.QualityDataCtrl;

public class TxGetFutureQualities {
    private int idStation; 
    private ArrayList<Quality> result;
 
    public TxGetFutureQualities(int idStation){
        this.idStation = idStation;
    }

    public void execute(){
        SystemState sy = SystemState.getInstance();
        Date lastChangeDate = sy.getFutureLastChangeDate(idStation);
        long now = (new Date()).toInstant().toEpochMilli();
        if(!sy.existsStationFutureQuality(idStation) || (now - lastChangeDate.toInstant().toEpochMilli()) >= 120000 ){
            DataCtrl dataCtrl = DataCtrl.getInstance();
            QualityDataCtrl qdc = dataCtrl.getQualityDataCtrl();
            result = qdc.selectFuture(idStation);
            sy.addStationFutureQualities(idStation, result);
        } else {
            result = sy.getStationFutureQualities(idStation);
        }
    }

    public ArrayList<Quality> getResult(){
        return result;
    }
}

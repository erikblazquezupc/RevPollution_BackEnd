package domain.controllers;

import java.util.ArrayList;
import java.util.Date;

import domain.Quality;
import domain.SystemState;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.QualityDataCtrl;

public class TxGetPastQualities {
    private int idStation;
    private ArrayList<Quality> result;

    public TxGetPastQualities(int idStation){
        this.idStation = idStation; 
    }

    public void execute(){
        SystemState sy = SystemState.getInstance();
        Date lastChangeDate = sy.getPastLastChangeDate(idStation);
        long now = (new Date()).toInstant().toEpochMilli();
        if(!sy.existsStationPastQuality(idStation) || (now - lastChangeDate.toInstant().toEpochMilli()) >= 120000 ){
            DataCtrl dataCtrl = DataCtrl.getInstance();
            QualityDataCtrl qdc = dataCtrl.getQualityDataCtrl();
            result = qdc.selectPast(idStation);
            sy.addStationPastQualities(idStation, result);
        } else {
            result = sy.getStationPastQualities(idStation);
        }
    }

    public ArrayList<Quality> getResult(){
        return result;
    }
}

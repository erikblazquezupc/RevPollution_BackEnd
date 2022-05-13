package domain.controllers;

import java.util.ArrayList;

import domain.Quality;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.QualityDataCtrl;

public class TxGetPastQualitiesByHours {
    private int idStation;
    private ArrayList<Quality> result;

    public TxGetPastQualitiesByHours(int idStation){
        this.idStation = idStation;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        QualityDataCtrl qdc = dataCtrl.getQualityDataCtrl();
        result = qdc.selectPastHours(idStation);
    }

    public ArrayList<Quality> getResult(){
        return result;
    }
}

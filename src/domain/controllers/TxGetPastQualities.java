package domain.controllers;

import java.util.ArrayList;

import domain.Quality;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.QualityDataCtrl;

public class TxGetPastQualities {
    private int idStation;
    private ArrayList<Quality> result;

    public TxGetPastQualities(int idStation){
        this.idStation = idStation;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        QualityDataCtrl qdc = dataCtrl.getQualityDataCtrl();
        result = qdc.selectPast(idStation);
    }

    public ArrayList<Quality> getResult(){
        return result;
    }
}

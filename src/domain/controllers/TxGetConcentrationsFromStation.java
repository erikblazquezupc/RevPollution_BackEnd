package domain.controllers;

import java.util.ArrayList;

import domain.Concentration;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.ConcentrationDataCtrl;

public class TxGetConcentrationsFromStation {
    private int idStation;
    private ArrayList<Concentration> result;

    public TxGetConcentrationsFromStation(int idStation){
        this.idStation = idStation;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        ConcentrationDataCtrl cdc = dataCtrl.getConcentrationDataCtrl();
        result = cdc.selectMostRecentFromStation(idStation);
    }

    public ArrayList<Concentration> getResult(){
        return result;
    }
}
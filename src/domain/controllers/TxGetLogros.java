package domain.controllers;

import java.util.ArrayList;

import domain.Logro;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.LogroDataCtrl;

public class TxGetLogros {
    private ArrayList<Logro> result;

    public TxGetLogros(){}

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        LogroDataCtrl ldc = dataCtrl.getLogroDataCtrl();
        result = ldc.selectAll();;
    }

    public ArrayList<Logro> getResult(){
        return result;
    }
}
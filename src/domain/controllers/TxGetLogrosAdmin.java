package domain.controllers;

import java.util.ArrayList;

import domain.Logro;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.LogroDataCtrl;

public class TxGetLogrosAdmin {
    private ArrayList<Logro> result;

    public TxGetLogrosAdmin(){}

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        LogroDataCtrl ldc = dataCtrl.getLogroDataCtrl();
        result = ldc.selectAllAdmin();;
    }

    public ArrayList<Logro> getResult(){
        return result;
    }
}

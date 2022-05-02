package domain.controllers;

//import java.util.ArrayList;

import domain.Logro;
import domain.Logro.Tier;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.LogroDataCtrl;

public class TxGetLogro {
    private String name;
    private Tier tier;
    private Logro result;

    public TxGetLogro(String name, Tier tier){
        this.name = name;
        this.tier = tier;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        LogroDataCtrl sdc = dataCtrl.getLogroDataCtrl();
        result = sdc.select(name, tier);
    }

    public Logro getResult(){
        return result;
    }
}

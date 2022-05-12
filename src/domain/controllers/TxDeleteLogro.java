package domain.controllers;

import domain.Logro;
import domain.Logro.Tier;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.LogroDataCtrl;

public class TxDeleteLogro {
    private String name;
    private Tier tier;
    private Logro l;

    public TxDeleteLogro(String name, Tier tier){
        this.name = name;
        this.tier = tier;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        LogroDataCtrl ldc = dataCtrl.getLogroDataCtrl();
        ldc.delete(name, tier);
    }
}

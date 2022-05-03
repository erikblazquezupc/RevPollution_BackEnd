package domain.controllers;

import domain.Logro;
import domain.Logro.Tier;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.LogroDataCtrl;

public class TxGetLogroAdmin {
    private String name;
    private Tier tier;
    private Logro result;

    public TxGetLogroAdmin(String name, Tier tier){
        this.name = name;
        this.tier = tier;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        LogroDataCtrl ldc = dataCtrl.getLogroDataCtrl();
        result = ldc.selectAdmin(name, tier);
    }

    public Logro getResult(){
        return result;
    }
}

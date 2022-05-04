package domain.controllers;

import domain.Logro;
import domain.Logro.Tier;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.LogroDataCtrl;

public class TxCreateLogro {
    String name;
    Tier tier;
    String cond;
    boolean activated;

    public TxCreateLogro(String name, Tier tier, String cond, boolean activated){
        this.name = name;
        this.tier = tier;
        this.cond = cond;
        this.activated = activated;
    }

    public void execute(){
        Logro l = new Logro(name, tier, cond, activated);
        DataCtrl dataCtrl = DataCtrl.getInstance();
        LogroDataCtrl ldc = dataCtrl.getLogroDataCtrl();
        ldc.insert(l);
    }

}

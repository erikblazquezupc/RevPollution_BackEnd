package domain.controllers;

import domain.Logro;
import domain.Logro.Tier;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.LogroDataCtrl;

public class TxEditLogro {
    private String name;
    private Tier tier;
    private String condition;
    private boolean activated;
    //private Logro l;

    public TxEditLogro(String name, Tier tier, String condition, boolean activated){
        this.name = name;
        this.tier = tier;
        this.condition = condition;
        this.activated = activated;
        //this.l = l;
    }

    public void execute(){
        //Logro l = getLogro(name, tier);
        DataCtrl dataCtrl = DataCtrl.getInstance();
        LogroDataCtrl ldc = dataCtrl.getLogroDataCtrl();
        //l = ldc.selectAdmin(name, tier);
        Logro l = new Logro(name, tier, condition, activated);
        ldc.update(l);
    }
}

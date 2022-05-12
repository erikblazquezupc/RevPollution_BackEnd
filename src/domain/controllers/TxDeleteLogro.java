package domain.controllers;

//import domain.Logro;
import domain.Logro.Tier;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.LogroDataCtrl;

public class TxDeleteLogro {
    String name;
    Tier tier;

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

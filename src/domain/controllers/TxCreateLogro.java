package domain.controllers;

import domain.EnumStatistics;
import domain.Logro;
import domain.Logro.Tier;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.LogroDataCtrl;

public class TxCreateLogro {
    String name;
    Tier tier;
    String cond;
    boolean activated;
    int limit;
    String statistic;

    public TxCreateLogro(String name, Tier tier, String cond, boolean activated, int limit, String statistic){
        this.name = name;
        this.tier = tier;
        this.cond = cond;
        this.activated = activated;
        this.statistic = statistic;
        this.limit = limit;
    }

    public void execute(){
        Logro l = new Logro(name, tier, cond, activated, limit, EnumStatistics.valueOf(statistic));
        DataCtrl dataCtrl = DataCtrl.getInstance();
        LogroDataCtrl ldc = dataCtrl.getLogroDataCtrl();
        ldc.insert(l);
    }

}

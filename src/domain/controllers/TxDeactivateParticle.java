package domain.controllers;

import domain.Particle;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.ParticleDataCtrl;

public class TxDeactivateParticle {
    private String name;
    private boolean result;

    public TxDeactivateParticle(String name){
        this.name = name;
        result = false;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        ParticleDataCtrl pdc = dataCtrl.getParticleDataCtrl();
        Particle p = pdc.select(name);
        if (p == null) return;
        p.deactivate();
        pdc.update(p);
        result = true;
    }

    public Boolean getResult(){
        return result;
    }
}

package domain.controllers;

import java.util.ArrayList;

import domain.Particle;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.ParticleDataCtrl;

public class TxGetParticles {
    private int id;
    private ArrayList<Particle> result;

    public TxGetParticles(){
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        ParticleDataCtrl pdc = dataCtrl.getParticleDataCtrl();
        result = pdc.selectAll();
    }

    public ArrayList<Particle> getResult(){
        return result;
    }
}

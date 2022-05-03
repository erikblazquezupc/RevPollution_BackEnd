package domain.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Particle;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.ParticleDataCtrl;

public class TxDeactivateParticleTest {

    Particle p;
    ParticleDataCtrl pdc = DataCtrl.getInstance().getParticleDataCtrl();

    @Before
    public void setUp(){
        p = new Particle("TestParticleDeact", "mg/m3", true);
        pdc.insert(p);
    }

    @After
    public void clean(){
        pdc.delete(p.getName());
    }

    @Test
    public void testExecute() {
        TxDeactivateParticle tx = new TxDeactivateParticle(p.getName());
        tx.execute();
        p.deactivate();
        assertEquals(p, pdc.select(p.getName()));
        assertTrue(tx.getResult());
    }
}

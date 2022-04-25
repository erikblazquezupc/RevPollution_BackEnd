package domain.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import domain.StationStub;
import domain.Particle;
import domain.Concentration;

import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.StationDataCtrl;
import domain.dataCtrl.ParticleDataCtrl;
import domain.dataCtrl.ConcentrationDataCtrl;

public class TxElektroGoTest {
    StationStub s;
    StationDataCtrl sdc = DataCtrl.getInstance().getStationDataCtrl();
    Particle p;
    ParticleDataCtrl pdc = DataCtrl.getInstance().getParticleDataCtrl();
    Concentration c;
    ConcentrationDataCtrl cdc = DataCtrl.getInstance().getConcentrationDataCtrl();

    @Before
    public void setUp(){
        s = new StationStub("Stub1", "dir", 5.0, 5.0);
        sdc.insert(s);
        p = pdc.select("NO2");
        assertNotNull(p);
        Date d = new Date(20000);
        c = new Concentration(s, p, d, 180.0);
        cdc.insert(c);
    }
    
    @After
    public void clean(){
        cdc.delete(s.getId(), "NO2", new Date(20000));
        sdc.delete(s.getId());
    }

    @Test
    public void testTxElektroGo() {

        TxElektroGo tx = new TxElektroGo(5.0, 5.1);
        tx.execute();
        assertNotNull(tx.getResult());
        assertEquals(tx.getResult(), "Mid");
    }
}

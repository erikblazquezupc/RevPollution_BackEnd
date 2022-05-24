package domain.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.EnumStatistics;
import domain.Logro;
import domain.Logro.Tier;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.LogroDataCtrl;

public class TxSwitchActivationLogroTest {
    Logro l;
    LogroDataCtrl ldc = DataCtrl.getInstance().getLogroDataCtrl();

    @Before
    public void setUp(){
        l = new Logro("logro1", Tier.valueOf("bronce"), "condicion", true, 10, EnumStatistics.AchiveAchievement);
        ldc.insert(l);
    }
    
    @After
    public void clean(){
        ldc.delete(l.getName(), l.getTier());
    }

    @Test
    public void testTxSwitchActivation() {
        TxSwitchActivationLogro tx = new TxSwitchActivationLogro(l.getName(), l.getTier());
        tx.execute();
        Logro l2 = ldc.select(l.getName(), l.getTier());
        l.setActivated(false);
        assertEquals(l, l2);
        assertFalse(l2.getActivated());
    }
}
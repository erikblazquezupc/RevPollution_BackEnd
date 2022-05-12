package domain.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Logro;
import domain.Logro.Tier;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.LogroDataCtrl;

public class TxEditLogroTest {
    Logro l;
    LogroDataCtrl ldc;

    @Before
    public void setUp(){
        String tier = "bronce";
        l = new Logro("logro1", Tier.valueOf(tier), "condicion");
        DataCtrl dataCtrl = DataCtrl.getInstance();
        ldc = dataCtrl.getLogroDataCtrl();
        ldc.insert(l);
    }
    
    @After
    public void clean(){
        ldc.delete(l.getName(), l.getTier());
    }

    @Test
    public void testTxEditLogro() {
        TxEditLogro tx = new TxEditLogro(l.getName(), l.getTier(), "test ha cambiado", l.getActivated());
        tx.execute();
        Logro l2 = ldc.select(l.getName(), l.getTier());
        l.setCondition("test ha cambiado");
        assertEquals(l, l2);
    }
}

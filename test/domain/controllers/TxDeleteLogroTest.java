package domain.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Logro;
import domain.Logro.Tier;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.LogroDataCtrl;

public class TxDeleteLogroTest {
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
    public void testTxDeleteLogro() {
        TxDeleteLogro tx = new TxDeleteLogro(l.getName(), l.getTier());
        tx.execute();
        //assertNull(tx.getResult());
        Logro l2 = ldc.select(l.getName(), l.getTier());
        //assertEquals(null, tx.getResult());
        assertNull(l2);
    }
}
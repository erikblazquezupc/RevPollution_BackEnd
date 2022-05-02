package domain.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Logro;
import domain.Logro.Tier;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.LogroDataCtrl;

public class TxGetLogrosTest {
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
    public void testTxGetLogros() {
        TxGetLogros tx = new TxGetLogros();
        tx.execute();
        assertNotNull(tx.getResult());
        assertTrue(tx.getResult().contains(l));
    }
}

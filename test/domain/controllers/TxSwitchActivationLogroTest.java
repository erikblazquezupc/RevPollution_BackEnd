package domain.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

//import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Logro;
import domain.Logro.Tier;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.LogroDataCtrl;

public class TxSwitchActivationLogroTest {
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
    public void testTxSwitchActivation() {
        TxSwitchActivationLogro tx = new TxSwitchActivationLogro(l.getName(), l.getTier());
        tx.execute();

        TxGetLogroAdmin tx1 = new TxGetLogroAdmin(l.getName(), l.getTier());
        tx1.execute();
        assertNotNull(tx1.getResult());

        TxGetLogro tx2 = new TxGetLogro(l.getName(), l.getTier());
        tx2.execute();
        assertNull(tx2.getResult());

        assertEquals(tx1.getResult(), l);
    }
}
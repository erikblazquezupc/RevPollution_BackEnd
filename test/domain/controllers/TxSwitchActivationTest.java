package domain.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.StationStub;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.StationDataCtrl;

public class TxSwitchActivationTest {
    StationStub s;
    StationDataCtrl sdc;

    @Before
    public void setUp(){
        s = new StationStub("test", "Test Rd, 1", 0, 0);
        DataCtrl dataCtrl = DataCtrl.getInstance();
        sdc = dataCtrl.getStationDataCtrl();
        sdc.insert(s);
    }
    
    @After
    public void clean(){
        sdc.delete(s.getId());
    }

    @Test
    public void testTxSwitchActivation() {
        TxSwitchActivationStation tx = new TxSwitchActivationStation(s.getId());
        tx.execute();

        TxGetStationAdmin tx1 = new TxGetStationAdmin(s.getId());
        tx1.execute();
        assertNotNull(tx1.getResult());

        TxGetStation tx2 = new TxGetStation(s.getId());
        tx2.execute();
        assertNull(tx2.getResult());

        assertEquals(tx1.getResult(), s);
    }
}

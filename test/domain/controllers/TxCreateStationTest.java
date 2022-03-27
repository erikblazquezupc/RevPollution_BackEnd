package domain.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.StationStub;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.StationDataCtrl;

public class TxCreateStationTest {
    StationStub s;
    StationDataCtrl sdc;

    @Before
    public void setUp(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        sdc = dataCtrl.getStationDataCtrl();
        s = new StationStub("nametx", "addrestx", 0.0, 0.0);
    }
    
    @After
    public void clean(){
        sdc.delete(s.getId());
    }

    @Test
    public void testTxCreateStation() {
        TxCreateStation tx = new TxCreateStation("nametx", "addrestx", 0.0, 0.0);
        tx.execute();
        s = sdc.selectByName("nametx");
        assertNotNull(s);
        assertTrue(tx.getResult());
    }
}

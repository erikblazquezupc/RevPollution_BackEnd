package domain.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.StationStub;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.StationDataCtrl;

public class TxGetStationsTest {
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
    public void testTxGetStations() {
        TxGetStations tx = new TxGetStations();
        tx.execute();
        assertNotNull(tx.getResult());
        assertTrue(tx.getResult().contains(s));
    }
}

package domain.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.StationStub;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.StationDataCtrl;

public class TxGetStationAdminTest {
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
    public void testTxGetStationAdmin() {
        TxGetStationAdmin tx = new TxGetStationAdmin(s.getId());
        tx.execute();
        assertNotNull(tx.getResult());
        assertEquals(tx.getResult(), s);
    }
}

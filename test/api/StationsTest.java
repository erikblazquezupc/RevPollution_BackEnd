package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.StationStub;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.StationDataCtrl;

public class StationsTest {
    
    StationStub s;
    StationDataCtrl sdc;

    @Before
    public void setUp(){
        s = new StationStub(1234, "test", "Test Rd, 1", 0, 0);
        DataCtrl dataCtrl = DataCtrl.getInstance();
        sdc = dataCtrl.getStationDataCtrl();
        sdc.insert(s);
    }
    
    @After
    public void clean(){
        sdc.delete(s.getId());
    }

    @Test
    public void testGetStations() {
        Stations s = new Stations();
        Response r = s.stations();
        assertEquals(200, r.getStatus());
        assertNotNull(r.getEntity());
        // Falta algun check mes?
    }
}

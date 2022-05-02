package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.StationStub;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.StationDataCtrl;

public class StationsAdminTest {
    StationStub s;
    StationDataCtrl sdc;
    StationsAdmin st;

    @Before
    public void setUp(){
        st = new StationsAdmin();
        s = new StationStub("test", "Test Rd, 1", 2, 2);
        DataCtrl dataCtrl = DataCtrl.getInstance();
        sdc = dataCtrl.getStationDataCtrl();
        sdc.insert(s);
    }
    
    @After
    public void clean(){
        sdc.delete(s.getId());
    }

    @Test
    public void testGetStationsAdmin() {
        Response r = st.getStationsAdmin();
        assertEquals(200, r.getStatus());
        assertNotNull(r.getEntity());
        assertTrue(r.getEntity().toString().contains(s.toString()));
    }

    @Test
    public void testGetStationAdmin() {
        Response r = st.getStationAdmin(s.getId());
        assertEquals(200, r.getStatus());
        assertNotNull(r.getEntity());
        assertTrue(r.getEntity().toString().contains(s.toString()));
    }

    @Test
    public void testSwitchActivation() {
        Response r1 = st.switchActivation(s.getId());
        Response r2 = st.getStationAdmin(s.getId());
        assertEquals(200, r1.getStatus());
        assertEquals(200, r2.getStatus());
        assertNotNull(r2.getEntity());
        assertTrue(r2.getEntity().toString().contains(s.toString()));
    }
}

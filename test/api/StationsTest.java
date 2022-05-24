package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Concentration;
import domain.Particle;
import domain.StationStub;
import domain.dataCtrl.ConcentrationDataCtrl;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.ParticleDataCtrl;
import domain.dataCtrl.StationDataCtrl;

public class StationsTest {
    
    StationStub s;
    Stations st;


    Particle p;
    Concentration c;
    StationDataCtrl sdc = DataCtrl.getInstance().getStationDataCtrl();
    ParticleDataCtrl pdc = DataCtrl.getInstance().getParticleDataCtrl();
    ConcentrationDataCtrl cdc = DataCtrl.getInstance().getConcentrationDataCtrl();

    @Before
    public void setUp(){
        st = new Stations();
        s = new StationStub("testStub", "Test Rd, 1", 5.0, 5.0);
        sdc.insert(s);
        
        p = pdc.select("NO2");
        assertNotNull(p);
        Date d = new Date(20000);
        c = new Concentration(s, p, d, 180.0);
        cdc.insert(c);
    }
    
    @After
    public void clean(){
        sdc.delete(s.getId());
        cdc.delete(s.getId(), "NO2", new Date(20000));
        sdc.delete(s.getId());
    }

    @Test
    public void testGetStations() {
        Response r = st.getStations();
        assertEquals(200, r.getStatus());
        assertNotNull(r.getEntity());
        assertTrue(r.getEntity().toString().contains(s.toString()));
    }

    @Test
    public void testGetStation() {
        Response r = st.getStation(s.getId());
        assertEquals(200, r.getStatus());
        assertNotNull(r.getEntity());
        assertTrue(r.getEntity().toString().contains(s.toString()));
    }

    @Test
    public void testGetQuality() {
        Stations eg = new Stations();
        Response r = eg.getQuality(5.0, 5.0);
        assertEquals(200, r.getStatus());
        assertNotNull(r.getEntity());
        assertTrue(r.getEntity().toString().contains("Mid"));
    }

    @Test
    public void testGetPastQualities() {
        Response r = st.getPastQualities(s.getId());
        assertEquals(200, r.getStatus());
        assertNotNull(r.getEntity());
    }

    @Test
    public void testGetPastQualitiesByHours() {
        Response r = st.getPastQualitiesByHours(s.getId());
        assertEquals(200, r.getStatus());
        assertNotNull(r.getEntity());
    }

    @Test
    public void testGetFutureQualities() {
        Response r = st.getFutureQualities(s.getId());
        assertEquals(200, r.getStatus());
        assertNotNull(r.getEntity());
    }
    
    @Test
    public void testGetFutureQualitiesByHours() {
        Response r = st.getFutureQualitiesByHours(s.getId());
        assertEquals(200, r.getStatus());
        assertNotNull(r.getEntity());
    }
}

package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Particle;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.ParticleDataCtrl;

public class ParticlesTest {

    Particles pAPI;
    Particle p;
    ParticleDataCtrl pdc = DataCtrl.getInstance().getParticleDataCtrl();

    @Before
    public void setUp(){
        pAPI = new Particles();
        p = new Particle("TestParticleApi", "mg/m3", false);
        pdc.insert(p);
    }

    @After
    public void clean(){
        pdc.delete(p.getName());
    }

    @Test
    public void testGetParticles() {
        Response r = pAPI.getParticles();
        assertEquals(200, r.getStatus());
        assertNotNull(r.getEntity());
        assertTrue(r.getEntity().toString().contains(p.toString()));
    }

    @Test
    public void testDeactivate() {
        Response r = pAPI.deactivate(p.getName());
        assertEquals(200, r.getStatus());
        assertNotNull(r.getEntity());
        assertEquals(p, pdc.select(p.getName()));
        assertTrue(r.getEntity().toString().contains("true"));
    }

    @Test
    public void testActivate() {
        p.deactivate();
        pdc.update(p);
        assertEquals(p, pdc.select(p.getName()));
        p.activate();
        Response r = pAPI.activate(p.getName());
        assertEquals(200, r.getStatus());
        assertNotNull(r.getEntity());
        assertEquals(p, pdc.select(p.getName()));
        assertTrue(r.getEntity().toString().contains("true"));
    }
}

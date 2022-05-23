package api;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.EnumStatistics;
import domain.Logro;
import domain.Logro.Tier;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.LogroDataCtrl;

public class LogrosAdminTest {
        
    Logro l;
    LogroDataCtrl ldc = DataCtrl.getInstance().getLogroDataCtrl();
    LogrosAdmin ls;

    @Before
    public void setUp(){
        ls = new LogrosAdmin();
        l = new Logro("test", Tier.valueOf("plata"), "más de 100 tests", true, 10, EnumStatistics.AchiveAchievement);
        ldc.insert(l);
    }
    
    @After
    public void clean(){
        ldc.delete(l.getName(), l.getTier());
    }

    @Test
    public void testGetLogrosAdmin() {
        Response r = ls.getLogrosAdmin();
        assertEquals(200, r.getStatus());
        assertNotNull(r.getEntity());
        assertTrue(r.getEntity().toString().contains(l.toString()));
        
        l.setActivated(false);
        ldc.update(l);
        l.setActivated(true);
        r = ls.getLogrosAdmin();
        assertEquals(200, r.getStatus());
        assertNotNull(r.getEntity());
        assertFalse(r.getEntity().toString().contains(l.toString()));
    }

    @Test
    public void testGetLogroAdmin() {
        Response r = ls.getLogroAdmin(l.getName(), l.getTier());
        assertEquals(200, r.getStatus());
        assertNotNull(r.getEntity());
        assertTrue(r.getEntity().toString().contains(l.toString()));
        
        l.setActivated(false);
        ldc.update(l);
        l.setActivated(true);
        r = ls.getLogroAdmin(l.getName(), l.getTier());
        assertEquals(200, r.getStatus());
        assertNotNull(r.getEntity());
        assertFalse(r.getEntity().toString().contains(l.toString()));
    }

    @Test
    public void testSwitchActivation() {
        Response r = ls.switchActivation(l.getName(), l.getTier());
        l.setActivated(false);
        assertEquals(l, ldc.select(l.getName(), l.getTier()));
        assertEquals(200, r.getStatus());
        assertNotNull(r.getEntity());
        assertTrue(r.getEntity().toString().contains("true"));
    }
}

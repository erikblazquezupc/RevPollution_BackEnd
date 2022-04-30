package api;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Logro;
import domain.Logro.Tier;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.LogroDataCtrl;

public class LogrosAdminTest {
        
    Logro l;
    //Tier tier;
    LogroDataCtrl ldc;
    //Logros ls;
    LogrosAdmin ls;

    @Before
    public void setUp(){
        ls = new LogrosAdmin();
        l = new Logro("test", Tier.valueOf("plata"), "más de 100 tests"/*, false*/);
        DataCtrl dataCtrl = DataCtrl.getInstance();
        ldc = dataCtrl.getLogroDataCtrl();
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
        //assertTrue(r.getEntity().toString().contains(l.toStringActivated()));
        assertTrue(r.getEntity().toString().contains(l.toString()));
    }

    @Test
    public void testGetLogroAdmin() {
        Response r = ls.getLogroAdmin(l.getName(), l.getTier());
        assertEquals(200, r.getStatus());
        assertNotNull(r.getEntity());
        //assertTrue(r.getEntity().toString().contains(l.toStringActivated()));
        assertTrue(r.getEntity().toString().contains(l.toString()));
        //assertTrue(r.getEntity().toString().contains(l.getName()));
    }

    @Test
    public void testSwitchActivation() {
        Response r1 = ls.switchActivation(l.getName(), l.getTier());
        Response r2 = ls.getLogroAdmin(l.getName(), l.getTier());
        assertEquals(200, r1.getStatus());
        assertEquals(200, r2.getStatus());
        assertNotNull(r2.getEntity());
        //assertTrue(r2.getEntity().toString().contains(l.toStringActivated()));
        assertTrue(r2.getEntity().toString().contains(l.toString()));

    }


}

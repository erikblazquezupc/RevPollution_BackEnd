package api;

import static org.junit.Assert.assertEquals;
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

public class LogrosTest {
    
    Logro l;
    LogroDataCtrl ldc;
    Logros ls;

    @Before
    public void setUp(){
        ls = new Logros();
        l = new Logro("test", Tier.valueOf("plata"), "más de 100 tests", true, 10, EnumStatistics.AchiveAchievement);
        DataCtrl dataCtrl = DataCtrl.getInstance();
        ldc = dataCtrl.getLogroDataCtrl();
        ldc.insert(l);
    }
    
    @After
    public void clean(){
        ldc.delete(l.getName(), l.getTier());
    }

    @Test
    public void testGetLogros() {
        Response r = ls.getLogros();
        assertEquals(200, r.getStatus());
        assertNotNull(r.getEntity());
        assertTrue(r.getEntity().toString().contains(l.toString()));
    }
}

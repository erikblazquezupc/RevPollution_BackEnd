package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Expo;
import domain.User;

import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;
import domain.dataCtrl.ExpoDataCtrl;

public class DailyExpoTest {
    Expo e;
    ExpoDataCtrl edc = DataCtrl.getInstance().getExpoDataCtrl();
    User u;
    UserDataCtrl udc = DataCtrl.getInstance().getUserDataCtrl();

    @Before
    public void setUp(){
        u = new User("username", "name", "email@prueba", "password", "tel", "img");
        u.setToken("token");
        udc.insert(u);
        assertNotNull(u.getId());
        e = new Expo(u, 29, 4, 2022, 50.05);
    }

    @After
    public void clean(){
        edc.delete(u.getId());
        ArrayList<Expo> arrE = edc.selectRecent(u.getId());
        assertEquals(0, arrE.size());
        udc.delete(u.getId());
        u = udc.select(u.getId());
        assertNull(u);
    }

    @Test
    public void testAddDailyExpo() {
        DailyExpo de = new DailyExpo();
        Response r = de.addDailyExpo(e.getUser().getToken(), 43.0, 1.436);
        assertEquals(200, r.getStatus());
        assertEquals("true", r.getEntity().toString());
        boolean b = edc.select(u.getId());
        assertTrue(b);
    }
}

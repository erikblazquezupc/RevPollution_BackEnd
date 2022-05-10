package data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Location;
import domain.User;

public class LocationDBTest {
    Location e;
    LocationDB edb;
    User u;
    UserDB udb;

    @Before
    public void setUp(){
        u = new User("username", "name", "email@prueba", "password", "tel", "img");
        u.setToken("token");
        udb = UserDB.getInstance();
        edb = LocationDB.getInstance();
        udb.insert(u);
        assertNotNull(u.getId());
        e = new Location(u, 29, 4, 2022, 50.05);
        Date dat = new Date(100);
        edb.insert(e.getUserId(), dat, e.getValue());
    }

    @After
    public void clean(){
        edb.delete(u.getId());
        ArrayList<Location> arrE = edb.select(u.getId());
        assertEquals(0, arrE.size());
        udb.delete(u.getId());
        u = udb.select(u.getId());
        assertNull(u);
    }

    @Test
    public void testSelect() {
        ArrayList<Location> arrE = edb.select(u.getId());
        assertEquals(1, arrE.size());
        assertEquals(u.getId(), arrE.get(0).getUserId());
        Double d = 50.05;
        assertEquals(d, arrE.get(0).getValue());
    }
}

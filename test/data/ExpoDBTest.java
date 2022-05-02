package data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Expo;
import domain.User;

public class ExpoDBTest {
    Expo e;
    ExpoDB edb;
    User u;
    UserDB udb;

    @Before
    public void setUp(){
        u = new User("username", "name", "email@prueba", "password", "tel", "img");
        u.setToken("token");
        udb = UserDB.getInstance();
        edb = ExpoDB.getInstance();
        udb.insert(u);
        assertNotNull(u.getId());
        e = new Expo(u, 29, 4, 2022, 50.05);
        edb.insert(e.getUserId(), e.getValue());
    }

    @After
    public void clean(){
        edb.delete(u.getId());
        ArrayList<Expo> arrE = edb.selectRecent(u.getId());
        assertEquals(0, arrE.size());
        udb.delete(u.getId());
        u = udb.select(u.getId());
        assertNull(u);
    }

    @Test
    public void testSelectRecent() {
        ArrayList<Expo> arrE = edb.selectRecent(u.getId());
        assertEquals(0, arrE.size());
    }
}

package data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
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
        u = new User("TestExpoDB", "name", "TestExpoDB@prueba", "password", "tel", "img");
        udb = UserDB.getInstance();
        udb.insert(u);

        LocalDate now = LocalDate.now();
        e = new Expo(u, now.getDayOfMonth(), now.getMonthValue(), now.getYear(), 50.05);
        edb = ExpoDB.getInstance();
        edb.insert(e.getUserId(), e.getValue());
    }

    @After
    public void clean(){
        edb.delete(u.getId());
        ArrayList<Expo> arrE = edb.selectAll(u.getId());
        assertEquals(0, arrE.size());
        udb.delete(u.getId());
        u = udb.select(u.getId());
        assertNull(u);
    }

    @Test
    public void testSelectByUser(){
        ArrayList<Expo> actual = edb.selectAll(u.getId());
        assertTrue(actual.contains(e));
    }

    @Test
    public void testSelectRecent() {
        ArrayList<Expo> actual = edb.selectRecent(10);
        assertNotEquals(0, actual.size());
    }
}

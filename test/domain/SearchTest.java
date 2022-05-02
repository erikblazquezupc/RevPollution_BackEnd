package domain;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class SearchTest {
    Search s;
    User u;
    StationStub stat;

    @Before
    public void setUp(){
        stat = new StationStub("Name", "a", 1, 1);
        u = new User("username", "name", "email@prueba", "password", "tel", "img");
        u.setToken("token");
        Date d = new Date(200000);
        s = new Search(u, stat, d);
    }


    @Test
    public void testGetInstant() {
        assertEquals(new Date(200000), s.getInstant());
    }

    @Test
    public void testGetUser() {
        assertEquals(u, s.getUser());
    }

    @Test
    public void testGetStation() {
        assertEquals(stat, s.getStation());
    }

    @Test
    public void testSetInstant() {
        s.setInstant(new Date(10000));
        assertEquals(new Date(10000), s.getInstant());
    }

    @Test
    public void testSetUser() {
        User u2 = new User("username2", "name", "email@prueba", "password", "tel", "img");
        s.setUser(u2);
        assertEquals(u2, s.getUser());
    }

    @Test
    public void testSetStation() {
        StationStub s2 = new StationStub("Station", "a", 2, 2);
        s.setStation(s2);
        assertEquals(s2, s.getStation());
    }

    @Test
    public void testToString() {
        assertEquals("Search [user=name, station=Name, instant="+ new Date(200000) +"]", s.toString());
    }
}

package domain;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class SearchTest {
    Search s;
    User u;
    String name;

    @Before
    public void setUp(){
        name = "Calafell";
        u = new User("username", "name", "email@prueba", "password", "tel", "img");
        u.setToken("token");
        Date d = new Date(200000);
        s = new Search(u, name, d);
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
    public void testGetName() {
        assertEquals(name, s.getName());
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
    public void testSetName() {
        String name2 = "Girona";
        s.setName(name2);
        assertEquals(name2, s.getName());
    }

    @Test
    public void testToString() {
        assertEquals("Search [user=name, name=Calafell, instant="+ new Date(200000) +"]", s.toString());
    }
}

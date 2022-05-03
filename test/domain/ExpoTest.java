package domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ExpoTest {

    Expo e;
    User u;

    @Before
    public void setUp(){
        u = new User(1, "username", "name", "email", "password", "123", "img");
        e = new Expo(u, 29, 4, 2022, 50.05);
    }

    @Test
    public void testGetDay() {
        assertEquals(29, (int) e.getDay());
    }

    @Test
    public void testGetMonth() {
        assertEquals(4, (int) e.getMonth());
    }

    @Test
    public void testGetUser() {
        assertEquals(u, e.getUser());
    }

    @Test
    public void testGetUserId() {
        assertEquals(u.getId(), e.getUser().getId());
    }

    @Test
    public void testGetValue() {
        assertEquals(50.05, (double) e.getValue(), 0.0);
    }

    @Test
    public void testGetYear() {
        assertEquals(2022, (int) e.getYear());
    }

    @Test
    public void testSetDay() {
        e.setDay(30);
        assertEquals(30, (int) e.getDay());
    }

    @Test
    public void testSetMonth() {
        e.setMonth(5);
        assertEquals(5, (int) e.getMonth());
    }

    @Test
    public void testSetUser() {
        User u2 = new User(2, "username2", "name", "email", "password", "123", "img");
        e.setUser(u2);
        assertEquals(u2, e.getUser());
    }

    @Test
    public void testSetValue() {
        e.setValue(51.05);
        assertEquals(51.05, (double) e.getValue(), 0.0);
    }

    @Test
    public void testSetYear() {
        e.setYear(2021);
        assertEquals(2021, (int) e.getYear());
    }

    @Test
    public void testToString() {
        String expected = "Expo [user=name, day=29, month=4, year=2022, value=50.05]";
        assertEquals(expected, e.toString());
    }
}

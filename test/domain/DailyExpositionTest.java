package domain;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class DailyExpositionTest {
    DailyExposition l;
    User u;

    @Before
    public void setUp(){
        u = new User(1, "username", "name", "email", "password", "123", "img");
        l = new DailyExposition(u, 29, 4, 2022, 50.05);
    }

    @Test
    public void testGetDay() {
        assertEquals(29, (int) l.getDay());
    }

    @Test
    public void testGetMonth() {
        assertEquals(4, (int) l.getMonth());
    }

    @Test
    public void testGetUser() {
        assertEquals(u, l.getUser());
    }

    @Test
    public void testGetUserId() {
        assertEquals(u.getId(), l.getUser().getId());
    }

    @Test
    public void testGetValue() {
        assertEquals(50.05, (double) l.getValue(), 0.0);
    }

    @Test
    public void testGetYear() {
        assertEquals(2022, (int) l.getYear());
    }

    @Test
    public void testSetDay() {
        l.setDay(30);
        assertEquals(30, (int) l.getDay());
    }

    @Test
    public void testSetMonth() {
        l.setMonth(5);
        assertEquals(5, (int) l.getMonth());
    }

    @Test
    public void testSetUser() {
        User u2 = new User(2, "username2", "name", "email", "password", "123", "img");
        l.setUser(u2);
        assertEquals(u2, l.getUser());
    }

    @Test
    public void testSetValue() {
        l.setValue(51.05);
        assertEquals(51.05, (double) l.getValue(), 0.0);
    }

    @Test
    public void testSetYear() {
        l.setYear(2021);
        assertEquals(2021, (int) l.getYear());
    }

    @Test
    public void testToString() {
        String expected = "DailyExposition [user=name, day=29, month=4, year=2022, value=50.05]";
        assertEquals(expected, l.toString());
    }
}

package domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import domain.Logro.Tier;

public class UserLogroTest {
    User u, u2;
    Logro l, l2;
    UserLogro ul;

    @Before
    public void setUp(){
        u = new User(132, "username", "name", "email", "password", "tel", "img");
        u2 = new User(1322, "username", "name", "email", "password", "tel", "img");
        l = new Logro("name", Tier.bronce, "cond", false, 10, EnumStatistics.AchiveAchievement);
        l2 = new Logro("name2", Tier.bronce, "cond", false, 10, EnumStatistics.AchiveAchievement);
        ul = new UserLogro(u, l, 10, true);
    }

    @Test
    public void testGetLogro() {
        assertEquals(l, ul.getLogro());
    }

    @Test
    public void testGetLogroName() {
        assertEquals(l.getName(), ul.getLogroName());
    }

    @Test
    public void testGetLogroTier() {
        assertEquals(l.getTier().toString(), ul.getLogroTier());
    }

    @Test
    public void testGetUser() {
        assertEquals(u, ul.getUser());
    }

    @Test
    public void testGetUserId() {
        assertEquals(u.getId(), ul.getUserId());
    }

    @Test
    public void testGetPoints() {
        assertEquals(10, ul.getPoints());
    }

    @Test
    public void testIsAchieved() {
        assertEquals(true, ul.isAchieved());
    }

    @Test
    public void testSetLogro() {
        ul.setLogro(l2);
        assertEquals(l2, ul.getLogro());
    }

    @Test
    public void testSetUser() {
        ul.setUser(u2);
        assertEquals(u2, ul.getUser());
    }

    @Test
    public void testsetAchieved() {
        ul.setAchieved(false);
        assertEquals(false, ul.isAchieved());
    }

    @Test
    public void testSetPoints() {
        ul.setPoints(20);
        assertEquals(20, ul.getPoints());
    }

    @Test
    public void testToString() {
        assertEquals("UserLogro [logro="+l+", user="+u+", points=10, achieved=true]", ul.toString());
    }
}

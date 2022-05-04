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
        l = new Logro("name", Tier.bronce, "cond", false);
        l2 = new Logro("name2", Tier.bronce, "cond", false);
        ul = new UserLogro(u, l);
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
    public void testToString() {
        assertEquals("UserLogro [logro="+l+", user="+u+"]", ul.toString());
    }
}

package data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.User;

public class UserDBTest {
    User u;
    UserDB udb;

    @Before
    public void setUp(){
        u = new User("username", "name", "email@prueba", "password", "tel", "img");
        udb = UserDB.getInstance();
        udb.insert(u);
        assertNotNull(u.getId());
    }

    @After
    public void clean(){
        udb.delete(u.getId());
    }

    @Test
    public void testSelectByUsername() {

    }

    @Test
    public void testUpdate() {

    }

    @Test
    public void testSelect(){
        User u2 = udb.select(u.getId());
        assertEquals(u, u2);
    }
}

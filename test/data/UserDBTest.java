package data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
        u = udb.select(u.getId());
        assertNull(u);
    }

    @Test
    public void testSelectByUsername() {
        User u2 = udb.selectByUsername("username");
        assertEquals(u, u2);
    }

    @Test
    public void testUpdate() {
        u.setName("testname");
        udb.update(u);
        User u2 = udb.select(u.getId());
        assertEquals(u, u2);
    }

    @Test
    public void testSelect(){
        User u2 = udb.select(u.getId());
        assertEquals(u, u2);
    }
}

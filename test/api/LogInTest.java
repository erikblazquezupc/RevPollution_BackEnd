package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.User;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;

public class LogInTest {
    User u;
    UserDataCtrl udc;

    @Before
    public void setUp(){
        u = new User("username", "name", "email@prueba", "password", "tel", "img");
        DataCtrl dataCtrl = DataCtrl.getInstance();
        udc = dataCtrl.getUserDataCtrl();
        udc.insert(u);
    }
    
    @After
    public void clean(){
        udc.delete(u.getId());
    }

    @Test
    public void testLogin() {
        LogIn l = new LogIn();
        Response r = l.login("username", "password");
        assertEquals(200, r.getStatus());
        assertTrue(Boolean.parseBoolean(r.getEntity().toString()));
    }

    @Test
    public void testLoginFail() {
        LogIn l = new LogIn();
        Response r = l.login("username", "passwor");
        assertEquals(200, r.getStatus());
        assertFalse(Boolean.parseBoolean(r.getEntity().toString()));
    }
}

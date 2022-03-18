package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
        assertNotNull(r.getEntity());
    }

    @Test
    public void testLoginFail() {
        LogIn l = new LogIn();
        Response r = l.login("username", "passwor");
        assertEquals(200, r.getStatus());
        assertNull(r.getEntity());
    }
}

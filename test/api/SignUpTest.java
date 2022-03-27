package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.User;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;

public class SignUpTest {
    User u;
    UserDataCtrl udc;

    @Before
    public void setUp(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        udc = dataCtrl.getUserDataCtrl();
    }
    
    @After
    public void clean(){
        udc.delete(u.getId());
    }

    @Test
    public void testSignup() {
        SignUp s = new SignUp();
        Response r = s.signup("usernameapi", "passwordapi", "nameapi", "emailapi", "telfapi", "imageapi");
        assertEquals(200, r.getStatus());
        assertEquals("true", r.getEntity().toString());
        u = udc.selectByUsername("usernameapi");
        assertNotNull(u);

    }
}

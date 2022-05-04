package api;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.User;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;

public class UserEditTest {
    User u;
    UserDataCtrl udc;

    @Before
    public void setUp(){
        u = new User(1, "usernameTestEdit", "name", "emailTestEdit@prueba", "password", "tel", "img");
        u.setToken("tokenUserEdit");
        DataCtrl dataCtrl = DataCtrl.getInstance();
        udc = dataCtrl.getUserDataCtrl();
        udc.insert(u);
    }
    
    @After
    public void clean(){
        udc.delete(u.getId());
    }

    @Test
    public void testUserEdit() {
        UserEdit ue = new UserEdit();
        Response r = ue.useredit("usernamechanged", "password", "emailTestEdit@prueba", "name", "tel", "img", "tokenUserEdit");
        assertEquals(200, r.getStatus());
        u = udc.selectByToken("tokenUserEdit");
        assertEquals(u.getUsername(), "usernamechanged");
    }
}

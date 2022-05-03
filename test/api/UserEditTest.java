package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import javax.swing.text.AbstractDocument;
import javax.swing.text.AbstractDocument.Content;
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
        u = new User(1, "username", "name", "email@prueba", "password", "tel", "img");
        u.setToken("token");
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
        Response r = ue.useredit("usernamechanged", "password", "email@prueba", "name", "tel", "img", "token");
        assertEquals(200, r.getStatus());
        u = udc.selectByToken("token");
        assertEquals(u.getUsername(), "usernamechanged");
    }
}

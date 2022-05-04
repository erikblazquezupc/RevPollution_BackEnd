package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.User;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;

public class UserDeleteTest {
    User u;
    UserDataCtrl udc;

    @Before
    public void setUp() {
        u = new User("usertest", "test", "test@mail.com", "password", "tel", "img" );
        u.setToken("token");
        DataCtrl dataCtrl = DataCtrl.getInstance();
        udc = dataCtrl.getUserDataCtrl();
        udc.insert(u);
    }


    @Test
    public void testDeleteUser() {
        UserDelete ud = new UserDelete();
        Response r = ud.userdelete(Integer.toString(u.getId()));
        assertEquals(200, r.getStatus());
        u = udc.select(u.getId());
        assertNull(u);
    }

}

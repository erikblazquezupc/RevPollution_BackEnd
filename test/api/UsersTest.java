package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.EnumStatistics;
import domain.Logro;
import domain.User;
import domain.UserLogro;
import domain.Logro.Tier;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.LogroDataCtrl;
import domain.dataCtrl.UserDataCtrl;
import domain.dataCtrl.UserLogroDataCtrl;

public class UsersTest {

    User u, u2;
    Logro l;
    UserLogro ul;

    UserDataCtrl udc = DataCtrl.getInstance().getUserDataCtrl();
    LogroDataCtrl ldc = DataCtrl.getInstance().getLogroDataCtrl();
    UserLogroDataCtrl uldc = DataCtrl.getInstance().getUserLogroDataCtrl();

    @Before
    public void setUp(){
        u = new User(132, "APIGetUserLogros", "name", "APIGetUserLogros", "password", "tel", "img");
        u.setToken("APIGetUserLogros");
        u2 = new User(132, "APIGetUserLogros2", "name", "APIGetUserLogros2", "password", "tel", "img");
        u2.setToken("APIGetUserLogros2");
        l = new Logro("APIGetUserLogros", Tier.bronce, "cond", true, 10, EnumStatistics.AchiveAchievement);
        ul = new UserLogro(u, l, 10, true);

        udc.insert(u);
        udc.insert(u2);
        ldc.insert(l);
        uldc.insert(ul);
    }

    @After
    public void clean(){
        uldc.delete(ul.getUserId(), ul.getLogroName(), ul.getLogroTier());
        udc.delete(u.getId());
        udc.delete(u2.getId());
        ldc.delete(l.getName(), l.getTier());
    }


    @Test
    public void testUserinfo() {
        Users uapi = new Users();
        Response r = uapi.getUserLogros("APIGetUserLogros");
        assertEquals(200, r.getStatus());
        assertNotNull(r.getEntity());
        assertTrue(r.getEntity().toString().contains(ul.toString()));
        r = uapi.getUserLogros("APIGetUserLogros2");
        assertEquals(200, r.getStatus());
        assertNotNull(r.getEntity());
        assertFalse(r.getEntity().toString().contains(ul.toString()));
    }
}

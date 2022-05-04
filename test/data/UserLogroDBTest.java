package data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Logro;
import domain.User;
import domain.UserLogro;
import domain.Logro.Tier;

public class UserLogroDBTest {
    User u;
    Logro l;
    UserLogro ul;

    UserDB udb = UserDB.getInstance();
    LogroDB ldb = LogroDB.getInstance();
    UserLogroDB uldb = UserLogroDB.getInstance();

    @Before
    public void setUp(){
        u = new User(132, "UserLogroTest", "name", "UserLogroTest", "password", "tel", "img");
        u.setToken("UserLogroTest");
        l = new Logro("UserLogroTest", Tier.bronce, "cond", false);
        ul = new UserLogro(u, l);

        udb.insert(u);
        ldb.insert(l);
        assertTrue(uldb.insert(ul));
    }

    @After
    public void clean(){
        uldb.delete(ul.getUserId(), ul.getLogroName(), ul.getLogroTier());
        udb.delete(u.getId());
        ldb.delete(l.getName(), l.getTier());
    }

    @Test
    public void testSelect() {
        assertEquals(ul, uldb.select(ul.getUserId(), ul.getLogroName(), ul.getLogroTier()));
    }
    
    @Test
    public void testSelectAll() {
        assertTrue(uldb.selectAll().contains(ul));
    }
    
    @Test
    public void testSelectByUser() {
        assertTrue(uldb.selectByUser(u.getId()).contains(ul));
    }
}

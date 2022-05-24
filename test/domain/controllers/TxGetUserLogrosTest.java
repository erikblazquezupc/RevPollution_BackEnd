package domain.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

public class TxGetUserLogrosTest {

    User u, u2;
    Logro l;
    UserLogro ul;

    UserDataCtrl udc = DataCtrl.getInstance().getUserDataCtrl();
    LogroDataCtrl ldc = DataCtrl.getInstance().getLogroDataCtrl();
    UserLogroDataCtrl uldc = DataCtrl.getInstance().getUserLogroDataCtrl();

    @Before
    public void setUp(){
        u = new User(132, "TxGetUserLogros", "name", "TxGetUserLogros", "password", "tel", "img");
        u.setToken("TxGetUserLogros");
        u2 = new User(132, "TxGetUserLogros2", "name", "TxGetUserLogros2", "password", "tel", "img");
        u2.setToken("TxGetUserLogros2");
        l = new Logro("TxGetUserLogros", Tier.bronce, "cond", true, 10, EnumStatistics.AchiveAchievement);
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
    public void testExecute() {
        TxGetUserLogros tx = new TxGetUserLogros("TxGetUserLogros");
        tx.execute();
        assertTrue(tx.getResult().contains(ul));
        tx = new TxGetUserLogros("TxGetUserLogros2");
        tx.execute();
        assertEquals(0, tx.getResult().size());
    }
}

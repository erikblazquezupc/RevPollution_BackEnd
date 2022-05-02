package domain.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.User;
import domain.Expo;

import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;
import domain.dataCtrl.ExpoDataCtrl;

public class TxAddDailyExpoTest {
    Expo e;
    ExpoDataCtrl edc = DataCtrl.getInstance().getExpoDataCtrl();
    User u;
    UserDataCtrl udc = DataCtrl.getInstance().getUserDataCtrl();

    @Before
    public void setUp(){
        u = new User("username", "name", "email@prueba", "password", "tel", "img");
        u.setToken("token");
        udc.insert(u);
        assertNotNull(u.getId());
        e = new Expo(u, 29, 4, 2022, 50.05);
    }

    @After
    public void clean(){
        edc.delete(u.getId());
        ArrayList<Expo> arrE = edc.selectRecent(u.getId());
        assertEquals(0, arrE.size());
        udc.delete(u.getId());
        u = udc.select(u.getId());
        assertNull(u);
    }


    @Test
    public void testTxAddDailyExpo() {
        TxAddDailyExpo tx = new TxAddDailyExpo("token", 43.0, 1.436);
		tx.execute();
		assertTrue(tx.getResult());
		assertTrue(edc.select(u.getId()));
    }
}

package domain.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.User;
import domain.DailyExposition;

import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;
import domain.dataCtrl.DailyExpositionDataCtrl;

public class TxGetDailyExpoTest {
    DailyExposition e;
    DailyExpositionDataCtrl ddc = DataCtrl.getInstance().getDailyExpositionDataCtrl();
    User u;
    UserDataCtrl udc = DataCtrl.getInstance().getUserDataCtrl();

    @Before
    public void setUp(){
        u = new User("username", "name", "email@prueba", "password", "tel", "img");
        u.setToken("token");
        udc.insert(u);
        assertNotNull(u.getId());
        Date dat = new Date(100);
        ddc.insert(u.getId(), dat, 10.5);
    }

    @After
    public void clean(){
        ddc.delete(u.getId());
        ArrayList<DailyExposition> arrD = ddc.selectRecent(u.getId());
        assertEquals(0, arrD.size());
        udc.delete(u.getId());
        u = udc.select(u.getId());
        assertNull(u);
    }


    @Test
    public void testTxGetDailyExpo() {
        TxGetDailyExpo tx = new TxGetDailyExpo("token");
		tx.execute();
		ArrayList<DailyExposition> arrL = tx.getResult();
        assertTrue(arrL.size() == 1);
        assertEquals(arrL.get(0).getUserId(), u.getId());
        Double d = 10.5;
        assertEquals(arrL.get(0).getValue(), d);
    } 
}

package domain.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.User;
import domain.Location;
import domain.DailyExposition;

import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;
import domain.dataCtrl.LocationDataCtrl;
import domain.dataCtrl.DailyExpositionDataCtrl;

public class TxAddDailyExpoTest {
    Location l;
    LocationDataCtrl ldc = DataCtrl.getInstance().getLocationDataCtrl();
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
        l = new Location(u, 29, 4, 2022, 50.05);
    }

    @After
    public void clean(){
        ldc.delete(u.getId());
        ddc.delete(u.getId());
        ArrayList<DailyExposition> arrD = ddc.selectRecent(u.getId());
        ArrayList<Location> arrL = ldc.select(u.getId());
        assertEquals(0, arrD.size());
        assertEquals(0, arrL.size());
        udc.delete(u.getId());
        u = udc.select(u.getId());
        assertNull(u);
    }


    @Test
    public void testTxAddDailyExpo() {
        Date dat = new Date(90000);
        TxAddDailyExpo tx = new TxAddDailyExpo("token", 43.0, 1.436, dat);
		tx.execute();
		ArrayList<Location> arrL = ldc.select(u.getId());
        assertEquals(arrL.size(), 1);
        assertEquals(arrL.get(0).getUserId(), u.getId());

        dat = new Date(90000000);
        tx = new TxAddDailyExpo("token", 43.0, 1.436, dat);
        tx.execute();
        arrL = ldc.select(u.getId());
        assertEquals(arrL.size(), 1);
        assertEquals(arrL.get(0).getUserId(), u.getId());
        ArrayList<DailyExposition> arrD = ddc.selectRecent(u.getId()); 
        assertEquals(arrD.size(), 1);
        assertEquals(arrD.get(0).getUserId(), u.getId());
    }
}

package domain.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.User;
import domain.Concentration;
import domain.Expo;
import domain.Particle;
import domain.StationStub;
import domain.dataCtrl.ConcentrationDataCtrl;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;
import domain.dataCtrl.ExpoDataCtrl;
import domain.dataCtrl.StationDataCtrl;

public class TxAddDailyExpoTest {
    Expo e;
    ExpoDataCtrl edc = DataCtrl.getInstance().getExpoDataCtrl();
    User u;
    UserDataCtrl udc = DataCtrl.getInstance().getUserDataCtrl();
    StationDataCtrl sdc = DataCtrl.getInstance().getStationDataCtrl();
    StationStub s;
    ConcentrationDataCtrl cdc = DataCtrl.getInstance().getConcentrationDataCtrl();
    Concentration c;

    @Before
    public void setUp(){
        u = new User("username", "a", "email@prueba", "password", "tel", "img");
        u.setToken("token");
        udc.insert(u);
        assertNotNull(u.getId());
        
        s = new StationStub("TestStation", "address", 0.0, 0.0, true);
        sdc.insert(s);
        Particle p = new Particle("O3", "mg/m3");
        c = new Concentration(s, p, Date.from(Instant.now()), 0);
        cdc.insert(c);
        LocalDate now = LocalDate.now();
        e = new Expo(u, now.getDayOfMonth(), now.getMonthValue(), now.getYear(), 0);
    }

    @After
    public void clean(){
        edc.delete(u.getId());
        ArrayList<Expo> arrE = edc.selectRecent(u.getId());
        assertEquals(0, arrE.size());
        udc.delete(u.getId());
        assertNull(udc.select(u.getId()));
        sdc.delete(s.getId());
        assertNull(sdc.select(s.getId()));
        cdc.delete(c.getStationId(), c.getParticleName(), c.getInstant());
        assertNull(cdc.select(c.getStationId(), c.getParticleName(), c.getInstant()));
    }


    @Test
    public void testTxAddDailyExpo() {
        TxAddDailyExpo tx = new TxAddDailyExpo(u.getToken(), 0.0, 1.436);
		tx.execute();
		assertTrue(tx.getResult());
		assertTrue(edc.selectAll(u.getId()).contains(e));
    }
}

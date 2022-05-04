package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Concentration;
import domain.Expo;
import domain.Particle;
import domain.StationStub;
import domain.User;
import domain.dataCtrl.ConcentrationDataCtrl;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;
import domain.dataCtrl.ExpoDataCtrl;
import domain.dataCtrl.StationDataCtrl;

public class DailyExpoTest {
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
        u = new User("TestDailyExpo", "name", "DailyExpo@prueba", "password", "tel", "img");
        u.setToken("DailyExpo");
        udc.insert(u);
        assertNotNull(u.getId());
        e = new Expo(u, 29, 4, 2022, 50.05);

        s = new StationStub("TestStationDayliExpo", "address", 0.0, 0.0, true);
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
        u = udc.select(u.getId());
        assertNull(u);
        sdc.delete(s.getId());
        assertNull(sdc.select(s.getId()));
        cdc.delete(c.getStationId(), c.getParticleName(), c.getInstant());
        assertNull(cdc.select(c.getStationId(), c.getParticleName(), c.getInstant()));
    }

    @Test
    public void testAddDailyExpo() {
        DailyExpo de = new DailyExpo();
        Response r = de.addDailyExpo(e.getUser().getToken(), 0.0, 1.436);
        assertEquals(200, r.getStatus());
        assertEquals("true", r.getEntity().toString());
        assertTrue(edc.selectAll(u.getId()).contains(e));
    }
}

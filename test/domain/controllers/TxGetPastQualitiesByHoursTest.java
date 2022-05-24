package domain.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import domain.Quality;
import domain.Concentration;
import domain.Particle;
import domain.StationStub;

import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.StationDataCtrl;
import domain.dataCtrl.ConcentrationDataCtrl;
import domain.dataCtrl.QualityDataCtrl;

public class TxGetPastQualitiesByHoursTest {
    DataCtrl db;
    QualityDataCtrl qdb;
    Quality q;
    StationStub s;
    StationDataCtrl sdb;
    Concentration c, c1, c2, c3, c4, c5, c6, c7, c8;
    ConcentrationDataCtrl cdb;
    Particle p;

    @Before
    public void setUp() {

        cdb = DataCtrl.getInstance().getConcentrationDataCtrl();
        qdb = DataCtrl.getInstance().getQualityDataCtrl();
        sdb = DataCtrl.getInstance().getStationDataCtrl();

        p = new Particle("PM10", "ug/m3");
        s = new StationStub("stub1", "a", 0, 0);
        sdb.insert(s);

        Calendar cal = Calendar.getInstance();
        Date d = new Date();
        cal.setTime(d);
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        c = new Concentration(s, p, cal.getTime(), 205.0);
        cdb.insert(c);

        cal.set(Calendar.HOUR_OF_DAY, 14);

        cal.add(Calendar.DAY_OF_YEAR, -8);
        c8 = new Concentration(s, p, cal.getTime(), 280.0);
        cdb.insert(c8);

        cal.add(Calendar.DAY_OF_YEAR, 1);
        c7 = new Concentration(s, p, cal.getTime(), 270.0);
        cdb.insert(c7);

        cal.add(Calendar.DAY_OF_YEAR, 1);
        c6 = new Concentration(s, p, cal.getTime(), 260.0);
        cdb.insert(c6);

        cal.add(Calendar.DAY_OF_YEAR, 1);
        c5 = new Concentration(s, p, cal.getTime(), 250.0);
        cdb.insert(c5);

        cal.add(Calendar.DAY_OF_YEAR, 1);
        c4 = new Concentration(s, p, cal.getTime(), 240.0);
        cdb.insert(c4);

        cal.add(Calendar.DAY_OF_YEAR, 1);
        c3 = new Concentration(s, p, cal.getTime(), 230.0);
        cdb.insert(c3);

        cal.add(Calendar.DAY_OF_YEAR, 1);
        c2 = new Concentration(s, p, cal.getTime(), 220.0);
        cdb.insert(c2);

        cal.add(Calendar.DAY_OF_YEAR, 1);
        c1 = new Concentration(s, p, cal.getTime(), 210.0);
        cdb.insert(c1);
    } 

    @After
    public void clean(){
        Calendar cal = Calendar.getInstance();
        Date d = new Date(System.currentTimeMillis());
        cal.setTime(d);
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cdb.delete(c.getStationId(), c.getParticleName(), cal.getTime());
        cal.set(Calendar.HOUR_OF_DAY, 14);

        for (int i = 0; i < 8; ++i) {
            cal.add(Calendar.DAY_OF_YEAR, -1);
            cdb.delete(c.getStationId(), c.getParticleName(), cal.getTime());
        }
        sdb.delete(s.getId());
    }

    @Test
    public void testTxGetPastQualitiesByHours() {
        Date now = new Date();
        Calendar cNow = Calendar.getInstance();
        cNow.setTime(now);
        int h = cNow.get(Calendar.HOUR_OF_DAY);

        TxGetPastQualitiesByHours tx = new TxGetPastQualitiesByHours(s.getId());
        tx.execute();
        assertNotNull(tx.getResult());
        if (h < 14) assertEquals(4, tx.getResult().size());
        else if (h < 14) assertEquals(3, tx.getResult().size());

        if (h > 14) {
            assertEquals(220.0, tx.getResult().get(0).getValue(), 0);
            assertEquals(210.0, tx.getResult().get(1).getValue(), 0);
            assertEquals(205.0, tx.getResult().get(2).getValue(), 0);
        }
        else {
            assertEquals(230.0, tx.getResult().get(0).getValue(), 0);
            assertEquals(220.0, tx.getResult().get(1).getValue(), 0);
            assertEquals(210.0, tx.getResult().get(2).getValue(), 0);
        }
    }
}

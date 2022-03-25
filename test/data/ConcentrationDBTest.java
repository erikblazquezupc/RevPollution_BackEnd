package data;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Concentration;
import domain.Particle;
import domain.StationStub;

public class ConcentrationDBTest {
    Concentration c;
    Concentration c2;
    ConcentrationDB cdb;
    StationStub s;
    StationDB sdb;
    Particle p;
    Particle p2;
    ParticleDB pdb;

    @Before
    public void setUp(){
        p = new Particle("stub1", "mg/m3");
        p2 = new Particle("stub2", "mg/m3");
        s = new StationStub("stub1", "a", 0, 0);
        c = new Concentration(s, p, new Date(20000), 2.3);
        c2 = new Concentration(s, p2, new Date(10000), 2.246);
        cdb = ConcentrationDB.getInstance();
        sdb = StationDB.getInstance();
        pdb = ParticleDB.getInstance();
        sdb.insert(s);
        pdb.insert(p);
        pdb.insert(p2);
        cdb.insert(c);
        cdb.insert(c2);
    }

    @After
    public void clean(){
        cdb.delete(c.getStationId(), c.getParticleName(), c.getInstant());
        cdb.delete(c2.getStationId(), c2.getParticleName(), c2.getInstant());
        pdb.delete(p.getName());
        pdb.delete(p2.getName());
        sdb.delete(s.getId());
    }

    @Test
    public void testSelect() {
        assertEquals(c, cdb.select(s.getId(), "Stub1", new Date(20000)));
    }

    @Test
    public void testSelectMostRecentFromStation() {
        ArrayList<Concentration> expected = new ArrayList<Concentration>();
        expected.add(c);
        expected.add(c2);
        assertEquals(expected, cdb.selectMostRecentFromStation(s.getId()));
    }

    @Test
    public void testUpdate() {
        c.setValue(4.45);
        cdb.update(c);
        assertEquals(c, cdb.select(s.getId(), "Stub1", new Date(20000)));
    }
}

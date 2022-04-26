package data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Particle;

public class ParticleDBTest {
    Particle p;
    Particle p2;
    Particle p3;
    ParticleDB pdb;

    @Before
    public void setUp(){
        p = new Particle("p1", "mg/m3");
        p2 = new Particle("p2", "ug/m3");
        p3 = new Particle("p3", "ppm");
        pdb = ParticleDB.getInstance();
        pdb.insert(p);
        pdb.insert(p2);
        pdb.insert(p3);
    }

    @After
    public void clean(){
        pdb.delete(p.getName());
        pdb.delete(p2.getName());
        pdb.delete(p3.getName());
    }

    @Test
    public void testSelect() {
        assertEquals(p, pdb.select("p1"));
    }

    @Test
    public void testSelectAll() {
        ArrayList<Particle> expected = new ArrayList<Particle>();
        expected.add(p);
        expected.add(p2);
        expected.add(p3);
        assertTrue(pdb.selectAll().containsAll(expected));
    }

    @Test
    public void testUpdate() {
        p.setUnit("ng/m3");
        pdb.update(p);
        assertEquals(p, pdb.select("p1"));
    }
}

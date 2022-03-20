package domain;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class ConcentrationTest {
    Concentration c;
    Particle p;
    StationStub s;

    @Before
    public void setUp(){
        s = new StationStub("Name", "a", 1, 1);
        p = new Particle("03");
        Date d = new Date(200000);
        c = new Concentration(s, p, d, 2.5);
    }


    @Test
    public void testGetInstant() {
        assertEquals(new Date(200000), c.getInstant());
    }

    @Test
    public void testGetParticle() {
        assertEquals(p, c.getParticle());
    }

    @Test
    public void testGetStation() {
        assertEquals(s, c.getStation());
    }

    @Test
    public void testGetValue() {
        assertEquals(2.5, c.getValue(), 0);
    }

    @Test
    public void testSetInstant() {
        c.setInstant(new Date(10000));
        assertEquals(new Date(10000), c.getInstant());
    }

    @Test
    public void testSetParticle() {
        Particle p2 = new Particle("H2S");
        c.setParticle(p2);
        assertEquals(p2, c.getParticle());
    }

    @Test
    public void testSetStation() {
        StationStub s2 = new StationStub("Station", "a", 2, 2);
        c.setStation(s2);
        assertEquals(s2, c.getStation());
    }

    @Test
    public void testSetValue() {
        c.setValue(3);
        assertEquals(3, c.getValue(), 0);
    }

    @Test
    public void testToString() {
        assertEquals("Concentration [station=Name, particle=03, instant="+ new Date(200000) +", value=2.5]", c.toString());
    }
}

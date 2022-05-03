package domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ParticleTest {
    Particle p;

    @Before
    public void setUp(){
        p = new Particle("03", "ug/m3");
    }

    @Test
    public void testGetName() {
        assertEquals("03", p.getName());
    }

    @Test
    public void testGetUnit() {
        assertEquals("ug/m3", p.getUnit());
    }

    @Test
    public void testSetName() {
        p.setName("H2S");
        assertEquals("H2S", p.getName());
    }

    @Test
    public void testSetUnit() {
        p.setUnit("mg/m3");
        assertEquals("mg/m3", p.getUnit());
    }

    @Test
    public void testToString() {
        assertEquals("Particle [activated=true, name=03, unit=ug/m3]", p.toString());
    }
}

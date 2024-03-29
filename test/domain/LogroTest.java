package domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import domain.Logro.Tier;


public class LogroTest {
    Logro l;
    String tier;

    @Before
    public void setUp(){
        tier = "oro";
        l = new Logro("logro1", Tier.valueOf(tier), "testeando", true);
    }

    @Test
    public void testGetName() {
        assertEquals("logro1", l.getName());
    }

    @Test
    public void testGetTier() {
        assertEquals(Tier.valueOf(tier), l.getTier());
    }

    @Test
    public void testGetCondition() {
        assertEquals("testeando", l.getCondition());
    }

    @Test
    public void testGetActivated() {
        assertTrue(l.getActivated());
    }

    @Test
    public void testSetName() {
        l.setName("logro2");
        assertEquals("logro2", l.getName());
    }

    @Test
    public void testSetTier() {
        l.setTier(Tier.valueOf("plata"));
        assertEquals(Tier.valueOf("plata"), l.getTier());
    }

    @Test
    public void testSetCondition() {
        l.setCondition("condicionNueva");
        assertEquals("condicionNueva", l.getCondition());
    }

    @Test
    public void testSetActivated() {
        l.setActivated(false);
        assertEquals(false, l.getActivated());
    }

    @Test
    public void testToStringActivated() {
        l.setActivated(true);
        assertEquals("Logro [name=logro1, tier=oro, cond=testeando, activated=1]", l.toStringActivated());
    }

    @Test
    public void testToString() {
        assertEquals("Logro [activated=true, cond=testeando, name=logro1, tier=oro]", l.toString());
    }
}

package domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class LogroTest {
    Logro l;

    @Before
    public void setUp(){
        l = new Logro("logro1", oro, "testeando");
    }

    @Test
    public void testGetName() {
        assertEquals("logro1", l.getName());
    }

    @Test
    public void testGetTier() {
        assertEquals(oro, l.getTier());
    }

    @Test
    public void testGetCondition() {
        assertEquals("testeando", l.getCondition());
    }

    @Test
    public void testSetName() {
        l.setName("logro2");
        assertEquals("logro2", l.getName());
    }

    @Test
    public void testSetTier() {
        l.setTier(plata);
        assertEquals(plata, l.getTier());
    }

    @Test
    public void testSetCondition() {
        l.setTier("condicionNueva");
        assertEquals("condicionNueva", l.getCondition());
    }

    @Test
    public void testToString() {
        assertEquals("Logro [name=logro1, tier=oro, condition=testeando]", l.toString());
    }
}

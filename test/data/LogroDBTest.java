package data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Logro;
import domain.Logro.Tier;


public class LogroDBTest {
    Logro l;
    Logro l2;
    LogroDB ldb;

    @Before
    public void setUp(){
        l = new Logro("logro1", Tier.valueOf("plata"), "testeando");
        l2 = new Logro("logro2", Tier.valueOf("plata"), "testeando");
        ldb = LogroDB.getInstance();
        ldb.insert(l);
        ldb.insert(l2);
        //assertNotNull(l.getName());
        //assertNotNull(l2.getName());
    }

    @After
    public void clean(){
        ldb.delete(l.getName(), l.getTier());
        ldb.delete(l2.getName(), l2.getTier());
    }

    /*@Test
    public void testInsert() {
        Logro l3 = new Logro("logro3", Tier.valueOf("plata"), "testeando");
        ldb = LogroDB.getInstance();
        ldb.insert(l3); 
        assertNotNull(l3.getName());
        assertNotNull(l3.getTier());
    }*/

    @Test
    public void testSelect() {
        assertEquals(l, ldb.select(l.getName(), l.getTier()));
    }
    
    @Test
    public void testSelectAll() {
        ArrayList<Logro> expected = new ArrayList<Logro>();
        expected.add(l);
        expected.add(l2);
        assertTrue(ldb.selectAll().containsAll(expected));
    }

    @Test
    public void testUpdate() {
        //l.setName("logro3");
        //l.setTier(Tier.valueOf("oro"));
        l.setCondition("nueva");
        //assertEquals("nueva", l.getCondition());
        ldb.update(l);
        assertEquals(l, ldb.select("logro1", Tier.valueOf("plata")));
    }
}
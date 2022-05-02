package data;

import static org.junit.Assert.assertEquals;
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
    Logro l3;
    LogroDB ldb;

    @Before
    public void setUp(){
        l = new Logro("Testlogro1", Tier.valueOf("plata"), "testeando");
        l2 = new Logro("Testlogro2", Tier.valueOf("plata"), "testeando");
        l3 = new Logro("Testlogro1", Tier.valueOf("oro"), "testeando");
        ldb = LogroDB.getInstance();
        ldb.insert(l);
        ldb.insert(l2);
        ldb.insert(l3);
    }

    @After
    public void clean(){
        ldb.delete(l.getName(), l.getTier());
        ldb.delete(l2.getName(), l2.getTier());
        ldb.delete(l3.getName(), l3.getTier());
    }

    @Test
    public void testSelect() {
        assertEquals(l, ldb.select(l.getName(), l.getTier()));
    }

    @Test
    public void testSelectByName() {
        ArrayList<Logro> actual = ldb.selectByName("Testlogro1");
        ArrayList<Logro> expecteds = new ArrayList<Logro>();
        expecteds.add(l);
        expecteds.add(l3);
        assertEquals(expecteds, actual);
    }
    
    @Test
    public void testSelectAll() {
        ArrayList<Logro> actuals = ldb.selectAll();
        assertTrue(actuals.contains(l));
    }

    @Test
    public void testUpdate() {
        l.setCondition("Nueva condicion");
        ldb.update(l);
        Logro actual = ldb.select(l.getName(), l.getTier());
        assertEquals(l, actual);
    }
}
package data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.EnumStatistics;
import domain.Logro;
import domain.Logro.Tier;


public class LogroDBTest {
    Logro l;
    Logro l2;
    Logro l3;
    LogroDB ldb;

    @Before
    public void setUp(){
        l = new Logro("Testlogro1", Tier.valueOf("plata"), "testeando", true, 10, EnumStatistics.AchiveAchievement);
        l2 = new Logro("Testlogro2", Tier.valueOf("plata"), "testeando", true, 10, EnumStatistics.LogIn);
        l3 = new Logro("Testlogro1", Tier.valueOf("oro"), "testeando", true, 20, EnumStatistics.AchiveAchievement);
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
    public void testSelectAdmin() {
        assertEquals(l, ldb.selectAdmin(l.getName(), l.getTier()));
    }
    
    @Test
    public void testSelectAll() {
        ArrayList<Logro> actuals = ldb.selectAll();
        assertTrue(actuals.contains(l));
    }

    @Test
    public void testSelectAllAdmin() {
        ArrayList<Logro> expected = new ArrayList<Logro>();
        expected.add(l);
        expected.add(l2);
        assertTrue(ldb.selectAllAdmin().containsAll(expected));
    }

    @Test
    public void testUpdate() {
        l.setCondition("Nueva condicion");
        ldb.update(l);
        assertEquals(l, ldb.select("Testlogro1", Tier.valueOf("plata")));
    }

    @Test
    public void testSwitchActivation() {
        l.setActivated(false);
        ldb.switchActivation(l.getName(), l.getTier());
        assertEquals(l, ldb.select(l.getName(), l.getTier())); 
    }
}
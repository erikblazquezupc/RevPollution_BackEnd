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
        l = new Logro("logro1", Tier.valueOf(tier), "testeando", true, 10, EnumStatistics.LogIn);
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
    public void testSetLimit(){
        l.setLimit(20);
        assertEquals(20, l.getLimit());
    }
    
    @Test
    public void testGetLimit(){
        assertEquals(10, l.getLimit());
    }

    @Test
    public void testSetStatistic(){
        l.setStatistic(EnumStatistics.AchiveAchievement);
        assertEquals(EnumStatistics.AchiveAchievement, l.getStatistic());
    }
    
    @Test
    public void testGetStatistic(){
        assertEquals(EnumStatistics.LogIn, l.getStatistic());
    }


    @Test
    public void testToString() {
        assertEquals("Logro [activated=true, cond=testeando, name=logro1, tier=oro, limit=10, statistic=LogIn]", l.toString());
    }
}

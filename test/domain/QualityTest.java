package domain;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class QualityTest {
    Quality q;
    StationStub s;

    @Before
    public void setUp(){
        s = new StationStub("Name", "a", 1, 1);
        Date d = new Date(200000);
        q = new Quality(s, d, 2.5);
    }

    @Test
    public void testGetInstant() {
        assertEquals(new Date(200000), q.getInstant());
    }

    @Test
    public void testGetStation() {
        assertEquals(s, q.getStation());
    }

    @Test
    public void testGetStationId() {
        assertEquals(s.getId(), q.getStationId());
    }

    @Test
    public void testGetValue() {
        assertEquals(2.5, q.getValue(), 0);
    }

    @Test
    public void testSetInstant() {
        q.setInstant(new Date(10000));
        assertEquals(new Date(10000), q.getInstant());
    }

    @Test
    public void testSetStation() {
        StationStub s2 = new StationStub("Station", "a", 2, 2);
        q.setStation(s2);
        assertEquals(s2, q.getStation());
    }

    @Test
    public void testSetValue() {
        q.setValue(3);
        assertEquals(3, q.getValue(), 0);
    }

    @Test
    public void testToString() {
        assertEquals("Quality [station=Name, instant="+ new Date(200000) +", value=2.5]", q.toString());
    }
}

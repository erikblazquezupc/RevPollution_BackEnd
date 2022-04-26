package domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class StationStubTest {
    StationStub s;

    @Before
    public void setUp(){
        s = new StationStub(1, "name", "address", 0, 0);
    }

    @Test
    public void testGetAddress() {
        assertEquals("address", s.getAddress());
    }

    @Test
    public void testGetId() {
        assertEquals(1, s.getId());
    }

    @Test
    public void testGetLat() {
        assertEquals(0, s.getLat(), 0);
    }

    @Test
    public void testGetLon() {
        assertEquals(0, s.getLon(), 0);
    }

    @Test
    public void testGetName() {
        assertEquals("name", s.getName());
    }

    @Test
    public void testSetAddress() {
        s.setAddress("new Address");
        assertEquals("new Address", s.getAddress());
    }

    @Test
    public void testSetId() {
        s.setId(2);
        assertEquals(2, s.getId());
    }

    @Test
    public void testSetLat() {
        s.setLat(13);
        assertEquals(13, s.getLat(), 0);
    }

    @Test
    public void testSetLon() {
        s.setLon(124.2);
        assertEquals(124.2, s.getLon(), 0);
    }

    @Test
    public void testSetName() {
        s.setName("new name");
        assertEquals("new name", s.getName());
    }

    @Test
    public void testToString() {
        assertEquals("StationStub [id=1, name=name, address=address, lat=0.0, lon=0.0]", s.toString());
    }
}

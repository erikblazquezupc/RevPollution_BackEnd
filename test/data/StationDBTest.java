package data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.StationStub;

public class StationDBTest {
    StationStub s;
    StationStub s2;
    StationDB sdb;

    @Before
    public void setUp(){
        s = new StationStub("Stub1", "a", 0, 0);
        s2 = new StationStub("Stub2", "a", 0, 0);
        sdb = StationDB.getInstance();
        sdb.insert(s);
        sdb.insert(s2);
        assertNotNull(s.getId());
        assertNotNull(s2.getId());
    }

    @After
    public void clean(){
        sdb.delete(s.getId());
        sdb.delete(s2.getId());
    }

    @Test
    public void testSelect() {
        assertEquals(s, sdb.select(s.getId()));
    }
    
    @Test
    public void testSelectAll() {
        ArrayList<StationStub> expected = new ArrayList<StationStub>();
        expected.add(s);
        expected.add(s2);
        assertTrue(sdb.selectAll().containsAll(expected));
    }
    
    @Test
    public void testSelectByName() {
        assertEquals(s2, sdb.selectByName("Stub2"));
    }

    @Test
    public void testUpdate() {
        s.setName("Stub1_c");
        sdb.update(s);
        assertEquals(s, sdb.selectByName("Stub1_c"));
    }
}

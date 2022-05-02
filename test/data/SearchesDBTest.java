package data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.User;
import domain.Search;
import domain.StationStub;

import java.util.ArrayList;
import java.util.Date;

public class SearchesDBTest {
    Search s;
    User u;
    StationStub stat;
    SearchDB sdb;
    UserDB udb;
    StationDB ssdb;

    @Before
    public void setUp(){
        u = new User("username", "name", "email@prueba", "password", "tel", "img");
        u.setToken("token");
        udb = UserDB.getInstance();
        udb.insert(u);
        assertNotNull(u.getId());

        stat = new StationStub("Stub1", "a", 0, 0);
        ssdb = StationDB.getInstance();
        ssdb.insert(stat);
        assertNotNull(stat.getId());

        Date d = new Date(1650837600);
        s = new Search(u, stat, d);
        sdb = SearchDB.getInstance();
        sdb.insert(s);
    }
    
    @After
    public void clean(){

        sdb.delete(u.getId(), stat.getId());
        s = sdb.select(u.getId(), stat.getId());
        assertNull(s);

        ssdb.delete(stat.getId());
        stat = ssdb.select(stat.getId());
        assertNull(stat);

        udb.delete(u.getId());
        u = udb.select(u.getId());
        assertNull(u);
    }

    @Test
    public void testSelectRecent() {
        ArrayList<Search> s2 = sdb.selectRecent(u.getId());
        assertEquals(s.getUser().getId(), s2.get(0).getUser().getId());
        assertEquals(s.getStation().getId(), s2.get(0).getStation().getId());
    }

    @Test
    public void testSelect() {
        Search s2 = sdb.select(u.getId(), stat.getId());
        assertEquals(s.getUser().getId(), s2.getUser().getId());
        assertEquals(s.getStation().getId(), s2.getStation().getId());
    }
}

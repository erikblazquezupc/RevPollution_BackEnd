package data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.User;
import domain.Search;

import java.util.ArrayList;
import java.util.Date;

public class SearchesDBTest {
    Search s;
    User u;
    String name;
    SearchDB sdb;
    UserDB udb;

    @Before
    public void setUp(){
        u = new User("username", "name", "email@prueba", "password", "tel", "img");
        u.setToken("token");
        udb = UserDB.getInstance();
        udb.insert(u);
        assertNotNull(u.getId());

        name = "Calafell";

        Date d = new Date(1650837600);
        s = new Search(u, name, d);
        sdb = SearchDB.getInstance();
        sdb.insert(s);
    }
    
    @After
    public void clean(){

        sdb.delete(u.getId(), name);
        s = sdb.select(u.getId(), name);
        assertNull(s);

        udb.delete(u.getId());
        u = udb.select(u.getId());
        assertNull(u);
    }

    @Test
    public void testSelectRecent() {
        ArrayList<Search> s2 = sdb.selectRecent(u.getId());
        assertEquals(s.getUser().getId(), s2.get(0).getUser().getId());
        assertEquals(s.getName(), s2.get(0).getName());
    }

    @Test
    public void testSelect() {
        Search s2 = sdb.select(u.getId(), name);
        assertEquals(s.getUser().getId(), s2.getUser().getId());
        assertEquals(s.getName(), s2.getName());
    }
}

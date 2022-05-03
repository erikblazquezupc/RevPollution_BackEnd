package api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.User;
import domain.Search;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import javax.ws.rs.core.Response;

import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;
import domain.dataCtrl.SearchDataCtrl;

public class UserSearchesTest {
    Search s;
    User u;
    String name;
    UserSearches uAPI;

    DataCtrl dc = DataCtrl.getInstance();
    SearchDataCtrl sdc = dc.getSearchDataCtrl();
    UserDataCtrl udc = dc.getUserDataCtrl();

    @Before
    public void setUp(){
        uAPI = new UserSearches();
        u = new User("TestApiuserSear", "name", "testapiusersear@prueba", "password", "tel", "img");
        u.setToken("token");
        udc.insert(u);
        name = "Calafell";
        Date d = new Date(1650838000);
        s = new Search(u, name, d);
        sdc.insert(s);
    }
    
    @After
    public void clean(){
        sdc.delete(u.getId(), name);
        udc.delete(u.getId());
    }

    @Test
    public void testRecentSearches() {
        Response r = uAPI.recentSearches("token");
        assertEquals(200, r.getStatus());
        assertNotNull(r.getEntity());
        assertTrue(r.getEntity().toString().contains(s.toString()));
    }
    
    @Test
    public void testAddSearch(){
        Response r = uAPI.addSearch("token", "hola");
        assertEquals(200, r.getStatus());
        assertNotNull(r.getEntity());
        assertTrue(r.getEntity().toString().contains("true"));
        Search s2 = sdc.select(u.getId(), "hola"); 
        assertEquals("hola", s2.getName());
        assertEquals(s.getUser(), s2.getUser());
        sdc.delete(u.getId(), "hola");
    }

    @Test
    public void testDeleteSearch(){
        Date d = new Date(1650838000);
        Search s2 = new Search(u, "hola", d);
        sdc.insert(s2);
        assertEquals(s2, sdc.select(u.getId(), "hola"));
        Response r = uAPI.deleteSearch("token", "hola");
        assertEquals(200, r.getStatus());
        assertNotNull(r.getEntity());
        assertTrue(r.getEntity().toString().contains("true"));
        assertNull(sdc.select(u.getId(), "hola"));
    }
}

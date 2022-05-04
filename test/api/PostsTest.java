package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.PostDB;
import data.UserDB;
import domain.Post;
import domain.User;
import domain.dataCtrl.PostDataCtrl;
import domain.dataCtrl.UserDataCtrl;

public class PostsTest {
    Post pGeneral;
    Post pAPI;
    PostDataCtrl pdb;

    User u;
    UserDataCtrl udb;

    String expectedJSON = "{\"username\":\"testAPIPosts\",\"profilepic\":\"testAPIPosts\",\"text\":\"testAPI\",\"timestamp\":100}";

    @Before
    public void setUp(){
        u = new User("testAPIPosts", "testAPIPosts", "testAPIPosts@test", "testAPIPosts", "1234", "testAPIPosts");
        u.setToken("testAPIPosts");
        udb = UserDB.getInstance();
        udb.insert(u);
        assertNotNull(u.getId());

        pGeneral = new Post(u, "testAPI", 100);
        pdb = PostDB.getInstance();
        assertTrue(pdb.insert(pGeneral));
    }

    @After
    public void clean(){
        pdb.delete(pGeneral.getCreator().getId(), pGeneral.getPostedOn());
        pGeneral = pdb.select(pGeneral.getCreator().getId(), pGeneral.getPostedOn());
        assertNull(pGeneral);

        udb.delete(u.getId());
        u = udb.select(u.getId());
        assertNull(u);
    }

    @Test
    public void testNewPost(){
        Posts psAPI =  new Posts();
        Response r = psAPI.newPost("testApi", u.getToken());
        assertEquals(200, r.getStatus());
        assertNotNull(r.getEntity());
        pAPI = pdb.select(u.getId(), Long.valueOf(r.getEntity().toString()));
        assertNotNull(pAPI);

        pdb.delete(pAPI.getCreator().getId(), pAPI.getPostedOn());
        pAPI = pdb.select(pAPI.getCreator().getId(), pAPI.getPostedOn());
        assertNull(pAPI);
    }
    

    @Test
    public void testGetAllInfo() {
        Posts psAPI = new Posts();
        Response r = psAPI.getPosts(null, null);
        assertEquals(200, r.getStatus());
        assertTrue("pGeneral in JSON response", r.getEntity().toString().contains(expectedJSON));
    }

    @Test
    public void testGetBiggerInfo() {
        Posts psAPI = new Posts();
        Response r = psAPI.getPosts((long) 1, null);
        assertEquals(200, r.getStatus());
        assertTrue("pGeneral in JSON response", r.getEntity().toString().contains(expectedJSON));

        r = psAPI.getPosts((long) 100, null);
        assertEquals(200, r.getStatus());
        assertTrue("pGeneral in JSON response", r.getEntity().toString().contains(expectedJSON));
        
        r = psAPI.getPosts((long) 200, null);
        assertEquals(200, r.getStatus());
        assertFalse("pGeneral in JSON response", r.getEntity().toString().contains(expectedJSON));
    }

    @Test
    public void testGetSmallerInfo() {
        Posts psAPI = new Posts();
        Response r = psAPI.getPosts(null, (long) 200);
        assertEquals(200, r.getStatus());
        assertTrue("pGeneral in JSON response", r.getEntity().toString().contains(expectedJSON));

        r = psAPI.getPosts(null, (long) 100);
        assertEquals(200, r.getStatus());
        assertTrue("pGeneral in JSON response", r.getEntity().toString().contains(expectedJSON));
        
        r = psAPI.getPosts(null, (long) 99);
        assertEquals(200, r.getStatus());
        assertFalse("pGeneral in JSON response", r.getEntity().toString().contains(expectedJSON));
    }

    @Test
    public void testGetBothInfo() {
        Posts psAPI = new Posts();
        Response r = psAPI.getPosts((long) 1, (long) 200);
        assertEquals(200, r.getStatus());
        assertTrue("pGeneral in JSON response", r.getEntity().toString().contains(expectedJSON));

        r = psAPI.getPosts((long) 100, (long) 100);
        assertEquals(200, r.getStatus());
        assertTrue("pGeneral in JSON response", r.getEntity().toString().contains(expectedJSON));
        
        r = psAPI.getPosts((long) 1, (long) 99);
        assertEquals(200, r.getStatus());
        assertFalse("pGeneral in JSON response", r.getEntity().toString().contains(expectedJSON));

        r = psAPI.getPosts((long) 101, (long) 200);
        assertEquals(200, r.getStatus());
        assertFalse("pGeneral in JSON response", r.getEntity().toString().contains(expectedJSON));

    }
}

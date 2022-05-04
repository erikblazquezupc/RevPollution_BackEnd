package domain.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.codehaus.jettison.json.JSONArray;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.PostDB;
import data.UserDB;
import domain.Post;
import domain.User;
import domain.dataCtrl.PostDataCtrl;
import domain.dataCtrl.UserDataCtrl;

public class TxGetAllPostsTest {
    Post p;
    PostDataCtrl pdb;

    User u;
    UserDataCtrl udb;

    String expectedJSON = "{\"username\":\"testTxGetAllPosts\",\"profilepic\":\"testTxGetAllPosts\",\"text\":\"testTxGetAllPosts\",\"timestamp\":100}";
    
    @Before
    public void setUp() {
        u = new User("testTxGetAllPosts", "testTxGetAllPosts", "testTxGetAllPosts@test", "testTxGetAllPosts", "1234", "testTxGetAllPosts");
        u.setToken("testTxGetAllPosts");
        udb = UserDB.getInstance();
        udb.insert(u);
        assertNotNull(u.getId());

        p = new Post(u, "testTxGetAllPosts", 100);
        pdb = PostDB.getInstance();
        assertTrue(pdb.insert(p));
    }

    @After
    public void clean() {
        pdb.delete(p.getCreator().getId(), p.getPostedOn());
        p = pdb.select(p.getCreator().getId(), p.getPostedOn());
        assertNull(p);

        udb.delete(u.getId());
        u = udb.select(u.getId());
        assertNull(u);
    }

    @Test
    public void testTxGetAllPosts() {
        TxGetAllPosts tx = new TxGetAllPosts();
        tx.execute();
        JSONArray result = tx.getResult();
        assertTrue("p is contained in the result", result.toString().contains(expectedJSON));
    }
}

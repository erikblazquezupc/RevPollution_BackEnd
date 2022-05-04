package domain.controllers;

import static org.junit.Assert.assertFalse;
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

public class TxGetPostsTest {
    Post p;
    PostDataCtrl pdb;

    User u;
    UserDataCtrl udb;

    String expectedJSON = "{\"username\":\"testTxGetPosts\",\"profilepic\":\"testTxGetPosts\",\"text\":\"testTxGetPosts\",\"timestamp\":100}";
    
    @Before
    public void setUp() {
        u = new User("testTxGetPosts", "testTxGetPosts", "testTxGetPosts@test", "testTxGetPosts", "1234", "testTxGetPosts");
        u.setToken("testTxGetPosts");
        udb = UserDB.getInstance();
        udb.insert(u);
        assertNotNull(u.getId());

        p = new Post(u, "testTxGetPosts", 100);
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
    public void testTxGetPosts() {
        TxGetPosts tx = new TxGetPosts((long) 1, null);
        tx.execute();
        JSONArray result = tx.getResult();
        assertTrue("p is contained in the result", result.toString().contains(expectedJSON));

        tx = new TxGetPosts((long) 100, null);
        tx.execute();
        result = tx.getResult();
        assertTrue("p is contained in the result", result.toString().contains(expectedJSON));

        tx = new TxGetPosts((long) 200, null);
        tx.execute();
        result = tx.getResult();
        assertFalse("p is contained in the result", result.toString().contains(expectedJSON));


        tx = new TxGetPosts(null , (long) 200);
        tx.execute();
        result = tx.getResult();
        assertTrue("p is contained in the result", result.toString().contains(expectedJSON));

        tx = new TxGetPosts(null , (long) 100);
        tx.execute();
        result = tx.getResult();
        assertTrue("p is contained in the result", result.toString().contains(expectedJSON));

        tx = new TxGetPosts(null , (long) 99);
        tx.execute();
        result = tx.getResult();
        assertFalse("p is contained in the result", result.toString().contains(expectedJSON));



        tx = new TxGetPosts((long) 1 , (long) 200);
        tx.execute();
        result = tx.getResult();
        assertTrue("p is contained in the result", result.toString().contains(expectedJSON));
        
        tx = new TxGetPosts((long) 100 , (long) 100);
        tx.execute();
        result = tx.getResult();
        assertTrue("p is contained in the result", result.toString().contains(expectedJSON));

        tx = new TxGetPosts((long) 1, (long) 99);
        tx.execute();
        result = tx.getResult();
        assertFalse("p is contained in the result", result.toString().contains(expectedJSON));

        tx = new TxGetPosts((long) 101, (long) 200);
        tx.execute();
        result = tx.getResult();
        assertFalse("p is contained in the result", result.toString().contains(expectedJSON));

    }
}

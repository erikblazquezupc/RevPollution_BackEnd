package domain.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.codehaus.jettison.json.JSONArray;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Post;
import domain.User;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.PostDataCtrl;
import domain.dataCtrl.UserDataCtrl;

public class TxGetAllPostsTest {
    Post p;
    User u;

    PostDataCtrl pdb = DataCtrl.getInstance().getPostDataCtrl();
    UserDataCtrl udb = DataCtrl.getInstance().getUserDataCtrl();


    @Before
    public void setUp() {
        u = new User("testTxGetAllPosts", "testTxGetAllPosts", "testTxGetAllPosts@test", "testTxGetAllPosts", "1234", "testTxGetAllPosts");
        u.setToken("testTxGetAllPosts");
        udb.insert(u);
        assertNotNull(u.getId());

        p = new Post(u, "testTxGetAllPosts", 100);
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
        assertTrue("p is contained in the result", result.toString().contains(p.toJSON().toString()));
    }
}

package domain.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.PostDB;
import data.UserDB;
import domain.Post;
import domain.User;
import domain.dataCtrl.PostDataCtrl;
import domain.dataCtrl.UserDataCtrl;

public class TxNewPostTest {
    Post p;
    PostDataCtrl pdb;

    User u;
    UserDataCtrl udb;

    @Before
    public void setUp() {
        u = new User("testTxNewPost", "testTxNewPost", "testTxNewPost@test", "testTxNewPost", "1234", "testTxNewPost");
        u.setToken("testTxNewPost");
        udb = UserDB.getInstance();
        udb.insert(u);
        assertNotNull(u.getId());

        pdb = PostDB.getInstance();
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
    public void testTxNewPost() {
        TxNewPost t = new TxNewPost("testTxNewPost", u.getToken());
        t.execute();
        Long ts = t.getResult();
        assertTrue("created this second or some before", ts <= System.currentTimeMillis());

        p = pdb.select(u.getId(), ts);
        assertNotNull(p);
    }
}

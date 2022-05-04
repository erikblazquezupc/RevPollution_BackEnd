package data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Post;
import domain.User;
import domain.dataCtrl.PostDataCtrl;
import domain.dataCtrl.UserDataCtrl;

public class PostDBTest {
    Post p;
    PostDataCtrl pdb;

    User u;
    UserDataCtrl udb;

    @Before
    public void setUp(){
        u = new User("testPostDB", "testPostDB", "testPostDB@test", "testPostDB", "1234", "testPostDB");
        u.setToken("testPostDB");
        udb = UserDB.getInstance();
        udb.insert(u);
        assertNotNull(u.getId());

        p = new Post(u, "testDB", 100);
        pdb = PostDB.getInstance();
        assertTrue(pdb.insert(p));
    }
    
    @After
    public void clean(){
        pdb.delete(p.getCreator().getId(), p.getPostedOn());
        p = pdb.select(p.getCreator().getId(), p.getPostedOn());
        assertNull(p);

        udb.delete(u.getId());
        u = udb.select(u.getId());
        assertNull(u);
    }

    @Test
    public void testSelect() {
        Post tmp = pdb.select(p.getCreator().getId(), p.getPostedOn());
        assertEquals(p, tmp);
    }

    @Test
    public void testSelectAll() {
        List<Post> tmp = pdb.selectAll();
        assertTrue("P contanied in List", tmp.contains(p));
    }

    @Test
    public void testSelectByDateBigger() {
        List<Post> tmp = pdb.selectByDateBigger((long) 1);
        assertTrue("P contanied in List", tmp.contains(p));
        tmp = pdb.selectByDateBigger((long) 100);
        assertTrue("P contanied in List", tmp.contains(p));
        tmp = pdb.selectByDateBigger((long) 101);
        assertFalse("P contanied in List", tmp.contains(p));
    }

    @Test
    public void testSelectByDateSmaller() {
        List<Post> tmp = pdb.selectByDateSmaller((long) 200);
        assertTrue("P contanied in List", tmp.contains(p));
        tmp = pdb.selectByDateSmaller((long) 100);
        assertTrue("P contanied in List", tmp.contains(p));
        tmp = pdb.selectByDateSmaller((long) 99);
        assertFalse("P contanied in List", tmp.contains(p));
    }

    @Test
    public void testSelectByDateBoth() {
        List<Post> tmp = pdb.selectByDateBoth((long) 1, (long) 200);
        assertTrue("P contanied in List", tmp.contains(p));
        tmp = pdb.selectByDateBoth((long) 100, (long) 100);
        assertTrue("P contanied in List", tmp.contains(p));
        tmp = pdb.selectByDateBoth((long) 1, (long) 99);
        assertFalse("P contanied in List", tmp.contains(p));
        tmp = pdb.selectByDateBoth((long) 101, (long) 200);
        assertFalse("P contanied in List", tmp.contains(p));
    }
}
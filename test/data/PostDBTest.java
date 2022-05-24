package data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Post;
import domain.User;
import domain.dataCtrl.PostDataCtrl;
import domain.dataCtrl.UserDataCtrl;

public class PostDBTest {
    Post p;
    User u;
    
    PostDataCtrl pdb = PostDB.getInstance();
    UserDataCtrl udb = UserDB.getInstance();

    @Before
    public void setUp(){
        u = new User("testPostDB", "testPostDB", "testPostDB@test", "testPostDB", "1234", "testPostDB");
        u.setToken("testPostDB");
        udb.insert(u);
        assertNotNull(u.getId());

        p = new Post(u, "testDB", 100);
        assertTrue(pdb.insert(p));
    }
    
    @After
    public void clean(){
        pdb.delete(p.getCreator().getId(), p.getPostedOn());
        assertNull(pdb.select(p.getCreator().getId(), p.getPostedOn()));

        udb.delete(u.getId());
        assertNull(udb.select(u.getId()));
    }

    @Test
    public void testSelect() {
        Post tmp = pdb.select(p.getCreator().getId(), p.getPostedOn());
        assertEquals(p, tmp);
    }

    @Test
    public void testSelectAll() {
        ArrayList<Post> tmp = pdb.selectAll();
        assertTrue(tmp.contains(p));
    }

    @Test
    public void testSelectByDateBigger() {
        ArrayList<Post> tmp = pdb.selectByDateBigger((long) 1);
        assertTrue(tmp.contains(p));
        
        tmp = pdb.selectByDateBigger((long) 100);
        assertTrue(tmp.contains(p));

        tmp = pdb.selectByDateBigger((long) 101);
        assertFalse(tmp.contains(p));
    }

    @Test
    public void testSelectByDateSmaller() {
        ArrayList<Post> tmp = pdb.selectByDateSmaller((long) 200);
        assertTrue(tmp.contains(p));

        tmp = pdb.selectByDateSmaller((long) 100);
        assertTrue(tmp.contains(p));

        tmp = pdb.selectByDateSmaller((long) 99);
        assertFalse(tmp.contains(p));
    }

    @Test
    public void testSelectByDateBoth() {
        ArrayList<Post> tmp = pdb.selectByDateBoth((long) 1, (long) 200);
        assertTrue(tmp.contains(p));

        tmp = pdb.selectByDateBoth((long) 100, (long) 100);
        assertTrue(tmp.contains(p));

        tmp = pdb.selectByDateBoth((long) 1, (long) 99);
        assertFalse(tmp.contains(p));

        tmp = pdb.selectByDateBoth((long) 101, (long) 200);
        assertFalse(tmp.contains(p));
    }
}
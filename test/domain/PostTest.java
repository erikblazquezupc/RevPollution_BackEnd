package domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PostTest {
    Post p;

    @Before
    public void setUp(){
        p = new Post(new User(1, "testPost", "testPost", "testPost@prueba", "testPost", "testPost", "testPost"), "test", 1);
    }

    @Test
    public void testGetCreator() {
        assertEquals(new User(1, "testPost", "testPost", "testPost@prueba", "testPost", "testPost", "testPost"), p.getCreator());
    }

    @Test
    public void testGetText() {
        assertEquals("test", p.getText());;
    }

    @Test
    public void testGetPostedOn() {
        assertEquals(1, p.getPostedOn());
    }

    @Test
    public void testToString() {
        assertEquals("Post [creator=" + new User(1, "testPost", "testPost", "testPost@prueba", "testPost", "testPost", "testPost").toString() + ", postedOn=" + "1" + ", text=" + "test" + "]", p.toString());
    }
}

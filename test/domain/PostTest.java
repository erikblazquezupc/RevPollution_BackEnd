package domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PostTest {
    Post p;
    User u;

    @Before
    public void setUp(){
        u = new User(1, "testPost", "testPost", "testPost@prueba", "testPost", "testPost", "testPost");
        p = new Post(u, "test", 1);
    }

    @Test
    public void testGetCreator() {
        assertEquals(u, p.getCreator());
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
        assertEquals("Post [creator=" + u + ", postedOn=" + "1" + ", text=" + "test" + "]", p.toString());
    }

    @Test
    public void testToJSON(){
        String expected = "{\"username\":\"testPost\",\"profilepic\":\"testPost\",\"text\":\"test\",\"timestamp\":1}";
        assertEquals(expected, p.toJSON().toString());
    }
}

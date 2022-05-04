package domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PostTest {
    Post p;

    @Before
    public void setUp(){
        p = new Post(new User(1, "username", "name", "email", "password", "123", "img"), "test", 1);
    }

    @Test
    public void testGetCreator() {
        assertEquals(new User(1, "username", "name", "email", "password", "123", "img"), p.getCreator());
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
        assertEquals("Post [creator=" + new User(1, "username", "name", "email", "password", "123", "img").toString() + ", postedOn=" + "1" + ", text=" + "test" + "]", p.toString());
    }
}

package domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
    User u;
    @Before
    public void setUp(){
        u = new User(1, "username", "name", "email", "password", "123", "img");
    }

    @Test
    public void testGetEmail() {
        assertEquals("email", u.getEmail());
    }

    @Test
    public void testGetId() {
        assertEquals(1, u.getId());
    }

    @Test
    public void testGetImg() {
        assertEquals("img", u.getImg());
    }

    @Test
    public void testGetName() {
        assertEquals("name", u.getName());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password", u.getPassword());
    }

    @Test
    public void testGetTel() {
        assertEquals("123", u.getTel());
    }

    @Test
    public void testGetUsername() {
        assertEquals("username", u.getUsername());
    }

    @Test
    public void testSetEmail() {
        u.setEmail("a@gmail.com");
        assertEquals("a@gmail.com", u.getEmail());
    }

    @Test
    public void testSetId() {
        u.setId(2);
        assertEquals(2, u.getId());
    }

    @Test
    public void testSetImg() {
        u.setImg("img.png");
        assertEquals("img.png", u.getImg());
    }

    @Test
    public void testSetName() {
        u.setName("Paco");
        assertEquals("Paco", u.getName());
    }

    @Test
    public void testSetPassword() {
        u.setPassword("123456789");
        assertEquals("123456789", u.getPassword());
    }

    @Test
    public void testSetTel() {
        u.setTel("987654321");
        assertEquals("987654321", u.getTel());
    }

    @Test
    public void testSetUsername() {
        u.setUsername("Pacolo");
        assertEquals("Pacolo", u.getUsername());
    }

    @Test
    public void testSetToken(){
        u.setToken("ads6asc");
        assertEquals("ads6asc", u.getToken());
    }

    @Test
    public void testToString() {
        String expected = "User [id=1, username=username, name=name, email=email, password=password, tel=123, img=img, token=null]";
        assertEquals(expected, u.toString());
    }
}

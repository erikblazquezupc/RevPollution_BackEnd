package data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.User;
import domain.Search;
import domain.StationStub;

import java.util.ArrayList;
import java.util.Date;

public class SearchesDBTest {
    Search s;
    User u;
    StationStub stat;
    SearchDB sdb;
    UserDB udb;
    StationDB ssdb;

    @Before
    public void setUp(){
        
    }
    
    @After
    public void clean(){
    }
 
    @Test
    public void testSelectRecent() {
    }

    @Test
    public void testSelect() {
    }
}

package domain.controllers;

import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.User;
import domain.Search;

import java.util.Date;

import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;
import domain.dataCtrl.SearchDataCtrl;

public class TxDeleteSearchTest {
    Search s;
    User u;
    String name;

    DataCtrl dc = DataCtrl.getInstance();
    SearchDataCtrl sdc = dc.getSearchDataCtrl();
    UserDataCtrl udc = dc.getUserDataCtrl();

    @Before
    public void setUp(){
        u = new User("username", "name", "email@prueba", "password", "tel", "img");
        u.setToken("token");
        udc.insert(u);

        name = "Calafell";

        Date d = new Date(1650837600);
        s = new Search(u, name, d);
        sdc.insert(s);
    }
    
    @After
    public void clean(){
        sdc.delete(u.getId(), name);
        udc.delete(u.getId());
    }

    @Test
    public void testTxDeleteSearch() {
        TxDeleteSearch tx = new TxDeleteSearch("token", name);
        tx.execute();
        s = sdc.select(u.getId(), name);
        assertNull(s);
    }
}

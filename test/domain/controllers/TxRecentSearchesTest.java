package domain.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.User;
import domain.Search;
import domain.StationStub;

import java.util.Date;

import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;
import domain.dataCtrl.SearchDataCtrl;
import domain.dataCtrl.StationDataCtrl;

public class TxRecentSearchesTest {
    Search s;
    User u;
    StationStub stat;

    DataCtrl dc = DataCtrl.getInstance();
    SearchDataCtrl sdc = dc.getSearchDataCtrl();
    UserDataCtrl udc = dc.getUserDataCtrl();
    StationDataCtrl ssdc = dc.getStationDataCtrl();

    @Before
    public void setUp(){
        u = new User("username", "name", "email@prueba", "password", "tel", "img");
        u.setToken("token");
        udc.insert(u);

        stat = new StationStub("Stub1", "a", 0, 0);
        ssdc.insert(stat);

        Date d = new Date(1650837600);
        s = new Search(u, stat, d);
        sdc.insert(s);
    }
    
    @After
    public void clean(){
        sdc.delete(u.getId(), stat.getId());
        ssdc.delete(stat.getId());
        udc.delete(u.getId());
    }

    @Test
    public void testTxRecentSearches() {
        TxRecentSearches tx = new TxRecentSearches("token");
        tx.execute();
        s = sdc.select(u.getId(), stat.getId());
        assertNotNull(s);
        assertEquals(tx.getResult().get(0), s);
    }
}

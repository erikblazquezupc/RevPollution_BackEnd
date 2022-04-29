package domain.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

public class TxAddSearchTest {
    Search s;
    User u;
    StationStub stat;

    DataCtrl dc = DataCtrl.getInstance();
    SearchDataCtrl sdc = dc.getSearchDataCtrl();
    UserDataCtrl udc = dc.getUserDataCtrl();
    StationDataCtrl ssdc = dc.getStationDataCtrl();

    @Before
    public void setUp(){
    }
    
    @After
    public void clean(){
    } 

    @Test
    public void testTxAddSearch() {
    }
}

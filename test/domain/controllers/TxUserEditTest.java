package domain.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.User;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;

public class TxUserEditTest {
    User u;
    UserDataCtrl udc;

    @Before
    public void setUp(){
        u = new User(0,"usernametx", "nametx", "emailtx", "passwordtx", "telftx", "imagetx");
        u.setToken("token");
        DataCtrl dataCtrl = DataCtrl.getInstance();
        udc = dataCtrl.getUserDataCtrl();
        udc.insert(u);
    }
    
    @After
    public void clean(){
        udc.delete(u.getId());
    }

    @Test
    public void testTxUserEdit() {
        TxUserEdit tx = new TxUserEdit("usernametxchanged", "nametx", "passwordtx", "emailtx", "telftx", "imagetx", "token");
        tx.execute();
        //assertTrue(tx.getResult());
        u = udc.selectByToken("token");
        assertEquals(u.getUsername(), "usernametxchanged");
    }
}
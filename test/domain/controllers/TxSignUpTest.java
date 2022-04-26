package domain.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.User;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;

public class TxSignUpTest {
    User u;
    UserDataCtrl udc;

    @Before
    public void setUp(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        udc = dataCtrl.getUserDataCtrl();
    }
    
    @After
    public void clean(){
        udc.delete(u.getId());
    }

    @Test
    public void testTxSignUp() {
        TxSignUp tx = new TxSignUp("usernametx", "nametx", "passwordtx", "emailtx", "telftx", "imagetx");
        tx.execute();
        assertTrue(tx.getResult());
        u = udc.selectByUsername("usernametx");
        assertNotNull(u);
    }
}

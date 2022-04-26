package domain.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.User;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;

public class TxLogInTest {
    User u;
    UserDataCtrl udc;

    @Before
    public void setUp(){
        u = new User("username", "name", "email@prueba", "password", "tel", "img");
        DataCtrl dataCtrl = DataCtrl.getInstance();
        udc = dataCtrl.getUserDataCtrl();
        udc.insert(u);
    }
    
    @After
    public void clean(){
        udc.delete(u.getId());
    }

    @Test
    public void testTxLogIn() {
        TxLogIn tx = new TxLogIn("username", "password");
        tx.execute();
        assertNotNull(tx.getResult());

    }

    @Test
    public void testTxLogInTestFail() {
        TxLogIn tx = new TxLogIn("username", "");
        tx.execute();
        assertNull(tx.getResult());
        
    }
}

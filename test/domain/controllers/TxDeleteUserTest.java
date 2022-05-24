package domain.controllers;

import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.User;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;

public class TxDeleteUserTest {
    User u;
    UserDataCtrl udc;

    @Before
    public void setUp() {
        u = new User("txusername", "txname", "txemail@email.com", "txpwd", "txtel", "tximg");
        u.setToken("txtoken");
        udc = DataCtrl.getInstance().getUserDataCtrl();
        udc.insert(u);
    }

    @Test
    public void testTxDeleteUser(){
        TxUserDelete tx = new TxUserDelete(Integer.toString(u.getId()));
        tx.execute();
        u = udc.select(u.getId());
        assertNull(u);
    }
}

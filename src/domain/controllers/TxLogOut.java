package domain.controllers;

import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;

public class TxLogOut {
    private String username;

    public TxLogOut(String username){
        this.username = username;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        UserDataCtrl userCtrl = dataCtrl.getUserDataCtrl();
        userCtrl.deleteToken(username);
    }
}

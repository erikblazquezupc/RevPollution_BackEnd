package domain.controllers;

import domain.User;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;


public class TxUserInfo {
    private String token;
    private User result;

    public TxUserInfo(String token){
        this.token = token;
        this.result = null;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        UserDataCtrl userCtrl = dataCtrl.getUserDataCtrl();
        User u = userCtrl.selectByToken(token);
        result = u;
    }

    public User getResult(){
        return result;
    }
}
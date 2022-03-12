package domain.controllers;

import domain.User;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;

public class TxLogIn {
    private String username;
    private String password;
    private boolean result;

    public TxLogIn(String username, String password){
        this.username = username;
        this.password = password;
        this.result = false;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        UserDataCtrl userCtrl = dataCtrl.getUserDataCtrl();
        User u = userCtrl.selectByUsername(username);
        if(u == null) return;
        if(!u.getPassword().equals(password)) return;
        result = true;
    }

    public boolean getResult(){
        return result;
    }
}

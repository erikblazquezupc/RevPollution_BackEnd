package domain.controllers;

import java.util.ArrayList;

import domain.User;
import domain.UserLogro;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;
import domain.dataCtrl.UserLogroDataCtrl;

public class TxGetUserLogros {
    private String username;
    private ArrayList<UserLogro> result;

    public TxGetUserLogros(String username){
        this.username = username;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        UserLogroDataCtrl uldc = dataCtrl.getUserLogroDataCtrl();
        UserDataCtrl udc = dataCtrl.getUserDataCtrl();
        User u = udc.selectByUsername(username);
        result = uldc.selectByUser(u.getId());
    }

    public ArrayList<UserLogro> getResult(){
        return result;
    }
}

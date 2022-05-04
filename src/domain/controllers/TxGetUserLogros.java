package domain.controllers;

import java.util.ArrayList;

import domain.StationStub;
import domain.User;
import domain.UserLogro;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.StationDataCtrl;
import domain.dataCtrl.UserDataCtrl;
import domain.dataCtrl.UserLogroDataCtrl;

public class TxGetUserLogros {
    private String token;
    private ArrayList<UserLogro> result;

    public TxGetUserLogros(String token){
        this.token = token;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        UserLogroDataCtrl uldc = dataCtrl.getUserLogroDataCtrl();
        UserDataCtrl udc = dataCtrl.getUserDataCtrl();
        User u = udc.selectByToken(token);
        result = uldc.selectByUser(u.getId());
    }

    public ArrayList<UserLogro> getResult(){
        return result;
    }
}

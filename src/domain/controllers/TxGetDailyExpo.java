package domain.controllers;

import java.util.ArrayList;

import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.ExpoDataCtrl;
import domain.dataCtrl.UserDataCtrl;
import domain.User;
import domain.Expo;

public class TxGetDailyExpo {
    private String token;
    private ArrayList<Expo> result;

    public TxGetDailyExpo(String token){
        this.token = token;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        ExpoDataCtrl edc = dataCtrl.getExpoDataCtrl();
        UserDataCtrl udc = dataCtrl.getUserDataCtrl();
        User u = udc.selectByToken(token);
        result = edc.selectRecent(u.getId());
    }

    public ArrayList<Expo> getResult(){
        return result;
    }
}

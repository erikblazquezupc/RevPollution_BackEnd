package domain.controllers;

import java.util.ArrayList;

import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.DailyExpositionDataCtrl;
import domain.dataCtrl.UserDataCtrl;
import domain.User;
import domain.DailyExposition;

public class TxGetDailyExpo {
    private String token;
    private ArrayList<DailyExposition> result; 

    public TxGetDailyExpo(String token){
        this.token = token;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        DailyExpositionDataCtrl edc = dataCtrl.getDailyExpositionDataCtrl();
        UserDataCtrl udc = dataCtrl.getUserDataCtrl();
        User u = udc.selectByToken(token);
        result = edc.selectRecent(u.getId());
    }

    public ArrayList<DailyExposition> getResult(){
        return result;
    }
}

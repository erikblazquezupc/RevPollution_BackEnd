package domain.controllers;

import domain.User;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;

public class TxSignUpPic {
    private String username;
    private String image;

    private boolean result;

    public TxSignUpPic(String username, String image){
        this.username = username;
        this.image = image;
        this.result = false;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        UserDataCtrl userCtrl = dataCtrl.getUserDataCtrl();
        userCtrl.setPic(username, image);
        result = true;
    }

    public boolean getResult(){
        return result;
    }
}

package domain.controllers;

import domain.User;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;

public class TxSignUp {
    private String name;
    private String username;
    private String password;
    private String email;
    private String telf;
    private String image;

    private boolean result;

    public TxSignUp(String username, String name, String password,
        String email, String telf, String image){
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.telf = telf;
        this.image = image;
        this.result = false;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        UserDataCtrl userCtrl = dataCtrl.getUserDataCtrl();
        User u = new User(username, name, email, password, telf, image);
        userCtrl.insert(u);
        result = true;
    }

    public boolean getResult(){
        return result;
    }
}

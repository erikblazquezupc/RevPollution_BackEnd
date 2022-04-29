package domain.controllers;

import domain.User;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;

public class TxUserEdit {
    private String name;
    private String username;
    private String password;
    private String email;
    private String telf;
    private String image;
    private String token;

    private boolean result;

    public TxUserEdit(String username, String name, String password,
        String email, String telf, String image, String token){
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.telf = telf;
        this.image = image;
        this.token = token;
        this.result = false;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        UserDataCtrl userCtrl = dataCtrl.getUserDataCtrl();
        /*User u = userCtrl.selectByToken(token);
        u.setEmail(email);
        u.setImg(image);
        u.setName(name);
        u.setPassword(password);
        u.setTel(telf);
        u.setUsername(username);*/
        User u = new User(username, name, email, password, telf, image);
        u.setToken(token);
        userCtrl.editInfo(u);
    }

    public boolean getResult(){
        return result;
    }
}

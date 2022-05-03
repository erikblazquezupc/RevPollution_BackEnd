package domain.controllers;

import domain.Post;
import domain.User;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.PostDataCtrl;
import domain.dataCtrl.UserDataCtrl;

public class TxNewPost {
    String text;
    String userToken;

    Boolean result;

    public TxNewPost(String text, String userToken) {
        this.text = text;
        this.userToken = userToken;
        this.result = false;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        UserDataCtrl udc = dataCtrl.getUserDataCtrl();
        PostDataCtrl pdc = dataCtrl.getPostDataCtrl();
        
        User u = udc.selectByToken(userToken);
        Post p = new Post(u, text);
        pdc.insert(p);
        result = true;
    }

    public boolean getResult(){
        return result;
    }
}

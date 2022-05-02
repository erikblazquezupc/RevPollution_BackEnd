package domain.controllers;

import domain.User;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;
import domain.dataCtrl.SearchDataCtrl;

public class TxDeleteSearch {
    private String token;
    private String name; 

    public TxDeleteSearch(String token, String name){
        this.token = token;
        this.name = name;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        UserDataCtrl userCtrl = dataCtrl.getUserDataCtrl();
        User user = userCtrl.selectByToken(token);

        SearchDataCtrl recentSearchesCtrl = dataCtrl.getSearchDataCtrl();
        recentSearchesCtrl.delete(user.getId(), name);
    }
}

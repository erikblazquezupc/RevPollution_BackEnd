package domain.controllers;

import domain.User;
import domain.Search;

import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;
import domain.dataCtrl.SearchDataCtrl;

import java.util.Date;

public class TxAddSearch {
    private String token; 
    private String name;
    private Date instant;
    private boolean result;

    public TxAddSearch(String token, String name, Date instant){
        this.token = token;
        this.name = name;
        this.instant = instant;
        this.result = false;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        UserDataCtrl userCtrl = dataCtrl.getUserDataCtrl();

        User user = userCtrl.selectByToken(token);
        Search search = new Search(user, name, instant);

        SearchDataCtrl recentSearchesCtrl = dataCtrl.getSearchDataCtrl();
        result = recentSearchesCtrl.insert(search);
    }

    public boolean getResult(){
        return result;
    }
}

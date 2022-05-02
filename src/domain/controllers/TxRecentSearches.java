package domain.controllers;

import domain.User;
import domain.Search;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;
import domain.dataCtrl.SearchDataCtrl;

import java.util.ArrayList;

public class TxRecentSearches { 
    private String token;
    private ArrayList<Search> result;

    public TxRecentSearches(String token){
        this.token = token;
        this.result = null;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        UserDataCtrl userCtrl = dataCtrl.getUserDataCtrl();
        User u = userCtrl.selectByToken(token);
        SearchDataCtrl recentSearchesCtrl = dataCtrl.getSearchDataCtrl();
        result = recentSearchesCtrl.selectRecent(u.getId());
    }

    public ArrayList<Search> getResult(){
        return result;
    }
}

package domain.controllers;

import domain.User;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;
import domain.dataCtrl.SearchDataCtrl;

public class TxDeleteSearch {
    private String token;
    private Integer idStation;

    public TxDeleteSearch(String token, int idStation){
        this.token = token;
        this.idStation = idStation;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        UserDataCtrl userCtrl = dataCtrl.getUserDataCtrl();
        User user = userCtrl.selectByToken(token);

        SearchDataCtrl recentSearchesCtrl = dataCtrl.getSearchDataCtrl();
        recentSearchesCtrl.delete(user.getId(), idStation);
    }
}

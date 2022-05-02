package domain.controllers;

import domain.User;
import domain.Search;
import domain.StationStub;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;
import domain.dataCtrl.SearchDataCtrl;
import domain.dataCtrl.StationDataCtrl;

import java.util.Date;

public class TxAddSearch {
    private String token;
    private Integer idStation;
    private Date instant;
    private boolean result;

    public TxAddSearch(String token, int idStation, Date instant){
        this.token = token;
        this.idStation = idStation;
        this.instant = instant;
        this.result = false;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        UserDataCtrl userCtrl = dataCtrl.getUserDataCtrl();
        StationDataCtrl stationCtrl = dataCtrl.getStationDataCtrl();

        User user = userCtrl.selectByToken(token);
        StationStub station = stationCtrl.select(idStation);

        Search search = new Search(user, station, instant);

        SearchDataCtrl recentSearchesCtrl = dataCtrl.getSearchDataCtrl();
        result = recentSearchesCtrl.insert(search);
    }

    public boolean getResult(){
        return result;
    }
}

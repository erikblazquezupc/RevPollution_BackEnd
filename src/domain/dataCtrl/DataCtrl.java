package domain.dataCtrl;

import data.StationDB;
import data.UserDB;
import data.UserLogroDB;
import data.ConcentrationDB;
import data.LogroDB;
import data.SearchDB;
import data.ExpoDB;
import data.ParticleDB;

public class DataCtrl{
    static DataCtrl instance;

    public static DataCtrl getInstance(){
        if(instance == null) instance = new DataCtrl();
        return instance;
    }

    public UserDataCtrl getUserDataCtrl(){
        return UserDB.getInstance();
    }

    public SearchDataCtrl getSearchDataCtrl(){
        return SearchDB.getInstance();
    }

    public StationDataCtrl getStationDataCtrl(){
        return StationDB.getInstance();
    }

    public ParticleDataCtrl getParticleDataCtrl(){
        return ParticleDB.getInstance();
    }

    public ConcentrationDataCtrl getConcentrationDataCtrl(){
        return ConcentrationDB.getInstance();
    }

    public LogroDataCtrl getLogroDataCtrl() {
        return LogroDB.getInstance();
    }
    
    public ExpoDataCtrl getExpoDataCtrl(){
        return ExpoDB.getInstance();
    }

    public UserLogroDataCtrl getUserLogroDataCtrl(){
        return UserLogroDB.getInstance();
    }
}
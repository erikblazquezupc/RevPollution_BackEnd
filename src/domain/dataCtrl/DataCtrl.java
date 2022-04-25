package domain.dataCtrl;

import data.StationDB;
import data.UserDB;
import data.ConcentrationDB;
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

    public StationDataCtrl getStationDataCtrl(){
        return StationDB.getInstance();
    }

    public ParticleDataCtrl getParticleDataCtrl(){
        return ParticleDB.getInstance();
    }

    public ConcentrationDataCtrl getConcentrationDataCtrl(){
        return ConcentrationDB.getInstance();
    }
}
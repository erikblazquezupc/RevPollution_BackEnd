package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SystemState {

    static SystemState instance;
    Map<Integer, ArrayList<Quality>> stationPastQualities;
    Map<Integer, ArrayList<Quality>> stationFutureQualities;

    SystemState(){
        stationPastQualities = new HashMap<Integer,ArrayList<Quality>>();
        stationFutureQualities = new HashMap<Integer,ArrayList<Quality>>();
    }

    static public SystemState getInstance(){
        if(instance == null) instance = new SystemState();
        return instance;
    }

    public ArrayList<Quality> getStationPastQualities(int id){
        return stationPastQualities.get(id);
    }

    public void addStationPastQualities(int id, ArrayList<Quality> array){
        stationPastQualities.putIfAbsent(id, array);
    }

    public void deleteStationPastQualities(int id){
        stationPastQualities.remove(id);
    }

    public boolean existsStationPastQuality(int idStation) {
        return stationPastQualities.containsKey(idStation);
    }

    public ArrayList<Quality> getStationFutureQualities(int id){
        return stationFutureQualities.get(id);
    }

    public void addStationFutureQualities(int id, ArrayList<Quality> array){
        stationFutureQualities.putIfAbsent(id, array);
    }

    public void deleteStationFutureQualities(int id){
        stationFutureQualities.remove(id);
    }

    public boolean existsStationFutureQuality(int idStation) {
        return stationFutureQualities.containsKey(idStation);
    }

}

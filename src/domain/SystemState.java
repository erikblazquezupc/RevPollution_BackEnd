package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SystemState {

    static SystemState instance;
    Map<Integer, Pair<Date, ArrayList<Quality>>> stationPastQualities;
    Map<Integer, Pair<Date, ArrayList<Quality>>> stationFutureQualities;

    SystemState(){
        stationPastQualities = new HashMap<Integer,Pair<Date, ArrayList<Quality>>>();
        stationFutureQualities = new HashMap<Integer,Pair<Date, ArrayList<Quality>>>();
    }

    static public SystemState getInstance(){
        if(instance == null) instance = new SystemState();
        return instance;
    }

    // PAST QUALITIES

    public ArrayList<Quality> getStationPastQualities(int id){
        return stationPastQualities.get(id).getSecond();
    }
    
    public void addStationPastQualities(int id, ArrayList<Quality> array){
        stationPastQualities.put(id, new Pair<Date, ArrayList<Quality>>(new Date(), array));
    }

    public void deleteStationPastQualities(int id){
        stationPastQualities.remove(id);
    }
    
    public boolean existsStationPastQuality(int id) {
        return stationPastQualities.containsKey(id);
    }
    
    public Date getPastLastChangeDate(int id){
        if(!existsStationPastQuality(id)) return null;
        return stationPastQualities.get(id).getFirst();
    }

    // FUTURE QUALITIES
    
    public ArrayList<Quality> getStationFutureQualities(int id){
        return stationFutureQualities.get(id).getSecond();
    }
    
    public void addStationFutureQualities(int id, ArrayList<Quality> array){
        stationFutureQualities.put(id, new Pair<Date, ArrayList<Quality>>(new Date(), array));
    }

    public void deleteStationFutureQualities(int id){
        stationFutureQualities.remove(id);
    }
    
    public boolean existsStationFutureQuality(int id) {
        return stationFutureQualities.containsKey(id);
    }
    
    public Date getFutureLastChangeDate(int id){
        if(!existsStationFutureQuality(id)) return null;
        return stationFutureQualities.get(id).getFirst();
    }
}

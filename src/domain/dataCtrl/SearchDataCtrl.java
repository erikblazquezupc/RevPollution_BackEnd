package domain.dataCtrl;

import domain.Search;

import java.util.ArrayList;

public interface SearchDataCtrl {
    public boolean insert(Search s);
    public Search select(int userId, int stationId);
    public void delete(int userId, int stationId);
    public ArrayList<Search> selectRecent (int userId);
}
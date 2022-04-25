package domain.dataCtrl;

import domain.Search;

import java.util.ArrayList;

public interface SearchDataCtrl {
    public boolean insert(Search s);
    public void delete(int stationId, int userId);
    public ArrayList<Search> selectRecent (int userId);
}
package domain.dataCtrl;

import domain.Search;

import java.util.ArrayList;

public interface SearchDataCtrl {
    public boolean insert(Search s);
    public Search select(int userId, String name);
    public void delete(int userId, String name);
    public ArrayList<Search> selectRecent (int userId);
} 
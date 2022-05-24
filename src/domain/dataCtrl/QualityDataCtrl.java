package domain.dataCtrl;

import domain.Quality;

import java.util.ArrayList;

public interface QualityDataCtrl {
    public ArrayList<Quality> selectPastHours(int stationId);
    public ArrayList<Quality> selectPast(int stationId);
    public ArrayList<Quality> selectFutureHours(int stationId);
    public ArrayList<Quality> selectFuture(int stationId);
}

package domain.dataCtrl;

import domain.DailyExposition;
import java.sql.Date;
import java.util.ArrayList;

public interface DailyExpositionDataCtrl {
    public ArrayList<DailyExposition> selectRecent(int idUser);
    public boolean insert(int idUser, Date dat, double value);
    public boolean delete(int idUser);
}

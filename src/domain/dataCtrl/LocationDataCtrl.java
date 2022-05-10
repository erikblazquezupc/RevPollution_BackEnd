package domain.dataCtrl;

import domain.Location;
import java.sql.Date;
import java.util.ArrayList;

public interface LocationDataCtrl {
    public boolean insert(int idUser, Date dat, double value);
    public boolean delete(int idUser);
    public ArrayList<Location> select(int idUser);
}

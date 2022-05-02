package domain.dataCtrl;

import domain.Expo;
import java.util.ArrayList;

public interface ExpoDataCtrl {
    public ArrayList<Expo> selectRecent(int idUser);
    public boolean insert(int idUser, double value);
    public void delete(int idUser);
    public boolean select(int idUser);
}

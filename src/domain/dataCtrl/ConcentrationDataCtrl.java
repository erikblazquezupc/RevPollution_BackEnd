package domain.dataCtrl;

import domain.Concentration;

import java.util.Date;

public interface ConcentrationDataCtrl {
    public boolean insert(Concentration c);
    public void delete(int stationId, String particleName, Date d);
    public void update(Concentration c);
    public Concentration select(int stationId, String particleName, Date d);
    public Concentration selectMostRecentFromStation(int stationId);
}

package domain.dataCtrl;

import domain.Concentration;
import domain.StationStub;
import domain.Particle;

import java.util.Date;

import java.util.ArrayList;

public interface ConcentrationDataCtrl {
    public boolean insert(Concentration c);
    public void delete(StationStub s, Particle p, Date d);
    public void update(Concentration c);
    public Concentration select(StationStub s, Particle p, Date d);
    public ArrayList<Concentration> selectMostRecentFromStation(StationStub s);
}

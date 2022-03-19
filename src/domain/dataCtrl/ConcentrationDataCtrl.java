package domain.dataCtrl;

import domain.Concentration;
import domain.StationStub;
import domain.Particle;

import java.util.Date;

public interface ConcentrationDataCtrl {
    public void insert(Concentration c);
    public void delete(StationStub s, Particle p, Date d);
    public void update(Concentration c);
    public Concentration select(StationStub s, Particle p, Date d);
}

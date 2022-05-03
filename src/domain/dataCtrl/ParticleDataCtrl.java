package domain.dataCtrl;

import java.util.ArrayList;

import domain.Particle;

public interface ParticleDataCtrl {
    public boolean insert(Particle p);
    public void delete(String name);
    public void update(Particle p);
    public Particle select(String name);
    public ArrayList<Particle> selectAll();
}

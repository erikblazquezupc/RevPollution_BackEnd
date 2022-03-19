package domain.dataCtrl;

import domain.Particle;

public interface ParticleDataCtrl {
    public void insert(Particle p);
    public void delete(String name);
    public void update(Particle p);
    public Particle select(String name);
}

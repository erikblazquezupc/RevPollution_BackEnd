package domain.dataCtrl;

import java.util.ArrayList;

import domain.Logro;
import domain.Logro.Tier;

public interface LogroDataCtrl {
    public boolean insert(Logro l);
    public void delete(String name, Tier tier);
    public void update(Logro l);
    public Logro select(String name, Tier tier);
    public ArrayList<Logro> selectAll();
    //public Logro selectByName(String name);
    public Logro selectAdmin(String name, Tier tier);
    public ArrayList<Logro> selectAllAdmin();
    public int switchActivation(String name, Tier tier);
}
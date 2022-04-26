package domain.dataCtrl;

import java.util.ArrayList;

import domain.Logro;

public interface LogroDataCtrl {
    public void insert(Logro l);
    public void delete(String name, Tier tier);
    public void update(Logro l);
    public Logro select(String name, Tier tier);
    public ArrayList<Logro> selectAll();
    //public Logro selectByName(String name);
}
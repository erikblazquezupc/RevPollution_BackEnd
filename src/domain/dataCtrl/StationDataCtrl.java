package domain.dataCtrl;

import domain.StationStub;

public interface StationDataCtrl {
    public void insert(StationStub s);
    public void delete(int id);
    public void update(StationStub s);
    public StationStub select(int id);
    public StationStub selectByName(String s);
}

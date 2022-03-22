package domain.dataCtrl;

import domain.User;

public interface UserDataCtrl {
    public boolean insert(User u);
    public void delete(int id);
    public void update(User u);
    public User select(int id);
    public User selectByUsername(String un);
    public User selectByToken(String Token);
}

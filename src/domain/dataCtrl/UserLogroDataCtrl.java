package domain.dataCtrl;

import java.util.ArrayList;

import domain.UserLogro;

public interface UserLogroDataCtrl {
    public UserLogro select(int idUser, String name, String tier);
    public ArrayList<UserLogro> selectByUser(int idUser);
    public ArrayList<UserLogro> selectAll();
    public boolean insert(UserLogro ul);
    public boolean update(UserLogro ul);
    public void delete(int idUser, String name, String tier);
}

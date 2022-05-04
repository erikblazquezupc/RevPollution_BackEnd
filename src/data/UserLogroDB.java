package data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.Logro;
import domain.User;
import domain.UserLogro;
import domain.Logro.Tier;
import domain.dataCtrl.UserLogroDataCtrl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserLogroDB implements UserLogroDataCtrl {
    private static UserLogroDB instance;
    Connection conn;
    PreparedStatement select;
    PreparedStatement selectByUser;
    PreparedStatement insert;
    PreparedStatement update;
    PreparedStatement delete;
    PreparedStatement selectAll;

    private UserLogroDB(){
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            select = conn.prepareStatement("SELECT * FROM UserLogros WHERE idUser = ? AND nameLogro = ? AND tier = ?");
            selectByUser = conn.prepareStatement("SELECT * FROM UserLogros WHERE idUser = ?");
            selectAll = conn.prepareStatement("SELECT * FROM UserLogros");
            insert = conn.prepareStatement("INSERT INTO UserLogros (idUser, nameLogro, tier) VALUES (?, ?, ?)");
            update = conn.prepareStatement("UPDATE UserLogros SET idUser = ?, nameLogro = ?, tier = ? WHERE idUser = ? AND nameLogro = ? AND tier = ?");
            delete = conn.prepareStatement("DELETE FROM UserLogros WHERE idUser = ? AND nameLogro = ? AND tier = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public UserLogroDB getInstance(){
        if(instance == null) instance = new UserLogroDB();
        return instance;
    }

    public UserLogro select(int idUser, String name, String tier){
        try {
            select.setInt(1, idUser);
            select.setString(2, name);
            select.setString(3, tier);
            ResultSet r = select.executeQuery();
            while (r.next()) {
                User u = UserDB.getInstance().select(idUser);
                Logro l = LogroDB.getInstance().select(name, Tier.valueOf(tier));
                UserLogro ul = new UserLogro(u, l);
                return ul;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<UserLogro> selectByUser(int idUser){
        ArrayList<UserLogro> ret = new ArrayList<UserLogro> ();
        try {
            selectByUser.setInt(1, idUser);
            ResultSet r = selectByUser.executeQuery();
            while (r.next()) {
                String name = r.getString("nameLogro");
                Tier tier = Tier.valueOf(r.getString("tier"));
                User u = UserDB.getInstance().select(idUser);
                Logro l = LogroDB.getInstance().select(name, tier);
                UserLogro ul = new UserLogro(u, l);
                ret.add(ul);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public ArrayList<UserLogro> selectAll(){
        ArrayList<UserLogro> ret = new ArrayList<UserLogro>();
        try {
            ResultSet r = selectAll.executeQuery();
            if (r.next()) {
                int idUser = r.getInt("idUser");
                String name = r.getString("nameLogro");
                Tier tier = Tier.valueOf(r.getString("tier"));
                User u = UserDB.getInstance().select(idUser);
                Logro l = LogroDB.getInstance().select(name, tier);
                UserLogro ul = new UserLogro(u, l);
                ret.add(ul);
            }
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public boolean insert(UserLogro ul){
        try {
            insert.setInt(1, ul.getUserId());
            insert.setString(2, ul.getLogroName());
            insert.setString(3, ul.getLogroTier());
            int n = insert.executeUpdate();
            if (n >= 1) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean update(UserLogro ul){
        try {
            update.setInt(1, ul.getUserId());
            update.setString(2, ul.getLogroName());
            update.setString(3, ul.getLogroTier());
            update.setInt(4, ul.getUserId());
            update.setString(5, ul.getLogroName());
            update.setString(6, ul.getLogroTier());
            int n = update.executeUpdate();
            if (n >= 1) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void delete(int idUser, String name, String tier){
        try {
            delete.setInt(1, idUser);
            delete.setString(2, name);
            delete.setString(3, tier);
            delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

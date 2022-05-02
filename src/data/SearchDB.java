package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Date;

import domain.Search;
import domain.User;
import domain.dataCtrl.SearchDataCtrl;

public class SearchDB implements SearchDataCtrl {
    static SearchDB instance;
    Connection conn;
    PreparedStatement insert;
    PreparedStatement delete;
    PreparedStatement selectRecent;
    PreparedStatement select;

    private SearchDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://10.4.41.56:3306/RevPollution_Dev?allowPublicKeyRetrieval=true&useSSL=false", "dev", "aRqffCdBd9t!");
            insert = conn.prepareStatement("REPLACE INTO LastSearches(idUser, name, instant) VALUES (?, ?, ?)");
            selectRecent = conn.prepareStatement("SELECT * FROM LastSearches WHERE idUser = ? ORDER BY instant DESC LIMIT 3");
            select = conn.prepareStatement("SELECT * FROM LastSearches WHERE idUser = ? and name = ?");
            delete = conn.prepareStatement("DELETE FROM LastSearches WHERE idUser = ? and name = ?");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public SearchDB getInstance(){
        if(instance == null) instance = new SearchDB();
        return instance;
    }
 
    public boolean insert(Search s){
        try {
            insert.setInt(1, s.getUser().getId());
            insert.setString(2, s.getName());
            Timestamp ts = new Timestamp(s.getInstant().getTime());
            insert.setTimestamp(3, ts);
            int n = insert.executeUpdate();
            if (n >= 1) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void delete(int userId, String name){
        try {
            delete.setInt(1, userId);
            delete.setString(2, name);
            delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Search> selectRecent(int userId){
        ArrayList<Search> ret = new ArrayList<Search> ();
        try {
            selectRecent.setInt(1, userId);
            ResultSet r = selectRecent.executeQuery();
            while (r.next()) {
                int UserId = r.getInt("idUser");
                String name = r.getString("name");
                Timestamp ts = r.getTimestamp("instant");
                Date date = new Date(ts.getTime());

                User user = UserDB.getInstance().select(UserId);
                Search s = new Search(user, name, date);
                ret.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public Search select(int userId, String n){
        try {
            select.setInt(1, userId);
            select.setString(2, n);
            ResultSet r = select.executeQuery();
            if (r.next()) {
                int UserId = r.getInt("idUser");
                String name = r.getString("name");
                Timestamp ts = r.getTimestamp("instant");
                Date date = new Date(ts.getTime());

                User user = UserDB.getInstance().select(UserId);
                Search s = new Search(user, name, date);
                return s;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

import domain.Expo;
import domain.User;
import domain.dataCtrl.ExpoDataCtrl;

public class ExpoDB implements ExpoDataCtrl {
    private static ExpoDB instance;
    Connection conn;
    PreparedStatement selectRecent;
    PreparedStatement insert;
    PreparedStatement delete;
    PreparedStatement select;

    private ExpoDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://10.4.41.56:3306/RevPollution_Dev?allowPublicKeyRetrieval=true&useSSL=false", "dev", "aRqffCdBd9t!");
            selectRecent = conn.prepareStatement("SELECT * FROM DailyExposition WHERE idUser = ? LIMIT 7");
            insert = conn.prepareStatement("CALL InsertLocation(?, ?, ?)");
            delete = conn.prepareStatement("DELETE FROM Location WHERE idUser = ?");
            select = conn.prepareStatement("SELECT * FROM Location WHERE idUser = ?");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public ExpoDB getInstance(){
        if(instance == null) instance = new ExpoDB();
        return instance;
    }

    public ArrayList<Expo> selectRecent(int idU){
        ArrayList<Expo> ret = new ArrayList<Expo> ();
        try {
            selectRecent.setInt(1, idU);
            ResultSet r = selectRecent.executeQuery();
            while (r.next()) {
                int idUser = r.getInt("idUser");

                Date dat = r.getDate("dat");
                LocalDate localDate = dat.toLocalDate();

                int day = localDate.getDayOfMonth();
                int month = localDate.getMonthValue();
                int year = localDate.getYear();

                double value = r.getDouble("value");

                User u = UserDB.getInstance().select(idUser);

                Expo e = new Expo(u, day, month, year, value);
                ret.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public boolean insert(int idUser, double value){
        try {
            insert.setInt(1, idUser);
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            insert.setTimestamp(2, ts);
            insert.setDouble(3, value);
            int n = insert.executeUpdate();
            if (n >= 1) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void delete(int idUser){
        try {
            delete.setInt(1, idUser);
            delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean select(int idUser){
        try {
            select.setInt(1, idUser);
            ResultSet r = select.executeQuery();
            if (r.next()) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
}

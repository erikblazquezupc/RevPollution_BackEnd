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
    PreparedStatement selectAll;

    private ExpoDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://10.4.41.56:3306/RevPollution_Dev?allowPublicKeyRetrieval=true&useSSL=false", "dev", "aRqffCdBd9t!");
            selectRecent = conn.prepareStatement("SELECT * FROM DailyExposition WHERE idUser = ? LIMIT 30");
            insert = conn.prepareStatement("CALL InsertLocation(?, ?, ?)");
            delete = conn.prepareStatement("DELETE FROM Location WHERE idUser = ?");
            selectAll = conn.prepareStatement("SELECT * FROM Location WHERE idUser = ?");
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

    public ArrayList<Expo> selectAll(int idUser){
        ArrayList<Expo> expos = new ArrayList<Expo>();
        try {
            selectAll.setInt(1, idUser);
            ResultSet r = selectAll.executeQuery();
            if (r.next()) {
                int id = r.getInt("idUser");
                LocalDate date = r.getDate("instant").toLocalDate();
                int day = date.getDayOfMonth();
                int month = date.getMonthValue();
                int year = date.getYear();
                double value = r.getDouble("value");
                UserDB udb = UserDB.getInstance();
                User user = udb.select(id);
                Expo e = new Expo(user, day, month, year, value);
                expos.add(e);
            }
            return expos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expos;
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
}

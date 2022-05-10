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

import domain.DailyExposition;
import domain.User;
import domain.dataCtrl.DailyExpositionDataCtrl;

public class DailyExpositionDB implements DailyExpositionDataCtrl {
    private static DailyExpositionDB instance;
    Connection conn;
    PreparedStatement selectRecent;
    PreparedStatement insert;
    PreparedStatement delete;

    private DailyExpositionDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://10.4.41.56:3306/RevPollution_Dev?allowPublicKeyRetrieval=true&useSSL=false", "dev", "aRqffCdBd9t!");
            selectRecent = conn.prepareStatement("SELECT * FROM DailyExposition WHERE idUser = ? LIMIT 30");
            insert = conn.prepareStatement("INSERT INTO DailyExposition VALUES (?, ?, ?)");
            delete = conn.prepareStatement("DELETE FROM DailyExposition WHERE idUser = ?");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public DailyExpositionDB getInstance(){
        if(instance == null) instance = new DailyExpositionDB();
        return instance;
    }

    public ArrayList<DailyExposition> selectRecent(int idU){
        ArrayList<DailyExposition> ret = new ArrayList<DailyExposition> ();
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
                DailyExposition e = new DailyExposition(u, day, month, year, value);
                ret.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public boolean insert(int idUser, Date dat, double value){
        try {
            insert.setInt(1, idUser);
            Timestamp ts = new Timestamp(dat.getTime());
            insert.setTimestamp(2, ts);
            insert.setDouble(3, value);
            int n = insert.executeUpdate();
            if (n >= 1) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int idUser){
        try {
            delete.setInt(1, idUser);
            int n = delete.executeUpdate();
            if (n >= 1) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}


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

import domain.Location;
import domain.User;
import domain.dataCtrl.LocationDataCtrl;

public class LocationDB implements LocationDataCtrl {
    private static LocationDB instance;
    Connection conn;
    PreparedStatement insert;
    PreparedStatement delete;
    PreparedStatement select;

    private LocationDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://10.4.41.56:3306/RevPollution_Dev?allowPublicKeyRetrieval=true&useSSL=false", "dev", "aRqffCdBd9t!");
            insert = conn.prepareStatement("CALL InsertLocation(?, ?, ?)");
            delete = conn.prepareStatement("DELETE FROM Location WHERE idUser = ?");
            select = conn.prepareStatement("SELECT * FROM Location WHERE idUser = ?");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public LocationDB getInstance(){
        if(instance == null) instance = new LocationDB();
        return instance;
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

    public ArrayList<Location> select(int idUser){
        ArrayList<Location> ret = new ArrayList<Location>();
        try {
            select.setInt(1, idUser);
            ResultSet r = select.executeQuery();
            while (r.next()) {
                int idUs = r.getInt("idUser");
                Date dat = r.getDate("instant");
                LocalDate localDate = dat.toLocalDate();
                int day = localDate.getDayOfMonth();
                int month = localDate.getMonthValue();
                int year = localDate.getYear();
                double value = r.getDouble("value");

                User u = UserDB.getInstance().select(idUs);

                Location l = new Location(u, day, month, year, value);
                ret.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
    
}

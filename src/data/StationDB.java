package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.StationStub;
import domain.dataCtrl.StationDataCtrl;

public class StationDB implements StationDataCtrl{
    static StationDB instance;
    Connection conn;
    PreparedStatement insert;
    PreparedStatement update;
    PreparedStatement delete;
    PreparedStatement select;
    PreparedStatement selectAll;
    PreparedStatement selectByName;

    private StationDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://10.4.41.56:3306/RevPollution_Dev?allowPublicKeyRetrieval=true&useSSL=false", "dev", "aRqffCdBd9t!");
            insert = conn.prepareStatement("INSERT INTO Station(name, address, lat, lon) VALUES (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            selectByName = conn.prepareStatement("SELECT * FROM Station WHERE name = ?");
            select = conn.prepareStatement("SELECT * FROM Station WHERE idStation = ?");
            selectAll = conn.prepareStatement("SELECT * FROM Station");
            delete = conn.prepareStatement("DELETE FROM Station WHERE idStation = ?");
            update = conn.prepareStatement("UPDATE Station SET name = ?, address = ?, lat = ?, lon = ? WHERE idStation = ?");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public StationDB getInstance(){
        if(instance == null) instance = new StationDB();
        return instance;
    }

    public void insert(StationStub s){
        try {
            insert.setString(1, s.getName());
            insert.setString(2, s.getAddress());
            insert.setDouble(3, s.getLat());
            insert.setDouble(4, s.getLon());
            insert.executeUpdate();
            ResultSet r = insert.getGeneratedKeys();
            if (r.next())
                s.setId(r.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id){
        try {
            delete.setInt(1, id);
            delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(StationStub s){
        try {
            update.setString(1, s.getName());
            update.setString(2, s.getAddress());
            update.setDouble(3, s.getLat());
            update.setDouble(4, s.getLon());
            update.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public StationStub select(int id){
        try {
            select.setInt(1, id);
            ResultSet r = select.executeQuery();
            while(r.next()) {
                String name = r.getString("name");
                String address = r.getString("address");
                Double lat = r.getDouble("password");
                Double lon = r.getDouble("tel");
                StationStub s = new StationStub(id, name, address, lat, lon);
                return s;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<StationStub> selectAll(){
        try {
            ResultSet r = selectAll.executeQuery();
            List<StationStub> stations = new ArrayList<StationStub>();
            while(r.next()) {
                int idStation = r.getInt("idStation");
                String name = r.getString("name");
                String address = r.getString("address");
                Double lat = r.getDouble("lat");
                Double lon = r.getDouble("lon");
                StationStub s = new StationStub(idStation, name, address, lat, lon);
                stations.add(s);
            }
            return stations;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<StationStub>();
    }

    public StationStub selectByName(String un){
        try {
            selectByName.setString(1, un);
            ResultSet r = selectByName.executeQuery();
            while(r.next()) {
                int id = r.getInt("idStation");
                String name = r.getString("name");
                String address = r.getString("address");
                Double lat = r.getDouble("lat");
                Double lon = r.getDouble("lon");
                StationStub s = new StationStub(id, name, address, lat, lon);
                return s;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

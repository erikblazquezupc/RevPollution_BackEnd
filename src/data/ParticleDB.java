package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Particle;
import domain.dataCtrl.ParticleDataCtrl;

import java.util.ArrayList;

public class ParticleDB implements ParticleDataCtrl{
    static ParticleDB instance;
    Connection conn;
    PreparedStatement insert;
    PreparedStatement update;
    PreparedStatement delete;
    PreparedStatement select;
    PreparedStatement selectAll;

    private ParticleDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://10.4.41.56:3306/RevPollution_Dev?allowPublicKeyRetrieval=true&useSSL=false", "dev", "aRqffCdBd9t!");
            insert = conn.prepareStatement("INSERT INTO Particle(name, unit, activated) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            select = conn.prepareStatement("SELECT * FROM Particle WHERE name = ?");
            selectAll = conn.prepareStatement("SELECT * FROM Particle");
            delete = conn.prepareStatement("DELETE FROM Particle WHERE name = ?");
            update = conn.prepareStatement("UPDATE Particle SET name = ?, unit = ?, activated = ? WHERE name = ?");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public ParticleDB getInstance(){
        if(instance == null) instance = new ParticleDB();
        return instance;
    }

    public boolean insert(Particle p){
        try {
            insert.setString(1, p.getName());
            insert.setString(2, p.getUnit());
            insert.setBoolean(3, p.isActivated());
            if (insert.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void delete(String name){
        try {
            delete.setString(1, name);
            delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Particle p){
        try {
            update.setString(1, p.getName());
            update.setString(2, p.getUnit());
            update.setBoolean(3, p.isActivated());
            update.setString(4, p.getName());
            update.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Particle select(String nam){
        try {
            select.setString(1, nam);
            ResultSet r = select.executeQuery();
            while(r.next()) {
                String name = r.getString("name");
                String unit = r.getString("unit");
                Boolean activated = r.getBoolean("activated");
                Particle s = new Particle(name, unit, activated);
                return s;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Particle> selectAll(){
        ArrayList<Particle> ret = new ArrayList<Particle> ();
        try {
            ResultSet r = selectAll.executeQuery();
            while(r.next()) {
                String name = r.getString("name");
                String unit = r.getString("unit");
                Boolean activated = r.getBoolean("activated");
                Particle s = new Particle(name, unit, activated);
                ret.add(s);
            }
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

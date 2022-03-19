package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import domain.Concentration;
import domain.StationStub;
import domain.Particle;
import domain.dataCtrl.ConcentrationDataCtrl;

import java.util.ArrayList;
import java.util.Date;

public class ConcentrationDB implements ConcentrationDataCtrl{
    static ConcentrationDB instance;
    Connection conn;
    PreparedStatement insert;
    PreparedStatement update;
    PreparedStatement delete;
    PreparedStatement select;
    PreparedStatement selectMostRecentFromStation;

    private ConcentrationDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://10.4.41.56:3306/RevPollution_Dev?allowPublicKeyRetrieval=true&useSSL=false", "dev", "aRqffCdBd9t!");
            insert = conn.prepareStatement("INSERT INTO Concentration(idStation, nameParticle, instant, value) VALUES (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            select = conn.prepareStatement("SELECT * FROM Concentration WHERE idStation = ? AND nameParticle = ? and instant = ?");
            selectMostRecentFromStation = conn.prepareStatement("SELECT * FROM Concentration p1 WHERE p1.idStation = ? AND NOT EXISTS (SELECT * FROM Particle p2 WHERE p2.instant > p1.instant)");
            delete = conn.prepareStatement("DELETE FROM Concentration WHERE idStation = ? AND nameParticle = ? and instant = ?");
            update = conn.prepareStatement("UPDATE Station SET idStation = ?, nameParticle = ?, instant = ?, value = ?");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public ConcentrationDB getInstance(){
        if(instance == null) instance = new ConcentrationDB();
        return instance;
    }

    public boolean insert(Concentration p){
        try {
            insert.setInt(1, p.getStation().getId());
            insert.setString(2, p.getParticle().getName());
            Timestamp ts = new Timestamp(p.getInstant().getTime());
            insert.setTimestamp(3, ts);
            insert.setDouble(4, p.getValue());
            insert.executeUpdate();
            ResultSet r = insert.getGeneratedKeys();
            if (r.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void delete(StationStub s, Particle p, Date d){
        try {
            delete.setInt(1, s.getId());
            delete.setString(2, p.getName());
            Timestamp ts = new Timestamp(d.getTime());
            delete.setTimestamp(3, ts);
            delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Concentration p){
        try {
            update.setInt(1, p.getStation().getId());
            update.setString(2, p.getParticle().getName());
            Timestamp ts = new Timestamp(p.getInstant().getTime());
            update.setTimestamp(3, ts);
            update.setDouble(4, p.getValue());
            update.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Concentration select(StationStub s, Particle p, Date d){
        try {
            select.setInt(1, s.getId());
            select.setString(2, p.getName());
            Timestamp ts = new Timestamp(d.getTime());
            select.setTimestamp(3, ts);
            ResultSet r = select.executeQuery();
            while(r.next()) {
                int stationId = r.getInt("idStation");
                String nameParticle = r.getString("nameParticle");
                ts = r.getTimestamp("instant");
                Date date = new Date(ts.getTime());
                double value = r.getDouble("value");

                StationStub stat = StationDB.getInstance().select(stationId);
                Particle part = ParticleDB.getInstance().select(nameParticle);

                Concentration c = new Concentration(stat, part, date, value);

                return c;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Concentration> selectMostRecentFromStation(StationStub s) {
        ArrayList<Concentration> ret = new ArrayList<Concentration> ();
        try {
            selectMostRecentFromStation.setInt(1, s.getId());
            ResultSet r = selectMostRecentFromStation.executeQuery();
            while(r.next()) {
                int stationId = r.getInt("idStation");
                String nameParticle = r.getString("nameParticle");
                Timestamp ts = r.getTimestamp("instant");
                Date date = new Date(ts.getTime());
                double value = r.getDouble("value");

                StationStub stat = StationDB.getInstance().select(stationId);
                Particle part = ParticleDB.getInstance().select(nameParticle);

                Concentration c = new Concentration(stat, part, date, value);
                ret.add(c);
            }
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

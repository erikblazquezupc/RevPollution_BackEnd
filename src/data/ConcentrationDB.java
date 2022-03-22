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

import java.util.Date;
import java.util.ArrayList;

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
            select = conn.prepareStatement("SELECT * FROM Concentration WHERE idStation = ? AND nameParticle = ? AND instant = ?");
            selectMostRecentFromStation = conn.prepareStatement("SELECT * FROM Concentration c1 WHERE c1.idStation = ? AND NOT EXISTS (SELECT * FROM Concentration c2 WHERE c2.instant > c1.instant AND c2.idStation = c1.idStation)");
            delete = conn.prepareStatement("DELETE FROM Concentration WHERE idStation = ? AND nameParticle = ? AND instant = ?");
            update = conn.prepareStatement("UPDATE Concentration SET value = ? WHERE idStation = ? AND nameParticle = ? AND instant = ?");
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

    public boolean insert(Concentration c){
        try {
            insert.setInt(1, c.getStation().getId());
            insert.setString(2, c.getParticle().getName());
            Timestamp ts = new Timestamp(c.getInstant().getTime());
            insert.setTimestamp(3, ts);
            insert.setDouble(4, c.getValue());
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

    public void delete(int station, String particle, Date d){
        try {
            delete.setInt(1, station);
            delete.setString(2, particle);
            Timestamp ts = new Timestamp(d.getTime());
            delete.setTimestamp(3, ts);
            delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Concentration c){
        try {
            update.setDouble(1, c.getValue());
            update.setInt(2, c.getStationId());
            update.setString(3, c.getParticleName());
            Timestamp ts = new Timestamp(c.getInstant().getTime());
            update.setTimestamp(4, ts);
            update.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Concentration select(int station, String particle, Date d){
        try {
            select.setInt(1, station);
            select.setString(2, particle);
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

    public ArrayList<Concentration> selectMostRecentFromStation(int station) {
        ArrayList<Concentration> ret = new ArrayList<Concentration> ();
        try {
            selectMostRecentFromStation.setInt(1, station);
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

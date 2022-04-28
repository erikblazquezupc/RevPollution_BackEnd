package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.Logro;
import domain.dataCtrl.LogroDataCtrl;

public class LogroDB implements LogroDataCtrl{
    static LogroDB instance;
    Connection conn;
    PreparedStatement insert;
    PreparedStatement update;
    PreparedStatement delete;
    PreparedStatement select;
    PreparedStatement selectAll;
    //PreparedStatement selectByName;

    private LogroDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://10.4.41.56:3306/RevPollution_Dev?allowPublicKeyRetrieval=true&useSSL=false", "dev", "aRqffCdBd9t!");
            insert = conn.prepareStatement("INSERT INTO Logro(name, tier, condition) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            //selectByName = conn.prepareStatement("SELECT * FROM Logro WHERE name = ?");
            select = conn.prepareStatement("SELECT * FROM Logro WHERE name = ? AND tier = ?");
            selectAll = conn.prepareStatement("SELECT * FROM Logro");
            delete = conn.prepareStatement("DELETE FROM Logro WHERE name = ? AND tier = ?");
            update = conn.prepareStatement("UPDATE Logro SET name = ?, tier = ?, condition = ? WHERE name = ? AND tier = ?");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    static public LogroDB getInstance(){
        if(instance == null) instance = new LogroDB();
        return instance;
    }

    public void insert(Logro l){
        try {
            insert.setString(1, l.getName());
            insert.setTier(2, l.getTier());
            insert.setString(3, l.getCondition());
            insert.executeUpdate();
            ResultSet r = insert.getGeneratedKeys();
            //if (r.next())
                //return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
       // return false;
    }

    public void delete(String name, Tier tier){
        try {
            delete.setString(1, name);
            delete.setTier(2, tier);
            delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Logro l){
        try {
            update.setString(1, l.getName());
            update.setTier(2, l.getTier());
            update.setString(3, l.getCondition());
            update.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Logro select(String name, Tier tier){
        try {
            select.setString(1, name);
            select.setTier(2, tier);
            ResultSet r = select.executeQuery();
            while(r.next()) {
                //String name = r.getString("name");
                //Tier tier = r.getTier("tier");
                String condition = r.getString("condition");
                Logro l = new Logro(name, tier, condition);
                return l;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Logro> selectAll(){
        ArrayList<Logro> ret = new ArrayList<Logro> ();
        try {
            ResultSet r = selectAll.executeQuery();
            while(r.next()) {
                String name = r.getString("name");
                Tier tier = r.getTier("tier");
                String condition = r.getString("condition");
                Logro l = new Logro(name, tier, condition);
                ret.add(l);
            }
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
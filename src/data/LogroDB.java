package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.Logro;
import domain.Logro.Tier;
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
    PreparedStatement selectAdmin;
    PreparedStatement selectAllAdmin;
    PreparedStatement switchActivation;

    private LogroDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://10.4.41.56:3306/RevPollution_Dev?allowPublicKeyRetrieval=true&useSSL=false", "dev", "aRqffCdBd9t!");
            insert = conn.prepareStatement("INSERT INTO Logro(name, tier, cond, activated) VALUES (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            //selectByName = conn.prepareStatement("SELECT * FROM Logro WHERE name = ?");
            select = conn.prepareStatement("SELECT * FROM Logro WHERE name = ? AND tier = ?");
            selectAll = conn.prepareStatement("SELECT * FROM Logro WHERE activated = 1");
            delete = conn.prepareStatement("DELETE FROM Logro WHERE name = ? AND tier = ?");
            update = conn.prepareStatement("UPDATE Logro SET name = ?, tier = ?, cond = ?, activated = ? WHERE name = ? AND tier = ?");
            selectAdmin = conn.prepareStatement("SELECT * FROM Logro WHERE name = ? AND tier = ?");
            selectAllAdmin = conn.prepareStatement("SELECT * FROM Logro");
            switchActivation = conn.prepareStatement("UPDATE Logro SET activated = NOT activated WHERE name = ? AND tier = ?");
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

    public boolean insert(Logro l){
        try {
            insert.setString(1, l.getName());
            insert.setString(2, l.getTier().toString());
            insert.setString(3, l.getCondition());
            insert.setBoolean(4, l.getActivated());
            insert.executeUpdate();
            ResultSet r = insert.getGeneratedKeys();
            if (r.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void delete(String name, Tier tier){
        try {
            delete.setString(1, name);
            delete.setString(2, tier.toString());
            delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Logro l){
        try {
            update.setString(1, l.getName());
            update.setString(2, l.getTier().toString());
            update.setString(3, l.getCondition());
            update.setBoolean(4, l.getActivated());
            update.setString(5, l.getName());
            update.setString(6, l.getTier().toString());
            update.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Logro select(String n, Tier t){
        try {
            select.setString(1, n);
            select.setString(2, t.toString());
            ResultSet r = select.executeQuery();
            while(r.next()) {
                String name = r.getString("name");
                String tierString = r.getString("tier");
                Tier tier = Tier.valueOf(tierString);
                String cond = r.getString("cond");
                boolean activated = r.getBoolean("activated");
                Logro l = new Logro(name, tier, cond, activated);
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
                Tier tier = Tier.valueOf(r.getString("tier"));
                String cond = r.getString("cond");
                Logro l = new Logro(name, tier, cond);
                ret.add(l);
            }
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Logro selectAdmin(String n, Tier t) {
        try {
            selectAdmin.setString(1, n);
            selectAdmin.setString(2, t.toString());
            ResultSet r = selectAdmin.executeQuery();
            while(r.next()) {
                String name = r.getString("name");
                String tierString = r.getString("tier");
                Tier tier = Tier.valueOf(tierString);
                String cond = r.getString("cond");
                Boolean activated = r.getBoolean("activated");
                Logro l = new Logro(name, tier, cond, activated);
                return l;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Logro> selectAllAdmin() {
        ArrayList<Logro> ret = new ArrayList<Logro> ();
        try {
            ResultSet r = selectAllAdmin.executeQuery();
            while(r.next()) {
                String name = r.getString("name");
                String tierString = r.getString("tier");
                Tier tier = Tier.valueOf(tierString);
                String cond = r.getString("cond");
                Boolean activated = r.getBoolean("activated");
                Logro l = new Logro(name, tier, cond, activated);
                ret.add(l);
            }
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public int switchActivation(String name, Tier tier) {
        try {
            switchActivation.setString(1, name);
            switchActivation.setString(2, tier.toString());
            return switchActivation.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
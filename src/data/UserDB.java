package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.User;
import domain.dataCtrl.UserDataCtrl;

public class UserDB implements UserDataCtrl{
    static UserDB instance;
    Connection conn;
    PreparedStatement insert;
    PreparedStatement update;
    PreparedStatement delete;
    PreparedStatement select;
    PreparedStatement selectByUsername;

    private UserDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://10.4.41.56:3306/RevPollution_Dev?allowPublicKeyRetrieval=true&useSSL=false", "dev", "aRqffCdBd9t!");
            insert = conn.prepareStatement("INSERT INTO User(username, password, email, name, tel, img) VALUES (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            selectByUsername = conn.prepareStatement("SELECT * FROM User WHERE username = ?");
            select = conn.prepareStatement("SELECT * FROM User WHERE idUser = ?");
            delete = conn.prepareStatement("DELETE FROM User WHERE idUser = ?");
            update = conn.prepareStatement("UPDATE User SET username = ?, password = ?, email = ?, name = ?, tel = ?, img = ? WHERE idUser = ?");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public UserDB getInstance(){
        if(instance == null) instance = new UserDB();
        return instance;
    }

    public void insert(User u){
        try {
            insert.setString(1, u.getUsername());
            insert.setString(2, u.getPassword());
            insert.setString(3, u.getEmail());
            insert.setString(4, u.getName());
            insert.setString(5, u.getTel());
            insert.setString(6, u.getImg());
            insert.executeUpdate();
            ResultSet r = insert.getGeneratedKeys();
            if (r.next())
                u.setId(r.getInt(1));
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

    public void update(User u){
        try {
            update.setString(1, u.getUsername());
            update.setString(2, u.getPassword());
            update.setString(3, u.getEmail());
            update.setString(4, u.getName());
            update.setString(5, u.getTel());
            update.setString(6, u.getImg());
            update.setInt(7, u.getId());
            update.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User select(int id){
        try {
            select.setInt(1, id);
            ResultSet r = select.executeQuery();
            while(r.next()) {
                String username = r.getString("username");
                String name = r.getString("name");
                String email = r.getString("email");
                String password = r.getString("password");
                String tel = r.getString("tel");
                String img = r.getString("img");
                User u = new User(id, username, name, email, password, tel, img);
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User selectByUsername(String un){
        try {
            selectByUsername.setString(1, un);
            ResultSet r = selectByUsername.executeQuery();
            while(r.next()) {
                int id = r.getInt("idUser");
                String username = r.getString("username");
                String name = r.getString("name");
                String email = r.getString("email");
                String password = r.getString("password");
                String tel = r.getString("tel");
                String img = r.getString("img");
                User u = new User(id, username, name, email, password, tel, img);
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

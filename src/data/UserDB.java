package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.User;
import domain.dataCtrl.UserDataCtrl;

public class UserDB implements UserDataCtrl{
    private static UserDB instance;
    Connection conn;
    PreparedStatement insert;
    PreparedStatement update;
    PreparedStatement delete;
    PreparedStatement select;
    PreparedStatement selectByUsername;
    PreparedStatement selectByToken;
    PreparedStatement editUserInfo;

    private UserDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://10.4.41.56:3306/RevPollution_Dev?allowPublicKeyRetrieval=true&useSSL=false", "dev", "aRqffCdBd9t!");
            insert = conn.prepareStatement("INSERT INTO User(username, password, email, name, tel, img, token) VALUES (?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            selectByUsername = conn.prepareStatement("SELECT * FROM User WHERE username = ?");
            selectByToken = conn.prepareStatement("SELECT * FROM User WHERE token = ?");
            select = conn.prepareStatement("SELECT * FROM User WHERE idUser = ?");
            delete = conn.prepareStatement("DELETE FROM User WHERE idUser = ?");
            update = conn.prepareStatement("UPDATE User SET username = ?, password = ?, email = ?, name = ?, tel = ?, img = ?, token = ? WHERE idUser = ?");
            editUserInfo = conn.prepareStatement("UPDATE User SET username = ?, password = ?, email = ?, name = ?, tel = ?, img = ? WHERE token = ?");
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

    public boolean insert(User u){
        try {
            insert.setString(1, u.getUsername());
            insert.setString(2, u.getPassword());
            insert.setString(3, u.getEmail());
            insert.setString(4, u.getName());
            insert.setString(5, u.getTel());
            insert.setString(6, u.getImg());
            insert.setString(7, u.getToken());
            insert.executeUpdate();
            ResultSet r = insert.getGeneratedKeys();
            if (r.next()) {
                u.setId(r.getInt(1));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
            update.setString(7, u.getToken());
            update.setInt(8, u.getId());
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
                String token = r.getString("token");
                User u = new User(id, username, name, email, password, tel, img);
                u.setToken(token);
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
                String token = r.getString("token");
                User u = new User(id, username, name, email, password, tel, img);
                u.setToken(token);
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User selectByToken(String token){
        try {
            selectByToken.setString(1, token);
            ResultSet r = selectByToken.executeQuery();
            while(r.next()) {
                int id = r.getInt("idUser");
                String username = r.getString("username");
                String name = r.getString("name");
                String email = r.getString("email");
                String password = r.getString("password");
                String tel = r.getString("tel");
                String img = r.getString("img");
                User u = new User(id, username, name, email, password, tel, img);
                u.setToken(token);
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void editInfo(User u){
        try {
            editUserInfo.setString(1, u.getUsername());
            editUserInfo.setString(2, u.getPassword());
            editUserInfo.setString(3, u.getEmail());
            editUserInfo.setString(4, u.getName());
            editUserInfo.setString(5, u.getTel());
            editUserInfo.setString(6, u.getImg());
            editUserInfo.setString(7, u.getToken());
            editUserInfo.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

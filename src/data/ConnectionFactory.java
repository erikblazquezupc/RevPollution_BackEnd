package data;

import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;

public class ConnectionFactory {
    private static ConnectionFactory instance;
    Connection conn;
    String url = "10.4.41.56:3306";
    String user = "dev";
    String password = "aRqffCdBd9t!";
    String database = "RevPollution_Dev";
    String configuration = "allowPublicKeyRetrieval=true&useSSL=false";

    private ConnectionFactory(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://"+url+"/"+database+"?"+configuration, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public ConnectionFactory getInstance(){
        if(instance == null) instance = new ConnectionFactory();
        return instance;
    }

    public Connection getConnection(){
        return conn;
    }
}

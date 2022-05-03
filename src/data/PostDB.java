package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Post;
import domain.dataCtrl.PostDataCtrl;

public class PostDB implements PostDataCtrl{
    private static PostDB instance;
    Connection conn;
    PreparedStatement insert;
    PreparedStatement selectAll;
    PreparedStatement selectByDateBigger;
    PreparedStatement selectByDateSmaller;
    PreparedStatement selectByDateBoth;

    private PostDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://10.4.41.56:3306/RevPollution_Dev?allowPublicKeyRetrieval=true&useSSL=false", "dev", "aRqffCdBd9t!");
            insert = conn.prepareStatement("");
            selectAll = conn.prepareStatement("");
            selectByDateBigger = conn.prepareStatement("");
            selectByDateSmaller = conn.prepareStatement("");
            selectByDateBoth = conn.prepareStatement("");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public PostDB getInstance(){
        if(instance == null) instance = new PostDB();
        return instance;
    }

    public boolean insert(Post u){
        try {
            // Logic and return true
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Post> selectAll(){
        try {
            // Logic and return posts
            return new ArrayList<Post>();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Post> selectByDateBigger(Integer firstDate) {
        try {
            // Logic and return posts
            return new ArrayList<Post>();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Post> selectByDateBoth(Integer firstDate, Integer lastDate) {
        try {
            // Logic and return posts
            return new ArrayList<Post>();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Post> selectByDateSmaller(Integer lastDate) {
        try {
            // Logic and return posts
            return new ArrayList<Post>();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
}

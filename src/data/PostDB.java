package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Post;
import domain.User;
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
            insert = conn.prepareStatement("INSERT INTO Posts(text, idCreator, postedOn) VALUES (?, ?, ?)");
            selectAll = conn.prepareStatement("SELECT * FROM Posts");
            selectByDateBigger = conn.prepareStatement("SELECT * FROM Posts WHERE timestamp > ?");
            selectByDateSmaller = conn.prepareStatement("SELECT * FROM Posts WHERE timestamp < ?");
            selectByDateBoth = conn.prepareStatement("SELECT * FROM Posts WHERE timestamp > ? AND timestamp < ?");
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

    public boolean insert(Post p){
        try {
            insert.setString(1, p.getText());
            insert.setInt(2, p.getCreator().getId());
            insert.setLong(3, p.getPostedOn());
            int n = insert.executeUpdate();
            if (n >= 1) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Post> selectAll(){
        ArrayList<Post> ret = new ArrayList<Post> ();
        try {
            ResultSet r = selectAll.executeQuery();
            while(r.next()) {
                int idCreator = r.getInt("idCreator");
                String text = r.getString("text");
                long postedOn = r.getLong("postedOn");
                
                User user = UserDB.getInstance().select(idCreator);

                Post s = new Post(user, text, postedOn);
                ret.add(s);
            }
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Post> selectByDateBigger(Integer firstDate) {
        ArrayList<Post> ret = new ArrayList<Post> ();
        try {
            ResultSet r = selectByDateBigger.executeQuery();
            while(r.next()) {
                selectByDateBigger.setInt(1, firstDate);

                int idCreator = r.getInt("idCreator");
                String text = r.getString("text");
                long postedOn = r.getLong("postedOn");
                
                User user = UserDB.getInstance().select(idCreator);

                Post s = new Post(user, text, postedOn);
                ret.add(s);
            }
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Post> selectByDateSmaller(Integer lastDate) {
        ArrayList<Post> ret = new ArrayList<Post> ();
        try {
            ResultSet r = selectByDateSmaller.executeQuery();
            while(r.next()) {
                selectByDateSmaller.setInt(1, lastDate);

                int idCreator = r.getInt("idCreator");
                String text = r.getString("text");
                long postedOn = r.getLong("postedOn");
                
                User user = UserDB.getInstance().select(idCreator);

                Post s = new Post(user, text, postedOn);
                ret.add(s);
            }
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Post> selectByDateBoth(Integer firstDate, Integer lastDate) {
        ArrayList<Post> ret = new ArrayList<Post> ();
        try {
            ResultSet r = selectByDateBoth.executeQuery();
            while(r.next()) {
                selectByDateBoth.setInt(1, firstDate);
                selectByDateBoth.setInt(2, lastDate);

                int idCreator = r.getInt("idCreator");
                String text = r.getString("text");
                long postedOn = r.getLong("postedOn");
                
                User user = UserDB.getInstance().select(idCreator);

                Post s = new Post(user, text, postedOn);
                ret.add(s);
            }
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

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
    PreparedStatement select;
    PreparedStatement selectAll;
    PreparedStatement selectByDateBigger;
    PreparedStatement selectByDateSmaller;
    PreparedStatement selectByDateBoth;
    PreparedStatement delete;

    private PostDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://10.4.41.56:3306/RevPollution_Dev?allowPublicKeyRetrieval=true&useSSL=false", "dev", "aRqffCdBd9t!");
            insert = conn.prepareStatement("INSERT INTO Post(text, idCreator, postedOn) VALUES (?, ?, ?)");
            selectAll = conn.prepareStatement("SELECT * FROM Post");
            select = conn.prepareStatement("SELECT * FROM Post WHERE idCreator = ? AND postedOn = ?");
            selectByDateBigger = conn.prepareStatement("SELECT * FROM Post WHERE postedOn >= ?");
            selectByDateSmaller = conn.prepareStatement("SELECT * FROM Post WHERE postedOn <= ?");
            selectByDateBoth = conn.prepareStatement("SELECT * FROM Post WHERE postedOn >= ? AND postedOn <= ?");
            delete = conn.prepareStatement("DELETE FROM Post WHERE idCreator = ? AND postedOn = ?");
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

    public Post select(Integer idCreator, Long postedOn) {
        Post ret = null;
        try {
            select.setInt(1, idCreator);
            select.setLong(2, postedOn);
            ResultSet r = select.executeQuery();
            while(r.next()) {

                int newIdCreator = r.getInt("idCreator");
                String newText = r.getString("text");
                long newPostedOn = r.getLong("postedOn");
                
                User user = UserDB.getInstance().select(newIdCreator);

                Post s = new Post(user, newText, newPostedOn);
                ret = s;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
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

    public List<Post> selectByDateBigger(Long firstDate) {
        ArrayList<Post> ret = new ArrayList<Post> ();
        try {
            selectByDateBigger.setLong(1, firstDate);
            ResultSet r = selectByDateBigger.executeQuery();
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

    public List<Post> selectByDateSmaller(Long lastDate) {
        ArrayList<Post> ret = new ArrayList<Post> ();
        try {
            selectByDateSmaller.setLong(1, lastDate);
            ResultSet r = selectByDateSmaller.executeQuery();
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

    public List<Post> selectByDateBoth(Long firstDate, Long lastDate) {
        ArrayList<Post> ret = new ArrayList<Post> ();
        try {
            selectByDateBoth.setLong(1, firstDate);
            selectByDateBoth.setLong(2, lastDate);
            ResultSet r = selectByDateBoth.executeQuery();
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

    public Integer delete(Integer idCreator, Long postedOn) {
        try {
            delete.setInt(1, idCreator);
            delete.setLong(2, postedOn);
            Integer result = delete.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}

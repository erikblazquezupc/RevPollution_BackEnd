package domain.dataCtrl;

import java.util.ArrayList;

import domain.Post;

public interface PostDataCtrl {
    public boolean insert(Post p);
    public Post select(Integer idCreator, Long postedOn);
    public ArrayList<Post> selectAll();
    public ArrayList<Post> selectByDateBigger(Long firstDate);
    public ArrayList<Post> selectByDateSmaller(Long lastDate);
    public ArrayList<Post> selectByDateBoth(Long firstDate, Long lastDate);
    public Integer delete(Integer idCreator, Long postedOn);
}

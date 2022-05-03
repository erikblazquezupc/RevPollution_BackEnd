package domain.dataCtrl;

import java.util.List;

import domain.Post;

public interface PostDataCtrl {
    public boolean insert(Post p);
    public List<Post> selectAll();
    public List<Post> selectByDateBigger(Integer firstDate);
    public List<Post> selectByDateSmaller(Integer lastDate);
    public List<Post> selectByDateBoth(Integer firstDate, Integer lastDate);
}

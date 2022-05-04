package domain.dataCtrl;

import java.util.List;

import domain.Post;

public interface PostDataCtrl {
    public boolean insert(Post p);
    public Post select(Integer idCreator, Long postedOn);
    public List<Post> selectAll();
    public List<Post> selectByDateBigger(Long firstDate);
    public List<Post> selectByDateSmaller(Long lastDate);
    public List<Post> selectByDateBoth(Long firstDate, Long lastDate);
    public Integer delete(Integer idCreator, Long postedOn);
}

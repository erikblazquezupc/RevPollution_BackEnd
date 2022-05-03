package domain.controllers;

import java.util.ArrayList;
import java.util.List;

import domain.Post;

public class TxGetPosts {
    Integer firstDate;
    Integer lastDate;
    
    List<Post> result;

    public TxGetPosts(Integer firstDate, Integer lastDate) {
        this.firstDate = firstDate;
        this.lastDate = lastDate;
    }

    public void execute(){
        // LOGIC GOES HERE
        result = new ArrayList<Post>();
    }

    public List<Post> getResult(){
        return result;
    }
}

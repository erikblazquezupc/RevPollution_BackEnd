package domain.controllers;

import java.util.ArrayList;
import java.util.List;

import domain.Post;

public class TxGetAllPosts {
    List<Post> result;

    public TxGetAllPosts() {}

    public void execute(){
        // LOGIC GOES HERE
        result = new ArrayList<Post>();
    }

    public List<Post> getResult(){
        return result;
    }
}

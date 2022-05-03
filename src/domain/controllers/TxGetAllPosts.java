package domain.controllers;

import java.util.List;

import domain.Post;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.PostDataCtrl;

public class TxGetAllPosts {
    List<Post> result;

    public TxGetAllPosts() {}

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        PostDataCtrl pdc = dataCtrl.getPostDataCtrl();
        result = pdc.selectAll();
    }

    public List<Post> getResult(){
        return result;
    }
}

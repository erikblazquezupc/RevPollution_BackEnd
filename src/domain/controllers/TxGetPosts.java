package domain.controllers;

import java.util.List;

import domain.Post;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.PostDataCtrl;

public class TxGetPosts {
    Long firstDate;
    Long lastDate;
    
    List<Post> result;

    public TxGetPosts(Long firstDate, Long lastDate) {
        this.firstDate = firstDate;
        this.lastDate = lastDate;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        PostDataCtrl pdc = dataCtrl.getPostDataCtrl();

        if (firstDate == null && lastDate == null) result = null;
        else if (lastDate == null) result = pdc.selectByDateBigger(firstDate);
        else if (firstDate == null) result = pdc.selectByDateSmaller(lastDate);
        else result = pdc.selectByDateBoth(firstDate, lastDate);
    }

    public List<Post> getResult(){
        return result;
    }
}

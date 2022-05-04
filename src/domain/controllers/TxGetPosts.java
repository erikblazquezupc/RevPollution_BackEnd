package domain.controllers;

import java.util.ArrayList;

import org.codehaus.jettison.json.JSONArray;

import domain.Post;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.PostDataCtrl;

public class TxGetPosts {
    Long firstDate;
    Long lastDate;
    
    JSONArray result;

    public TxGetPosts(Long firstDate, Long lastDate) {
        this.firstDate = firstDate;
        this.lastDate = lastDate;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        PostDataCtrl pdc = dataCtrl.getPostDataCtrl();

        ArrayList<Post> tmp;
        if (firstDate == null && lastDate == null) tmp = null;
        else if (lastDate == null) tmp = pdc.selectByDateBigger(firstDate);
        else if (firstDate == null) tmp = pdc.selectByDateSmaller(lastDate);
        else tmp = pdc.selectByDateBoth(firstDate, lastDate);

        result = new JSONArray();
        for (int i = 0; i < tmp.size(); ++i) result.put(tmp.get(i).toJSON());
    }

    public JSONArray getResult(){
        return result;
    }
}

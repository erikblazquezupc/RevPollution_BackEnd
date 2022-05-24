package domain.controllers;

import java.util.ArrayList;

import org.codehaus.jettison.json.JSONArray;

import domain.Post;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.PostDataCtrl;

public class TxGetAllPosts {
    JSONArray result;

    public TxGetAllPosts() {}

    public void execute(){
        ArrayList<Post> tmp;
        DataCtrl dataCtrl = DataCtrl.getInstance();
        PostDataCtrl pdc = dataCtrl.getPostDataCtrl();
        tmp = pdc.selectAll();

        result = new JSONArray();
        for (int i = 0; i < tmp.size(); ++i) result.put(tmp.get(i).toJSON());
    }

    public JSONArray getResult(){
        return result;
    }
}

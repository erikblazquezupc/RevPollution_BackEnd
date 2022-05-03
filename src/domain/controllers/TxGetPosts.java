package domain.controllers;

import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

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

        List<Post> tmp;
        if (firstDate == null && lastDate == null) tmp = null;
        else if (lastDate == null) tmp = pdc.selectByDateBigger(firstDate);
        else if (firstDate == null) tmp = pdc.selectByDateSmaller(lastDate);
        else tmp = pdc.selectByDateBoth(firstDate, lastDate);

        result = new JSONArray();
        for (int i = 0; i < tmp.size(); ++i){
            try {
                JSONObject item = new JSONObject();
                item.put("username", tmp.get(i).getCreator().getUsername());
                item.put("profilepic", tmp.get(i).getCreator().getImg());
                item.put("text", tmp.get(i).getText());
                item.put("timestamp", tmp.get(i).getPostedOn());
                result.put(item);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public JSONArray getResult(){
        return result;
    }
}

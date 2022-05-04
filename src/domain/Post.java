package domain;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class Post {
    User creator;
    String text;
    long postedOn;

    public Post(User creator, String text, long postedOn) {
        this.creator = creator;
        this.text = text;
        this.postedOn = postedOn;
    }

    public Post(User creator, String text) {
        this.creator = creator;
        this.text = text;
        this.postedOn = System.currentTimeMillis();
    }

    public User getCreator() {
        return creator;
    }

    public String getText() {
        return text;
    }

    public long getPostedOn() {
        return postedOn;
    }

    @Override
    public String toString() {
        return "Post [creator=" + creator + ", postedOn=" + postedOn + ", text=" + text + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((creator == null) ? 0 : creator.hashCode());
        result = prime * result + (int) (postedOn ^ (postedOn >>> 32));
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Post other = (Post) obj;
        if (creator == null) {
            if (other.creator != null)
                return false;
        } else if (!creator.equals(other.creator))
            return false;
        if (postedOn != other.postedOn)
            return false;
        if (text == null) {
            if (other.text != null)
                return false;
        } else if (!text.equals(other.text))
            return false;
        return true;
    }

    public JSONObject toJSON(){
        try {
            JSONObject item = new JSONObject();
            item.put("username", creator.getUsername());
            item.put("profilepic", creator.getImg());
            item.put("text", text);
            item.put("timestamp", postedOn);
            return item;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}

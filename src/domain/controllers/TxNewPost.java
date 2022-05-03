package domain.controllers;

public class TxNewPost {
    String text;
    String userToken;
    
    Boolean result;

    public TxNewPost(String text, String userToken) {
        this.text = text;
        this.userToken = userToken;
    }

    public void execute(){
        // LOGIC GOES HERE
        result = false;
    }

    public boolean getResult(){
        return result;
    }
}

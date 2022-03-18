package domain.controllers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import domain.User;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;

public class TxLogIn {
    private String username;
    private String password;
    private String result;

    public TxLogIn(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void execute(){
        DataCtrl dataCtrl = DataCtrl.getInstance();
        UserDataCtrl userCtrl = dataCtrl.getUserDataCtrl();
        User u = userCtrl.selectByUsername(username);
        String hash = hash(username, password);
        System.out.println(hash);
        if(u == null) return;
        if(!u.getPassword().equals(password)) return;
        String token = hash(Long.toString(System.currentTimeMillis()), username);
        u.setToken(token);
        userCtrl.update(u);
        System.out.println(token);
        result = token;
    }

    public String getResult(){
        return result;
    }

    private String hash(String text, String word) {
		MessageDigest digest;
		String hash = "";
		try {
			digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
		    digest.update((text+word).getBytes());
		    hash = String.format("%064x", new BigInteger(1, digest.digest()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hash;
	}
}

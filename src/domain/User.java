package domain;

public class User{
    int id;
    String username;
    String name;
    String email;
    String password;
    String tel;
    String img;

    public User(){
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String name, String email, String password, String tel, String img) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.img = img;
    }

    public User(int id, String username, String name, String email, String password, String tel, String img) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", name=" + name + ", email=" + email + ", password=" + password + ", tel=" + tel + ", img=" + img +  
                "]" ;
    }
}
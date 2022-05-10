package domain;

public class Location {
    User user;
    Integer day;
    Integer month;
    Integer year;
    Double value;

    public Location() {}

    public Location(User user, Integer day, Integer month, Integer year, double value) {
        this.user = user;
        this.day = day;
        this.month = month;
        this.year = year;
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Location [user=" + user.getName() + ", day=" + day + ", month=" + month +  
        ", year=" + year + ", value=" + value + "]" ;
    }

    public int getUserId() {
        return user.getId();
    }

}

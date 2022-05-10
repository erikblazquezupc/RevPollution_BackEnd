package domain;

public class DailyExposition extends Location {

    public DailyExposition() {}

    public DailyExposition(User user, Integer day, Integer month, Integer year, double value) {
        this.user = user;
        this.day = day;
        this.month = month;
        this.year = year;
        this.value = value;
    }

    @Override
    public String toString() {
        return "DailyExposition [user=" + user.getName() + ", day=" + day + ", month=" + month +  
        ", year=" + year + ", value=" + value + "]" ;
    }

}


package domain;

public class UserLogro {
    User user;
    Logro logro;
    int points;
    boolean achieved;

    public UserLogro(User user, Logro logro, int points, boolean achieved){
        this.user = user;
        this.logro = logro;
        this.points = points;
        this.achieved = achieved;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Logro getLogro() {
        return logro;
    }

    public void setLogro(Logro logro) {
        this.logro = logro;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isAchieved() {
        return achieved;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (achieved ? 1231 : 1237);
        result = prime * result + ((logro == null) ? 0 : logro.hashCode());
        result = prime * result + points;
        result = prime * result + ((user == null) ? 0 : user.hashCode());
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
        UserLogro other = (UserLogro) obj;
        if (achieved != other.achieved)
            return false;
        if (logro == null) {
            if (other.logro != null)
                return false;
        } else if (!logro.equals(other.logro))
            return false;
        if (points != other.points)
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "UserLogro [logro=" + logro + ", user=" + user + ", points=" + points + ", achieved=" + achieved + "]";
    }

    public String getLogroTier() {
        return logro.getTier().toString();
    }

    public String getLogroName() {
        return logro.getName();
    }

    public int getUserId() {
        return user.getId();
    }

}

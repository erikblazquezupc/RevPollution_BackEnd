package domain;

import java.util.Date;

public class Search {
    User user;
    StationStub station;
    Date instant;

    public Search() {

    }

    public Search(User user, StationStub station, Date date) {
        this.user = user;
        this.station = station;
        this.instant = date;
    }

    public StationStub getStation() {
        return station;
    }

    public User getUser() {
        return user;
    }

    public void setStation(StationStub station) {
        this.station = station;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getInstant() {
        return instant;
    }

    public void setInstant(Date date) {
        this.instant = date;
    }

    @Override
    public String toString() {
        return "Search [user=" + user.getName() + ", station=" + station.getName() + ", instant=" + instant + 
                "]" ;
    }

    public int getStationId() {
        return station.getId();
    }

    public String getUsername() {
        return user.getUsername();
    }

    public int getUserId() {
        return user.getId();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((instant == null) ? 0 : instant.hashCode());
        result = prime * result + ((station == null) ? 0 : station.hashCode());
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
        Search other = (Search) obj;
        if (instant == null) {
            if (other.instant != null)
                return false;
        } else if (!instant.equals(other.instant))
            return false;
        if (station == null) {
            if (other.station != null)
                return false;
        } else if (!station.equals(other.station))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }
}

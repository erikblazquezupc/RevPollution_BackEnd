package domain;

import java.util.Date;

public class Search {
    User user;
    String name;
    Date instant;

    public Search() {
 
    }

    public Search(User user, String name, Date date) {
        this.user = user;
        this.name = name;
        this.instant = date;
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Search [user=" + user.getName() + ", name=" + name + ", instant=" + instant + 
                "]" ;
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
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }
}

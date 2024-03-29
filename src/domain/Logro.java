package domain;


public class Logro {
    public enum Tier {
        bronce, plata, oro;
    }

    String name;
    Tier tier;
    String cond;
    boolean activated;

    public Logro() {
        activated = true;
    }

    public Logro(String name, Tier tier) {
        this.name = name;
        this.tier = tier;
        activated = true;
    }

    public Logro(String name, Tier tier, String cond) {
        this.name = name;
        this.tier = tier;
        this.cond = cond;
        activated = true;
    }

    public Logro(String name, Tier tier, String cond, boolean activated) {
        this.name = name;
        this.tier = tier;
        this.cond = cond;
        this.activated = activated;
    }

    public Logro (String name, String tier, String cond) {
        this.name = name;
        this.tier = Tier.valueOf(tier);
        this.cond = cond;
        activated = true;
    }

    public String getName() {
        return name;
    }

    //public String getTier() {
    //    return tier.toString();
    //}

    public Tier getTier() {
        return tier;
    }

    public String getCondition() {
        return cond;
    }

    public boolean getActivated() {
        return activated;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    public void setCondition(String cond) {
        this.cond = cond;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }


    public String toStringActivated() {
        int act;
        if (activated) act = 1;
        else act = 0;
        return "Logro [name=" + name + ", tier=" + tier + ", cond=" + cond + ", activated=" + act + "]";
    }

    

    @Override
    public String toString() {
        return "Logro [activated=" + activated + ", cond=" + cond + ", name=" + name + ", tier=" + tier + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (activated ? 1231 : 1237);
        result = prime * result + ((cond == null) ? 0 : cond.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((tier == null) ? 0 : tier.hashCode());
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
        Logro other = (Logro) obj;
        if (activated != other.activated)
            return false;
        if (cond == null) {
            if (other.cond != null)
                return false;
        } else if (!cond.equals(other.cond))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (tier != other.tier)
            return false;
        return true;
    }

    

}
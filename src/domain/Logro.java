package domain;


public class Logro {
    public enum Tier {
        bronce, plata, oro;
   }

    String name;
    Tier tier;
    String cond;

    public Logro() {

    }

    public Logro(String name, Tier tier) {
        this.name = name;
        this.tier = tier;
    }

    public Logro(String name, Tier tier, String cond) {
        this.name = name;
        this.tier = tier;
        this.cond = cond;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    public void setCondition(String cond) {
        this.cond = cond;
    }

    @Override
    public String toString() {
        return "Logro [name=" + name + ", tier=" + tier + ", cond=" + cond + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((tier == null) ? 0 : tier.hashCode());
        result = prime * result + ((cond == null) ? 0 : cond.hashCode());
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
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (tier == null) {
            if (other.tier != null)
                return false;
        } else if (!tier.equals(other.tier))
            return false;
        if (cond == null) {
            if (other.cond != null)
                return false;
        } else if (!cond.equals(other.cond))
            return false;
        if (tier != other.tier)
            return false;
        return true;
    }


}
package domain;

public class Logro {
    enum Tier {
        bronce, plata, oro;
    }

    String name;
    Tier tier;
    String condition;

    public Logro() {

    }

    public Logro(String name, Tier tier) {
        this.name = name;
        this.tier = tier;
    }

    public Logro(String name, Tier tier, String condition) {
        this.name = name;
        this.tier = tier;
        this.condition = condition;
    }

    public String getName() {
        return name;
    }

    public Tier getTier() {
        return tier;
    }

    public String getCondition() {
        return condition;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Logro [name=" + name + ", tier=" + tier + ", condition=" + condition + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((tier == null) ? 0 : tier.hashCode());
        result = prime * result + ((condition == null) ? 0 : condition.hashCode());
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
        if (condition == null) {
            if (other.condition != null)
                return false;
        } else if (!condition.equals(other.condition))
            return false;
        if (tier != other.tier)
            return false;
        return true;
    }


}
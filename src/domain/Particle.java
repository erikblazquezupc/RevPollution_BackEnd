package domain;

public class Particle {

    private enum Units {
        mgm3("mg/m3"), 
        ugm3("ug/m3"),
        ppm("ppm"), 
        ngm3("ng/m3");
        
        private final String UnitName;

        Units(String UnitName) {
            this.UnitName = UnitName;
        }

        @Override
        public String toString() {
            return UnitName;
        }

        public static Units get(String u){
            if (u.equals("mg/m3")) return mgm3;
            if (u.equals("ug/m3")) return ugm3;
            if (u.equals("ppm")) return ppm;
            if (u.equals("ng/m3")) return ngm3;
            return null;
        }
    }

    String name;
    Units unit;
    boolean activated;

    public Particle () {
        this.activated = true;
    }

    public Particle(String name) {
        this.name = name;
        this.activated = true;
    }

    public Particle (String name, String u) {
        this.name = name;
        this.unit = Units.get(u);
        this.activated = true;
    }

    public Particle(String name, String unit, Boolean activated) {
        this.name = name;
        this.unit = Units.get(unit);
        this.activated = activated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit.toString();
    }

    public void setUnit(String u) {
        this.unit = Units.get(u);
    }

    @Override
    public String toString() {
        return "Particle [activated=" + activated + ", name=" + name + ", unit=" + unit + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (activated ? 1231 : 1237);
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((unit == null) ? 0 : unit.hashCode());
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
        Particle other = (Particle) obj;
        if (activated != other.activated)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (unit != other.unit)
            return false;
        return true;
    }

    public void activate(){
        this.activated = true;
    }

    public void deactivate(){
        this.activated = false;
    }

    public boolean isActivated() {
        return activated;
    }
}

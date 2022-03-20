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

    public Particle () {
    }

    public Particle(String name) {
        this.name = name;
    }

    public Particle (String name, String u) {
        this.name = name;
        this.unit = Units.get(u);
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
        return "Particle [name=" + name + ", unit=" + unit.toString() + "]";
    }
}

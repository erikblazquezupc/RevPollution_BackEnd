package domain;

public class Particle {

    private enum Units {
        mgm3, ugm3, ppm, ngm3;
    }

    String name;
    Units unit;

    public Particle () {

    }

    public Particle(String name) {
        this.name = name;
    }

    public Particle (String name, Units u) {
        this.name = name;
        this.unit = u;
    }

    public Particle (String name, String u) {
        this.name = name;
        if (u.equals("mg/m3")) {
            this.unit = Units.mgm3;
        }
        else if (u.equals("ug/m3")) {
            this.unit = Units.ugm3;
        }
        else if (u.equals("ppm")) {
            this.unit = Units.ppm;
        }
        else if (u.equals("ng/m3")) {
            this.unit = Units.ngm3;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Units getUnit() {
        return unit;
    }

    public String getUnitToString() {
        String s = new String();
        if (unit.equals(Units.mgm3)) {
            s = "mg/m3";
        }
        else if (unit.equals(Units.ugm3)) {
            s = "ug/m3";
        }
        else if (unit.equals(Units.ppm)) {
            s = "ppm";
        }
        else if (unit.equals(Units.ugm3)) {
            s = "ug/m3";
        }
        return s;
    }

    public void setUnit(Units u) {
        this.unit = u;
    }

    public void setUnitByString(String u) {
        if (u.equals("mg/m3")) {
            this.unit = Units.mgm3;
        }
        else if (u.equals("ug/m3")) {
            this.unit = Units.ugm3;
        }
        else if (u.equals("ppm")) {
            this.unit = Units.ppm;
        }
        else if (u.equals("ng/m3")) {
            this.unit = Units.ngm3;
        }
    }

    @Override
    public String toString() {
        return "Particle [name=" + name + ", unit=" + getUnitToString() + "]";
    }
}

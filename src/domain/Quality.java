package domain;

import java.util.Comparator;
import java.util.Date;

public class Quality {
    StationStub station;
    Date instant;
    double value;

    public Quality() {

    }

    public Quality(StationStub station, Date date, double value) {
        this.station = station;
        this.instant = date;
        this.value = value;
    }

    public StationStub getStation() {
        return station;
    }

    public void setStation(StationStub station) {
        this.station = station;
    }

    public Date getInstant() {
        return instant;
    }

    public void setInstant(Date date) {
        this.instant = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Quality [station=" + station.getName() + ", instant=" + instant + ", value=" + value +  
                "]" ;
    }

    public int getStationId() {
        return station.getId();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((instant == null) ? 0 : instant.hashCode());
        result = prime * result + ((station == null) ? 0 : station.hashCode());
        long temp;
        temp = Double.doubleToLongBits(value);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        Concentration other = (Concentration) obj;
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
        if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
            return false;
        return true;
    }

    public static Comparator<Quality> dateComp = new Comparator<Quality> () {
        public int compare (Quality c1, Quality c2) {
            Date d1 = c1.getInstant();
            Date d2 = c2.getInstant();
            return d1.compareTo(d2);
        }
    };
}

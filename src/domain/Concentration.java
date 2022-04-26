package domain;

import java.util.Date;

/*
Timestamp timestamp = resultSet.getTimeStamp("instant");
if (timestamp != null) {
    instant = new Date(timestamp.getTime());
}
*/

public class Concentration {
    StationStub station;
    Particle particle;
    Date instant;
    double value;

    public Concentration() {

    }

    public Concentration(StationStub station, Particle particle, Date date, double value) {
        this.station = station;
        this.particle = particle;
        this.instant = date;
        this.value = value;
    }

    public StationStub getStation() {
        return station;
    }

    public void setStation(StationStub station) {
        this.station = station;
    }

    public Particle getParticle() {
        return particle;
    }

    public void setParticle(Particle particle) {
        this.particle = particle;
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
        return "Concentration [station=" + station.getName() + ", particle=" + particle.getName() + ", instant=" + instant + ", value=" + value +  
                "]" ;
    }

    public int getStationId() {
        return station.getId();
    }

    public String getParticleName() {
        return particle.getName();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((instant == null) ? 0 : instant.hashCode());
        result = prime * result + ((particle == null) ? 0 : particle.hashCode());
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
        if (particle == null) {
            if (other.particle != null)
                return false;
        } else if (!particle.equals(other.particle))
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
}

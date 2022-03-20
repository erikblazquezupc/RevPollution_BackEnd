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
}

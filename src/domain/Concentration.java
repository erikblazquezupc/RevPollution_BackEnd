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

    public Concentration(StationStub station, Particle p, Date d, double v) {
        this.station = station;
        this.particle = p;
        this.instant = d;
        this.value = v;
    }

    public StationStub getStation() {
        return station;
    }

    public void setStation(StationStub s) {
        station = s;
    }

    public Particle getParticle() {
        return particle;
    }

    public void setParticle(Particle p) {
        particle = p;
    }

    public Date getInstant() {
        return instant;
    }

    public void setInstant(Date d) {
        instant = d;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double v) {
        value = v;
    }

    @Override
    public String toString() {
        return "Concentration [station=" + station + ", particle=" + particle + ", instant=" + instant + ", value=" + value +  
                "]" ;
    }
}

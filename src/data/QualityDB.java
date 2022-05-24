package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import domain.Quality;
import domain.Concentration;
import domain.Particle;
import domain.StationStub;
import domain.dataCtrl.QualityDataCtrl;

import java.util.Date;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class QualityDB implements QualityDataCtrl {
    static QualityDB instance;
    Connection conn;

    //Necesario para los datos históricos
    PreparedStatement selectPastHours;
    PreparedStatement selectPast;

    //Necesario para la predicción por horas
    PreparedStatement selectPastHours8DaysAgo;
    PreparedStatement selectPastHours7DaysAgo;
    PreparedStatement selectPastHours1DayAgo;

    //Necesario para la predicción diaria
    PreparedStatement selectPast1DayAgo;
    PreparedStatement selectPast8DaysAgo;
    PreparedStatement selectPast6DaysAgo;
    PreparedStatement selectPast5DaysAgo;
    PreparedStatement selectPast4DaysAgo;

    String pastHours = "SELECT * FROM Concentration WHERE instant >= DATE_ADD(NOW(), INTERVAL -3 DAY) AND instant <= NOW() AND idStation = ?";
    String past = "SELECT * FROM Concentration WHERE CAST(instant AS DATE) >= DATE_ADD(CAST(NOW() AS DATE), INTERVAL -3 DAY) AND CAST(instant AS DATE) < CAST(NOW() AS DATE) AND idStation = ?";

    String pastHours8DaysAgo = "SELECT * FROM Concentration WHERE instant >= DATE_ADD(NOW(), INTERVAL -8 DAY) AND instant <= DATE_ADD(NOW(), INTERVAL -7 DAY) AND idStation = ?";
    String pastHours7DaysAgo = "SELECT * FROM Concentration WHERE instant >= DATE_ADD(NOW(), INTERVAL -7 DAY) AND instant <= DATE_ADD(NOW(), INTERVAL -6 DAY) AND idStation = ?";
    String pastHours1DayAgo = "SELECT * FROM Concentration WHERE instant >= DATE_ADD(NOW(), INTERVAL -1 DAY) AND instant <= NOW() AND idStation = ?";
  
    String past8DaysAgo = "SELECT * FROM Concentration WHERE CAST(instant AS DATE) >= DATE_ADD(CAST(NOW() AS DATE), INTERVAL -8 DAY) AND CAST(instant AS DATE) < DATE_ADD(CAST(NOW() AS DATE), INTERVAL -7 DAY) AND idStation = ?";
    String past6DaysAgo = "SELECT * FROM Concentration WHERE CAST(instant AS DATE) >= DATE_ADD(CAST(NOW() AS DATE), INTERVAL -6 DAY) AND CAST(instant AS DATE) < DATE_ADD(CAST(NOW() AS DATE), INTERVAL -5 DAY) AND idStation = ?";
    String past5DaysAgo = "SELECT * FROM Concentration WHERE CAST(instant AS DATE) >= DATE_ADD(CAST(NOW() AS DATE), INTERVAL -5 DAY) AND CAST(instant AS DATE) < DATE_ADD(CAST(NOW() AS DATE), INTERVAL -4 DAY) AND idStation = ?";
    String past4DaysAgo = "SELECT * FROM Concentration WHERE CAST(instant AS DATE) >= DATE_ADD(CAST(NOW() AS DATE), INTERVAL -4 DAY) AND CAST(instant AS DATE) < DATE_ADD(CAST(NOW() AS DATE), INTERVAL -3 DAY) AND idStation = ?";
    String past1DayAgo = "SELECT * FROM Concentration WHERE CAST(instant AS DATE) >= DATE_ADD(CAST(NOW() AS DATE), INTERVAL -1 DAY) AND CAST(instant AS DATE) < CAST(NOW() AS DATE) AND idStation = ?";
    
    private QualityDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://10.4.41.56:3306/RevPollution_Dev?allowPublicKeyRetrieval=true&useSSL=false", "dev", "aRqffCdBd9t!");
            selectPastHours = conn.prepareStatement(pastHours);
            selectPast = conn.prepareStatement(past);

            selectPastHours8DaysAgo = conn.prepareStatement(pastHours8DaysAgo);
            selectPastHours7DaysAgo = conn.prepareStatement(pastHours7DaysAgo);
            selectPastHours1DayAgo = conn.prepareStatement(pastHours1DayAgo);
            
            selectPast1DayAgo = conn.prepareStatement(past1DayAgo);
            selectPast8DaysAgo = conn.prepareStatement(past8DaysAgo);
            selectPast6DaysAgo = conn.prepareStatement(past6DaysAgo);
            selectPast5DaysAgo = conn.prepareStatement(past5DaysAgo);
            selectPast4DaysAgo = conn.prepareStatement(past4DaysAgo);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public QualityDB getInstance(){
        if(instance == null) instance = new QualityDB();
        return instance;
    }

    public ArrayList<Quality> selectPastHours(int statId) {
        ArrayList<Quality> ret = new ArrayList<Quality> ();
        try {
            selectPastHours.setInt(1, statId);
            ResultSet r = selectPastHours.executeQuery();
            ret = getQualities(statId, r);
            return ret;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Quality> selectPast(int stationId) {
        ArrayList<Quality> ret = new ArrayList<Quality> ();
        ArrayList<Quality> quals = new ArrayList<Quality>();

        try {
            selectPast.setInt(1, stationId);
            ResultSet r = selectPast.executeQuery();
            quals = getQualities(stationId, r);

            Collections.sort(quals, Quality.dateComp);
            ret = getAverageQualities(stationId, quals);

            return ret;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Quality> selectFutureHours(int stationId) {
        ArrayList<Quality> ret8 = new ArrayList<Quality> ();
        ArrayList<Quality> ret7 = new ArrayList<Quality> ();
        ArrayList<Quality> ret1 = new ArrayList<Quality> ();
        ArrayList<Quality> ret = new ArrayList<Quality> ();
        try {
            selectPastHours8DaysAgo.setInt(1, stationId);
            selectPastHours7DaysAgo.setInt(1, stationId);
            selectPastHours1DayAgo.setInt(1, stationId);
            ResultSet r8 = selectPastHours8DaysAgo.executeQuery();
            ResultSet r7 = selectPastHours7DaysAgo.executeQuery();
            ResultSet r1 = selectPastHours1DayAgo.executeQuery();
            ret8 = getQualities(stationId, r8);
            ret7 = getQualities(stationId, r7);
            ret1 = getQualities(stationId, r1);

            Calendar date8 = Calendar.getInstance();
            Calendar date7 = Calendar.getInstance();
            Calendar date1 = Calendar.getInstance();
            Calendar date = Calendar.getInstance();

            int i8, i7, i1;
            i8 = i7 = i1 = 0;
            for (int i = 0; i < 24; ++i) {
                if (i8 < ret8.size() && i7 < ret7.size() && i1 < ret1.size()) {
                    date8.setTime(ret8.get(i8).getInstant());
                    date7.setTime(ret7.get(i7).getInstant());
                    date1.setTime(ret1.get(i1).getInstant());

                    if (date8.get(Calendar.HOUR_OF_DAY) == date7.get(Calendar.HOUR_OF_DAY)) {
                        if (date7.get(Calendar.HOUR_OF_DAY) == date1.get(Calendar.HOUR_OF_DAY)) {
                            double desv = ret7.get(i8).getValue() - ret8.get(i7).getValue();
                            double value = ret1.get(i1).getValue() + desv;
                            date = date1;
                            date.add(Calendar.DAY_OF_YEAR, 1);
                            Date d = date.getTime();
                            StationStub stat = StationDB.getInstance().select(stationId);
                            Quality q = new Quality(stat, d, value);
                            ret.add(q);
                        }
                        else {
                            if (date7.get(Calendar.HOUR_OF_DAY) > date1.get(Calendar.HOUR_OF_DAY)) {
                                i8 -= 1; i7 -= 1;
                            }
                            else i1 -= 1;
                        }
                    }
                    else {
                        if (date8.get(Calendar.HOUR_OF_DAY) > date7.get(Calendar.HOUR_OF_DAY)) i8 -= 1;
                        else i7 -= 1;
                    }
                    i8 += 1; i7 += 1; i1 += 1;
                }
            }
            return ret;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Quality> selectFuture(int stationId) {
        ArrayList<Quality> ret8 = new ArrayList<Quality> ();
        ArrayList<Quality> ret6 = new ArrayList<Quality> ();
        ArrayList<Quality> ret5 = new ArrayList<Quality> ();
        ArrayList<Quality> ret4 = new ArrayList<Quality> ();
        ArrayList<Quality> ret1 = new ArrayList<Quality> ();
        ArrayList<Quality> ret = new ArrayList<Quality> ();

        try {
            selectPast8DaysAgo.setInt(1, stationId);
            selectPast6DaysAgo.setInt(1, stationId);
            selectPast5DaysAgo.setInt(1, stationId);
            selectPast4DaysAgo.setInt(1, stationId);
            selectPast1DayAgo.setInt(1, stationId);

            ResultSet r8 = selectPast8DaysAgo.executeQuery();
            ResultSet r6 = selectPast6DaysAgo.executeQuery();
            ResultSet r5 = selectPast5DaysAgo.executeQuery();
            ResultSet r4 = selectPast4DaysAgo.executeQuery();
            ResultSet r1 = selectPast1DayAgo.executeQuery();

            ret8 = getQualities(stationId, r8);
            ret6 = getQualities(stationId, r6);
            ret5 = getQualities(stationId, r5);
            ret4 = getQualities(stationId, r4);
            ret1 = getQualities(stationId, r1);

            Collections.sort(ret8, Quality.dateComp);
            Collections.sort(ret6, Quality.dateComp);
            Collections.sort(ret5, Quality.dateComp);
            Collections.sort(ret4, Quality.dateComp);
            Collections.sort(ret1, Quality.dateComp);

            ret8 = getAverageQualities(stationId, ret8);
            ret6 = getAverageQualities(stationId, ret6);
            ret5 = getAverageQualities(stationId, ret5);
            ret4 = getAverageQualities(stationId, ret4);
            ret1 = getAverageQualities(stationId, ret1);

            double desv1 = ret6.get(0).getValue() - ret8.get(0).getValue();
            double desv2 = ret5.get(0).getValue() - ret8.get(0).getValue();
            double desv3 = ret4.get(0).getValue() - ret8.get(0).getValue();

            Calendar date = Calendar.getInstance();

            for (int i = 0; i < 3; ++i) {
                date.setTime(ret1.get(0).getInstant());
                date.add(Calendar.DAY_OF_YEAR, i + 2);
                Date d = date.getTime();
                double v = ret1.get(0).getValue();
                if (i == 0) v += desv1;
                if (i == 1) v += desv2;
                if (i == 2) v += desv3;
                StationStub stat = StationDB.getInstance().select(stationId);
                Quality q = new Quality(stat, d, v);
                ret.add(q);
            }
            return ret;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Qualities por hora
    private ArrayList<Quality> getQualities(int statId, ResultSet r) {
        ArrayList<Concentration> conc = new ArrayList<Concentration> ();
        ArrayList<Quality> ret = new ArrayList<Quality> ();
        try {
            while(r.next()) {
                int stationId = r.getInt("idStation");
                String nameParticle = r.getString("nameParticle");
                Timestamp ts = r.getTimestamp("instant");
                Date date = new Date(ts.getTime());
                double value = r.getDouble("value");
                StationStub stat = StationDB.getInstance().select(stationId);
                Particle part = ParticleDB.getInstance().select(nameParticle);
                Concentration c = new Concentration(stat, part, date, value);
                conc.add(c);
            }
            Collections.sort(conc, Concentration.dateComp);
            Date last = new Date(0);
            double max = -1.0;
            double IPSO2, IPNO2, IPPM10, IPCO, IPO3;
            IPSO2 = IPNO2 = IPPM10 = IPCO = IPO3 = -1.0;
            for (int i = 0; i < conc.size(); ++i) {
                Date currentDate = conc.get(i).getInstant();

                if ((!last.equals(currentDate)) && (i != 0)) {
                    StationStub stat = StationDB.getInstance().select(statId);
                    max = Math.max( Math.max( Math.max ( Math.max (IPO3, IPCO), IPPM10), IPNO2), IPSO2);
                    Quality q = new Quality(stat, last, max);
                    ret.add(q);
                    IPSO2 = IPNO2 = IPPM10 = IPCO = IPO3 = -1.0;
                }
                Concentration curConcentration = conc.get(i);
                if (curConcentration.getParticle().getName().equals("SO2")) {
                    IPSO2 = 0.286 * curConcentration.getValue();
                }
                else if (curConcentration.getParticle().getName().equals("NO2")) {
                    IPNO2 = 0.5 * curConcentration.getValue();
                }
                else if (curConcentration.getParticle().getName().equals("PM10")) {
                    IPPM10 = 1.0 * curConcentration.getValue();
                }
                else if (curConcentration.getParticle().getName().equals("CO")) {
                    IPCO = 10 * curConcentration.getValue();
                }
                else if (curConcentration.getParticle().getName().equals("O3")) {
                    IPO3 = 0.556 * curConcentration.getValue();
                }
                last = currentDate;
            }
            StationStub stat = StationDB.getInstance().select(statId);
            max = Math.max( Math.max( Math.max ( Math.max (IPO3, IPCO), IPPM10), IPNO2), IPSO2);
            Quality q = new Quality(stat, last, max);
            ret.add(q);
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Medias de qualities
    private ArrayList<Quality> getAverageQualities(int stationId, ArrayList<Quality> quals) {
        Date last = new Date(0);
        Date now = new Date();
        double sum = 0;
        double values = 0;

        Calendar dateLast = Calendar.getInstance();
        Calendar dateCurrent = Calendar.getInstance();
        Calendar dateToday = Calendar.getInstance();

        StationStub stat = new StationStub();
        Quality q = new Quality();
        ArrayList<Quality> ret = new ArrayList<Quality> ();

        for (int i = 0; i < quals.size(); ++i) {
            dateToday.setTime(now);
            dateLast.setTime(last);
            dateCurrent.setTime(quals.get(i).getInstant());
            if (dateCurrent.get(Calendar.DAY_OF_MONTH) != dateToday.get(Calendar.DAY_OF_MONTH)) {
                if (i != 0) {
                    if (dateLast.get(Calendar.YEAR) < dateCurrent.get(Calendar.YEAR)) {
                        sum = sum / values;
                        stat = StationDB.getInstance().select(stationId);
                        dateLast.set(Calendar.HOUR_OF_DAY, 0);
                        last = dateLast.getTime();
                        q = new Quality(stat, last, sum);
                        ret.add(q);
                        sum = 0;
                        values = 0;
                    }
                    else if (dateLast.get(Calendar.MONTH) < dateCurrent.get(Calendar.MONTH)) {
                        sum = sum / values;
                        stat = StationDB.getInstance().select(stationId);
                        dateLast.set(Calendar.HOUR_OF_DAY, 0);
                        last = dateLast.getTime();
                        q = new Quality(stat, last, sum);
                        ret.add(q);
                        sum = 0;
                        values = 0;
                    }
                    else if (dateLast.get(Calendar.DAY_OF_MONTH) < dateCurrent.get(Calendar.DAY_OF_MONTH)) {
                        sum = sum / values;
                        stat = StationDB.getInstance().select(stationId);
                        dateLast.set(Calendar.HOUR_OF_DAY, 0);
                        last = dateLast.getTime();
                        q = new Quality(stat, last, sum);
                        ret.add(q);
                        sum = 0;
                        values = 0;
                    }
                }
                sum += quals.get(i).getValue();
                ++values;
                last = quals.get(i).getInstant();
            }
        }
        sum = sum / values;
        stat = StationDB.getInstance().select(stationId);
        dateLast.set(Calendar.HOUR_OF_DAY, 0);
        last = dateLast.getTime();
        q = new Quality(stat, last, sum);
        ret.add(q);

        return ret;
    }
}

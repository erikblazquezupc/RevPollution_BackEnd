package domain;

public class StatisticsCollector {
    static StatisticsCollector instance;

    public static StatisticsCollector getInstance(){
        if(instance == null) instance = new StatisticsCollector();
        return instance;
    }

    public void incrementStatistic(EnumStatistics st, String token){
        System.out.println("Recolectar estadistico " + st + " " + token);
    }
}

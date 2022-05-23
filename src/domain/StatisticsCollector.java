package domain;

import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.LogroDataCtrl;

public class StatisticsCollector {
    static StatisticsCollector instance;

    public static StatisticsCollector getInstance(){
        if(instance == null) instance = new StatisticsCollector();
        return instance;
    }

    public void incrementStatistic(EnumStatistics st, String token){
        System.out.println("Recolectar estadistico " + st + " " + token);
        LogroDataCtrl ldc = DataCtrl.getInstance().getLogroDataCtrl();
        System.out.println(ldc.incrementStatistic(st, token));
    }
}

package domain.controllers;

import domain.EnumStatistics;
import domain.StatisticsCollector;

public class TxIncrementStatistic {
    private String token;
    private String statistic;
    private String result;

    public TxIncrementStatistic(String statistic, String token){
        this.statistic = statistic;
        this.token = token;
        result = statistic;
    }

    public void execute(){
        EnumStatistics stat = EnumStatistics.valueOf(statistic);
        StatisticsCollector.getInstance().incrementStatistic(stat, token);
    }

    public String getResult(){
        return result;
    }
}

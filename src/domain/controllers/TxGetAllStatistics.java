package domain.controllers;

import java.util.ArrayList;

import domain.EnumStatistics;

public class TxGetAllStatistics {
    private ArrayList<EnumStatistics> result;

    public TxGetAllStatistics(){
        result = new ArrayList<EnumStatistics>();
    }

    public void execute(){
        result = EnumStatistics.getAll();
    }

    public ArrayList<EnumStatistics> getResult(){
        return result;
    }
}

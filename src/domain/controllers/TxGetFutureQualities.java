package domain.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import domain.Quality;
import domain.SystemState;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.QualityDataCtrl;

public class TxGetFutureQualities {
    private int idStation; 
    private ArrayList<Quality> result;
 
    public TxGetFutureQualities(int idStation){
        this.idStation = idStation;
    }

    public void execute(){
        SystemState sy = SystemState.getInstance();
        if(!sy.existsStationFutureQuality(idStation)){
            DataCtrl dataCtrl = DataCtrl.getInstance();
            QualityDataCtrl qdc = dataCtrl.getQualityDataCtrl();
            result = qdc.selectFuture(idStation);
            sy.addStationFutureQualities(idStation, result);
        }
        
        else {

            Date now = new Date(); 
            Calendar cal = Calendar.getInstance();
            cal.setTime(now);

            ArrayList<Quality> quals = sy.getStationFutureQualities(idStation);

            if (quals.size() > 0) {
                Date qDate = quals.get(0).getInstant();
                Calendar cal2 = Calendar.getInstance();
                cal2.setTime(qDate);
                if ((cal.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH))
                    && (cal.get(Calendar.HOUR_OF_DAY) < 5)) {
                    DataCtrl dataCtrl = DataCtrl.getInstance();
                    QualityDataCtrl qdc = dataCtrl.getQualityDataCtrl();

                    sy.deleteStationFutureQualities(idStation);
                    result = qdc.selectFuture(idStation);
                    sy.addStationFutureQualities(idStation, result);
                }
                else result = sy.getStationFutureQualities(idStation);
            }
            else result = sy.getStationFutureQualities(idStation);
        }
    }

    public ArrayList<Quality> getResult(){
        return result;
    }
}

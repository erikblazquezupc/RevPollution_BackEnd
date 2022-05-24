package domain;

import java.util.ArrayList;

public enum EnumStatistics {
    LogIn,
    ShareStation,
    ShareAchivement,
    ViewStation,
    AchiveAchievement;

    public static ArrayList<EnumStatistics> getAll(){
        ArrayList<EnumStatistics> res = new ArrayList<EnumStatistics>();
        res.add(LogIn);
        res.add(ShareStation);
        res.add(ShareAchivement);
        res.add(ViewStation);
        res.add(AchiveAchievement);
        return res;
    }
}

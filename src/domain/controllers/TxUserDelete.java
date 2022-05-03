package domain.controllers;
import domain.User;
import domain.dataCtrl.DataCtrl;
import domain.dataCtrl.UserDataCtrl;

public class TxUserDelete {
    private String idUser;
    private boolean result;

    public TxUserDelete(String idUser) {
        this.idUser = idUser;
        this.result = false;
    }

    public void execute() {
        DataCtrl dataCtrl = DataCtrl.getInstance();
        UserDataCtrl userCtrl = dataCtrl.getUserDataCtrl();
        userCtrl.delete(Integer.parseInt(idUser));
        User hasBeenDeleted = userCtrl.select(Integer.parseInt(idUser));
        if (hasBeenDeleted == null) {
            result = true;
        }

    }
    
    public boolean getResult() {
        return result;
    }
}

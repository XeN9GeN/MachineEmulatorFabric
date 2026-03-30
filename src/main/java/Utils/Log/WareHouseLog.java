package Utils.Log;

import Model.Observers.WarehouseObserver;

public class WareHouseLog extends MainLogger implements WarehouseObserver {

    @Override
    public void updateWare(String n, int size, int cap){
        WareHouseLog.info(String.format("[%s STORAGE] update | Count: %d/%d",
                n, size, cap));
    }
}

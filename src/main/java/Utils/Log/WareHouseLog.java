package Utils.Log;

public class WareHouseLog extends MainLogger{

    @Override
    public void update(String n, int size, int cap){
        WareHouseLog.info(String.format("[%s STORAGE] update | Count: %d/%d",
                n, size, cap));
    }
}

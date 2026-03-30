package Utils.Log;


public class FactoryLog extends  MainLogger{

    @Override
    public void update(String n, int size, int cap){
        WareHouseLog.info(String.format("[STORAGE] %s update | Count: %d/%d",
                n, size, cap));
    }

}

package Utils.ExectueMain;


import Utils.FIleWork.Config;

public class FactoryConfiguration{
    private final Config config;


    public FactoryConfiguration(Config c ){
        this.config=c;
    }

    public int getBodyWarehouseSize() { return config.getInt("BodyWarehouseSize"); }
    public int getEngineWarehouseSize() { return config.getInt("EngineWarehouseSize"); }
    public int getAccessoryWarehouseSize() { return config.getInt("AccessoryWarehouseSize"); }
    public int getProductWarehouseSize() { return config.getInt("ProductWarehouseSize"); }
    public int getAccessorySuppliers() { return config.getInt("AccessorySuppliers"); }
    public int getWorkersAmount() { return config.getInt("WorkersAmount"); }
    public int getDealersAmount() { return config.getInt("DealersAmount"); }
    public boolean isLogSale() { return config.getBol("LogSale"); }
}

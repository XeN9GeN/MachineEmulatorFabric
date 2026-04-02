package Utils.ExectueMain;

import Model.FactoryComponents.Details.Accessory;
import Model.FactoryComponents.Details.Body;
import Model.FactoryComponents.Details.Engine;
import Model.FactoryComponents.FinishedProducts.Car;
import Model.FactoryComponents.Warehouse.Warehouse;
import Utils.FIleWork.Config;
import Utils.Log.FactoryLog;
import Utils.Log.WareHouseLog;
import View.FabricPanel;

public class initializer {
    private final FactoryConfiguration factoryConfiguration;
    private final WarehouseConfiguration warehouseConfiguration;
    private final DealerConfiguration dealerConfiguration;
    private final SupplierConfiguration supplierConfiguration;

    private final FabricPanel gui;
    private final FactoryLog factoryLog;
    private final WareHouseLog wareHouseLog;

    private Warehouse<Body> bodyWarehouse;
    private Warehouse<Engine> engineWarehouse;
    private Warehouse<Accessory> accessoryWarehouse;
    private  Warehouse<Car> carWarehouse;


    public initializer(){
        this.factoryConfiguration = new FactoryConfiguration(new Config());
        this.warehouseConfiguration = new WarehouseConfiguration();
        this.dealerConfiguration = new DealerConfiguration(factoryConfiguration);
        this.supplierConfiguration = new SupplierConfiguration(factoryConfiguration);

        this.gui = new FabricPanel();
        this.factoryLog = new FactoryLog();
        this.wareHouseLog = new WareHouseLog();
    }


}

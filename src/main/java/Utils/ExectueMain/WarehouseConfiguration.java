package Utils.ExectueMain;

import Model.FactoryComponents.Details.Accessory;
import Model.FactoryComponents.Details.Body;
import Model.FactoryComponents.Details.Engine;
import Model.FactoryComponents.FinishedProducts.Car;
import Model.FactoryComponents.Warehouse.Warehouse;
import Utils.Log.WareHouseLog;
import View.FabricPanel;

public class WarehouseConfiguration {

    public Warehouse<Body> createBodyWarehouse(FactoryConfiguration c, WareHouseLog whl, FabricPanel gui){
        Warehouse<Body> bodyWarehouse = new Warehouse<>("BODY",c.getBodyWarehouseSize());
        bodyWarehouse.addObs(whl);
        bodyWarehouse.addObs(gui.addObsBar(gui.getBodyBar()));
        return bodyWarehouse;
    }

    public Warehouse<Engine> createEngineWarehouse(FactoryConfiguration c, WareHouseLog whl, FabricPanel gui) {
        Warehouse<Engine> engineWarehouse = new Warehouse<>("Engine", c.getEngineWarehouseSize());
        engineWarehouse.addObs(whl);
        engineWarehouse.addObs(gui.addObsBar(gui.getEngineBar()));
        return engineWarehouse;
    }

    public Warehouse<Accessory> createAccessoryWarehouse(FactoryConfiguration c, WareHouseLog whl, FabricPanel gui){
        Warehouse<Accessory> accessoryWarehouse = new Warehouse<>("Accessory", c.getAccessoryWarehouseSize());
        accessoryWarehouse.addObs(whl);
        accessoryWarehouse.addObs(gui.addObsBar(gui.getAccessoryBar()));
        return accessoryWarehouse;
    }

    public Warehouse<Car> createCarWarehouse(FactoryConfiguration c, WareHouseLog whl, FabricPanel gui) {
        Warehouse<Car> warehouse = new Warehouse<>("Cars", c.getProductWarehouseSize());
        warehouse.addObs(whl);
        warehouse.addObs(gui.addObsBar(gui.getCarBar()));
        return warehouse;
    }
}

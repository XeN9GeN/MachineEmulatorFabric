package Utils.ExectueMain;

import Model.FactoryComponents.Details.Accessory;
import Model.FactoryComponents.Details.Body;
import Model.FactoryComponents.Details.Engine;
import Model.FactoryComponents.FinishedProducts.Car;
import Model.FactoryComponents.Warehouse.Warehouse;
import Utils.Log.WareHouseLog;
import View.FabricPanel;
import View.FactoryInterface.ComponentsPanel;

public class WarehouseConfiguration {

    public Warehouse<Body> createBodyWarehouse(FactoryConfiguration c, WareHouseLog whl, FabricPanel gui, ComponentsPanel cp){
        Warehouse<Body> bodyWarehouse = new Warehouse<>("Body",c.getBodyWarehouseSize());
        bodyWarehouse.addObs(whl);
        bodyWarehouse.addObs(gui.addObsBar(gui.getBodyBar()));
        bodyWarehouse.addObs(cp);
        return bodyWarehouse;
    }

    public Warehouse<Engine> createEngineWarehouse(FactoryConfiguration c, WareHouseLog whl, FabricPanel gui, ComponentsPanel cp) {
        Warehouse<Engine> engineWarehouse = new Warehouse<>("Engine", c.getEngineWarehouseSize());
        engineWarehouse.addObs(whl);
        engineWarehouse.addObs(gui.addObsBar(gui.getEngineBar()));
        engineWarehouse.addObs(cp);
        return engineWarehouse;
    }

    public Warehouse<Accessory> createAccessoryWarehouse(FactoryConfiguration c, WareHouseLog whl, FabricPanel gui,ComponentsPanel cp){
        Warehouse<Accessory> accessoryWarehouse = new Warehouse<>("Accessory", c.getAccessoryWarehouseSize());
        accessoryWarehouse.addObs(whl);
        accessoryWarehouse.addObs(gui.addObsBar(gui.getAccessoryBar()));
        accessoryWarehouse.addObs(cp);
        return accessoryWarehouse;
    }

    public Warehouse<Car> createCarWarehouse(FactoryConfiguration c, WareHouseLog whl, FabricPanel gui, ComponentsPanel cp) {
        Warehouse<Car> warehouse = new Warehouse<>("Cars", c.getProductWarehouseSize());
        warehouse.addObs(whl);
        warehouse.addObs(gui.addObsBar(gui.getCarBar()));
        warehouse.addObs(cp);
        return warehouse;
    }
}

import Controller.WarehouseController;
import Model.FactoryComponents.Dealers.Dealer;
import Model.FactoryComponents.Details.*;
import Model.FactoryComponents.FinishedProducts.Car;
import Model.FactoryComponents.Suppliers.*;
import Model.FactoryComponents.Warehouse.Warehouse;
import Utils.Config;
import Utils.Log.FactoryLog;
import Utils.Log.MainLogger;
import Utils.Log.WareHouseLog;
import Utils.ThreadPool;
import View.FabricPanel;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        WareHouseLog wareHouseLog = new WareHouseLog();
        FactoryLog factoryLog = new FactoryLog();


        int configBodyWarehouseSize = config.getInt("BodyWarehouseSize");
        int configEngineWarehouseSize = config.getInt("EngineWarehouseSize");
        int configAccessoryWarehouseSize = config.getInt("AccessoryWarehouseSize");
        int configProductWarehouseSize = config.getInt("ProductWarehouseSize");
        int configAccessorySuppliers = config.getInt("AccessorySuppliers");
        int configWorkersAmount = config.getInt("WorkersAmount");
        int configDealersAmount = config.getInt("DealersAmount");
        boolean b = config.getBol("LogSale");



        Warehouse<Body> bodyWarehouse = new Warehouse<>("Body", configBodyWarehouseSize);
        Warehouse<Engine> engineWarehouse = new Warehouse<>("Engine", configEngineWarehouseSize);
        Warehouse<Accessory> accessoryWarehouse = new Warehouse<>("Accessory", configAccessoryWarehouseSize);
        Warehouse<Car> carsWarehouse = new Warehouse<>("Cars", configProductWarehouseSize);

        bodyWarehouse.addObs(wareHouseLog);
        engineWarehouse.addObs(wareHouseLog);
        accessoryWarehouse.addObs(wareHouseLog);
        carsWarehouse.addObs(wareHouseLog);

        FabricPanel gui = new FabricPanel();
        bodyWarehouse.addObs(gui.addObsBar(gui.getBodyBar()));
        engineWarehouse.addObs(gui.addObsBar(gui.getEngineBar()));
        accessoryWarehouse.addObs(gui.addObsBar(gui.getAccessoryBar()));
        carsWarehouse.addObs(gui.addObsBar(gui.getCarBar()));





        ThreadPool w_pool = new ThreadPool(configWorkersAmount);
        ThreadPool as_pool = new ThreadPool(configAccessorySuppliers);
        ThreadPool d_pool = new ThreadPool(configDealersAmount);


        for (int i = 0; i < configDealersAmount; i++) {
            int dealerId = i;
            d_pool.submit(new Dealer(carsWarehouse, (Car c) -> {
                String time = LocalTime.now().format(DateTimeFormatter.ofPattern("H:mm:ss"));

                String logMessage = String.format(
                        "Time: %s Dealer %d: Auto %d (Body: %d, Motor: %d, Accessory: %d)",
                        time,
                        dealerId,c.getCarID(),c.getBody().getId(), c.getEngine().getId(),c.getAccessory().getId()
                );

                MainLogger.info(logMessage);
            }, 10000));
        }

        Supplier<Body> bodySupplier = new Supplier<>(bodyWarehouse,1000,() -> new Body());
        Supplier<Engine> engineSupplier = new Supplier<>(engineWarehouse,1000, () -> new Engine());
        for(int i=0;i<configAccessorySuppliers;i++){
            as_pool.submit(new Supplier<>(accessoryWarehouse, 1000,() -> new Accessory()));
        }

        WarehouseController warehouseController = new WarehouseController(carsWarehouse, bodyWarehouse,
                engineWarehouse,accessoryWarehouse, w_pool);
        warehouseController.addObs(factoryLog);

        new Thread(bodySupplier).start();
        new Thread(engineSupplier).start();
        new Thread(warehouseController).start();
    }
}
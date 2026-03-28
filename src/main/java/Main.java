
import Controller.WarehouseController;
import Model.FactoryComponents.Dealers.Dealer;
import Model.FactoryComponents.Details.*;
import Model.FactoryComponents.FinishedProducts.Car;
import Model.FactoryComponents.Suppliers.*;
import Model.FactoryComponents.Warehouse.Warehouse;
import Utils.Config;
import Utils.FactLogger;
import Utils.ThreadPool;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();

        int configBodyWarehouseSize = config.getInt("BodyWarehouseSize");
        int configEngineWarehouseSize = config.getInt("EngineWarehouseSize");
        int configAccessoryWarehouseSize = config.getInt("AccessoryWarehouseSize");
        int configProductWarehouseSize = config.getInt("ProductWarehouseSize");
        int configAccessorySuppliers = config.getInt("AccessorySuppliers");
        int configWorkersAmount = config.getInt("WorkersAmount");
        int configDealersAmount = config.getInt("DealersAmount");
        boolean b = config.getBol("LogSale");



        Warehouse<Body> bodyWarehouse = new Warehouse<>(configBodyWarehouseSize);
        Warehouse<Engine> engineWarehouse = new Warehouse<>(configEngineWarehouseSize);
        Warehouse<Accessory> accessoryWarehouse = new Warehouse<>(configAccessoryWarehouseSize);
        Warehouse<Car> carsWarehouse = new Warehouse<>(configProductWarehouseSize);

        Supplier<Body> bodySupplier = new Supplier<>(bodyWarehouse,1000,(id) -> new Body(id));
        Supplier<Engine> engineSupplier = new Supplier<>(engineWarehouse,1000, (id) -> new Engine(id));



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

                FactLogger.info(logMessage);
            }, 10000));
        }
        for(int i=0;i<configAccessorySuppliers;i++){
            as_pool.submit(new Supplier<>(accessoryWarehouse, 1000,(id) -> new Accessory(id)));
        }

        WarehouseController warehouseController = new WarehouseController(carsWarehouse, bodyWarehouse,
                engineWarehouse,accessoryWarehouse, w_pool);

        new Thread(bodySupplier).start();
        new Thread(engineSupplier).start();
        new Thread(warehouseController).start();
    }
}
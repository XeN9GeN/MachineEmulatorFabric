
import Controller.WarehouseController;
import Model.FactoryComponents.Dealers.Dealer;
import Model.FactoryComponents.Details.*;
import Model.FactoryComponents.FinishedProducts.Cars;
import Model.FactoryComponents.Suppliers.*;
import Model.FactoryComponents.Warehouse.Warehouse;
import Model.FactoryComponents.Workers.Worker;
import Utils.Config;

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



        Warehouse<Body> bodyWarehouse = new Warehouse<>(configBodyWarehouseSize);
        Warehouse<Engine> engineWarehouse = new Warehouse<>(configEngineWarehouseSize);
        Warehouse<Accessory> accessoryWarehouse = new Warehouse<>(configAccessoryWarehouseSize);
        Warehouse<Cars> carsWarehouse = new Warehouse<>(configProductWarehouseSize);

        Supplier<Body> bodySupplier = new Supplier<>(bodyWarehouse,1000,(id) -> new Body(id));
        Supplier<Engine> engineSupplier = new Supplier<>(engineWarehouse,1000, (id) -> new Engine(id));



        ExecutorService w_pool = Executors.newFixedThreadPool(configWorkersAmount);
        ExecutorService as_pool = Executors.newFixedThreadPool(configAccessorySuppliers);
        ExecutorService d_pool = Executors.newFixedThreadPool(configDealersAmount);


        for(int i=0;i<configDealersAmount;i++){
            d_pool.submit(new Dealer(carsWarehouse, (Cars c) -> System.out.println("SOLD"),10000));
        }
        for(int i=0;i<configAccessorySuppliers;i++){
            as_pool.submit(new Supplier<>(accessoryWarehouse, 1000,(id) -> new Accessory(id)));
        }
        WarehouseController warehouseController = new WarehouseController(carsWarehouse, bodyWarehouse,engineWarehouse,accessoryWarehouse, w_pool);
        new Thread(bodySupplier).start();
        new Thread(engineSupplier).start();
        new Thread(warehouseController).start();
    }
}
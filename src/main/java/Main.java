
import Controller.WarehouseController;
import Model.FactoryComponents.Dealers.Dealer;
import Model.FactoryComponents.Details.*;
import Model.FactoryComponents.FinishedProducts.Cars;
import Model.FactoryComponents.Suppliers.*;
import Model.FactoryComponents.Warehouse.Warehouse;
import Model.FactoryComponents.Workers.Worker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Warehouse<Body> bodyWarehouse = new Warehouse<>(10);
        Warehouse<Engine> engineWarehouse = new Warehouse<>(10);
        Warehouse<Accessory> accessoryWarehouse = new Warehouse<>(10);
        Warehouse<Cars> carsWarehouse = new Warehouse<>(10);

        Supplier<Body> bodySupplier = new Supplier<>(bodyWarehouse,100,(id) -> new Body(id));
        Supplier<Engine> engineSupplier = new Supplier<>(engineWarehouse,100, (id) -> new Engine(id));
        Supplier<Accessory> accessorySupplier = new Supplier<>(accessoryWarehouse,100, id -> new Accessory(id));


        Dealer dealer1 = new Dealer(carsWarehouse,(c) -> System.out.println("Car was successfully sold"), 200);
        Dealer dealer2 = new Dealer(carsWarehouse,(c) -> System.out.println("Car was successfully sold"), 100);


        ExecutorService pool = Executors.newFixedThreadPool(10);//10 потоков
        WarehouseController warehouseController = new WarehouseController(carsWarehouse,
                bodyWarehouse,engineWarehouse,accessoryWarehouse, pool);

        Thread myt = new Thread(bodySupplier);
        myt.start();

        new Thread(engineSupplier).start();
        new Thread(accessorySupplier).start();
        new Thread(dealer1).start();
        new Thread(dealer2).start();
        new Thread(warehouseController).start();
    }
}
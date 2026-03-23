
import Model.FactoryComponents.Details.*;
import Model.FactoryComponents.FinishedProducts.Cars;
import Model.FactoryComponents.Suppliers.*;
import Model.FactoryComponents.Warehouse.Warehouse;
import Model.FactoryComponents.Workers.Worker;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Warehouse<Body> bodyWarehouse = new Warehouse<>(10);
        Warehouse<Engine> engineWarehouse = new Warehouse<>(10);
        Warehouse<Accessory> accessoryWarehouse = new Warehouse<>(10);
        Warehouse<Cars> carsWarehouse = new Warehouse<>(10);

        Supplier<Body> bodySupplier = new Supplier<>(bodyWarehouse,100,(id) -> new Body(id));
        Supplier<Engine> engineSupplier = new Supplier<>(engineWarehouse,100, (id) -> new Engine(id));
        Supplier<Accessory> accessorySupplier = new Supplier<>(accessoryWarehouse,100, id -> new Accessory(id));

        Worker worker1 = new Worker(bodyWarehouse,engineWarehouse,accessoryWarehouse, carsWarehouse,
                (b,e,a) -> new Cars(b,e,a),120);
        Worker worker2 = new Worker(bodyWarehouse,engineWarehouse,accessoryWarehouse, carsWarehouse,
                (b,e,a) -> new Cars(b,e,a), 100);

        new Thread(bodySupplier).start();
        new Thread(engineSupplier).start();
        new Thread(accessorySupplier).start();
        new Thread(worker1).start();
        new Thread(worker2).start();
    }
}
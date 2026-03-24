package Controller;

import Model.FactoryComponents.Details.*;
import Model.FactoryComponents.FinishedProducts.Cars;
import Model.FactoryComponents.Warehouse.Warehouse;
import Model.FactoryComponents.Workers.Worker;

import java.util.concurrent.ExecutorService;

public class WarehouseController implements Runnable{
    private final Warehouse<Cars> carWarehouse;
    private final ExecutorService worker_pool;

    private final Warehouse<Body> bodyWarehouse;
    private final Warehouse<Engine> engineWarehouse;
    private final Warehouse<Accessory> accessoryWarehouse;

    public WarehouseController(Warehouse<Cars> carWarehouse, Warehouse<Body> bw, Warehouse<Engine> ew,
                               Warehouse<Accessory> aw, ExecutorService workers) {
        this.carWarehouse = carWarehouse;
        this.bodyWarehouse = bw;
        this.engineWarehouse = ew;
        this.accessoryWarehouse = aw;
        this.worker_pool = workers;
    }


    @Override
    public void run(){
        try{
            while (!Thread.currentThread().isInterrupted()){
                synchronized (carWarehouse){
                    while (carWarehouse.isFull()){
                        carWarehouse.wait();
                    }
                }

                //Создать Worker -> передать в submit -> worker_pull в свободном потоке вызывает задачу Runnable(run) внутри него
                //sumbit принимает только объект типа Runnable
                //Runnable task = queue.take();
                //task.run();

                worker_pool.submit(new Worker(bodyWarehouse,engineWarehouse,accessoryWarehouse, carWarehouse,
                        (b,e,a) -> new Cars(b,e,a),500));
                //вместо Executor.execute(Runnable);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}

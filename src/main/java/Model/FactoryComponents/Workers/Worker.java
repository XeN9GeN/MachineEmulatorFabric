package Model.FactoryComponents.Workers;

import Model.FactoryComponents.Details.Accessory;
import Model.FactoryComponents.Details.Body;
import Model.FactoryComponents.Details.Engine;
import Model.FactoryComponents.FinishedProducts.Car;
import Model.FactoryComponents.Warehouse.Warehouse;
import Utils.FactLogger;

public class Worker implements Runnable{
  private final Warehouse<Body> bodyWarehouse;
  private final Warehouse<Engine> engineWarehouse;
  private final Warehouse<Accessory> accessoryWarehouse;
  private final Warehouse<Car> carsWarehouse;
  private final CarCreator carCreator;
  private static int totalWorkers=0;
  private final int workerID;
  private final int delay;

  public Worker(Warehouse<Body> bw, Warehouse<Engine> ew, Warehouse<Accessory> aw, Warehouse<Car> c,
                CarCreator i, int d){
      this.bodyWarehouse=bw;
      this.engineWarehouse=ew;
      this.accessoryWarehouse=aw;
      this.carsWarehouse=c;
      this.carCreator = i;
      this.delay = d;

      totalWorkers++;
      this.workerID=totalWorkers;
  }

  @Override
  public void run(){
      try{
          Engine e = engineWarehouse.take();
          Body b = bodyWarehouse.take();
          Accessory a = accessoryWarehouse.take();

          Car t = carCreator.doCar(b,e,a);
          sendCar(t);

          FactLogger.info(String.format("[WORKER] #%d finished Car #%d | Total:%d%n", workerID, t.getCarID(), Car.getTotal()));
          Thread.sleep(delay);

      }  catch (InterruptedException e) {
          Thread.currentThread().interrupt();
      }
  }

  public void sendCar(Car c){
      try {
          carsWarehouse.put(c);
      } catch (InterruptedException e) {
          throw new RuntimeException(e);
      }
  }



}

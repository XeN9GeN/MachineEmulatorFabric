package Model.FactoryComponents.Workers;

import Model.FactoryComponents.Details.Accessory;
import Model.FactoryComponents.Details.Body;
import Model.FactoryComponents.Details.Engine;
import Model.FactoryComponents.FinishedProducts.Car;
import Model.FactoryComponents.Warehouse.Warehouse;
import Model.Observers.FactoryObserver;

import java.util.ArrayList;
import java.util.List;


public class Worker implements Runnable{
  private List<FactoryObserver> obs = new ArrayList<>();
  private final Warehouse<Accessory> accessoryWarehouse;
  private final Warehouse<Engine> engineWarehouse;
  private final Warehouse<Body> bodyWarehouse;
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

  public void setObs(List<FactoryObserver> obs){
      this.obs = obs;
  }

  @Override
  public void run(){
      try{
          Engine e = engineWarehouse.take();
          Body b = bodyWarehouse.take();
          Accessory a = accessoryWarehouse.take();

          Car t = carCreator.doCar(b,e,a);
          for (FactoryObserver o : obs) {
              o.updateCar(t.getCarID(), Car.getTotal());
          }
          sendCar(t);
          for (FactoryObserver o : obs) {
              o.updateWorker(workerID, t, Car.getTotal());
          }
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

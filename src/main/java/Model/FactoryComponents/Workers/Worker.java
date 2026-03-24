package Model.FactoryComponents.Workers;

import Model.FactoryComponents.Details.Accessory;
import Model.FactoryComponents.Details.Body;
import Model.FactoryComponents.Details.Engine;
import Model.FactoryComponents.FinishedProducts.Cars;
import Model.FactoryComponents.Warehouse.Warehouse;

public class Worker implements Runnable{
  private final Warehouse<Body> bodyWarehouse;
  private final Warehouse<Engine> engineWarehouse;
  private final Warehouse<Accessory> accessoryWarehouse;
  private final Warehouse<Cars> carsWarehouse;
  private final CarCreator carCreator;
  private static int totalWorkers=0;
  private int workerID=0;
  private final int delay;

  public Worker(Warehouse<Body> bw, Warehouse<Engine> ew, Warehouse<Accessory> aw, Warehouse<Cars> c,
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

          Cars t = carCreator.doCar(b,e,a);
          System.out.println("Car was successfully created by " + workerID + " worker");
          sendCar(t);
          System.out.println("Car was successfully sent on a finished product warehouse");
          Thread.sleep(delay);

      }  catch (InterruptedException e) {
          Thread.currentThread().interrupt();
      }
  }

  public void sendCar(Cars c){
      try {
          carsWarehouse.put(c);
      } catch (InterruptedException e) {
          throw new RuntimeException(e);
      }
  }



}

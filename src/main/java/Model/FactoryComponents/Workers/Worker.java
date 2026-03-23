package Model.FactoryComponents.Workers;

import Model.FactoryComponents.Details.Accessory;
import Model.FactoryComponents.Details.Body;
import Model.FactoryComponents.Details.Detail;
import Model.FactoryComponents.Details.Engine;
import Model.FactoryComponents.FinishedProducts.Cars;
import Model.FactoryComponents.Warehouse.Warehouse;

public class Worker<T extends Detail> implements Runnable{
  private final Warehouse<Body> bodyWarehouse;
  private final Warehouse<Engine> engineWarehouse;
  private final Warehouse<Accessory> accessoryWarehouse;
  private final Warehouse<Cars> carsWarehouse;
  private final int delay;

  public Worker(Warehouse<Body> bw, Warehouse<Engine> ew, Warehouse<Accessory> aw, Warehouse<Cars> c, int d){
      this.bodyWarehouse=bw;
      this.engineWarehouse=ew;
      this.accessoryWarehouse=aw;
      this.carsWarehouse=c;
      this.delay = d;
  }

  @Override
  public void run(){
      try{
          while (!Thread.currentThread().isInterrupted()){
             Engine e = engineWarehouse.take();
             Body b = bodyWarehouse.take();
             Accessory a = accessoryWarehouse.take();
             Cars t = doCar(b,e,a);
             System.out.println("Car was successfully created");
             sendCar(t);
             System.out.println("Car was successfully sent on a finished product warehouse");
             Thread.sleep(delay);
          }
      } catch (RuntimeException | InterruptedException e) {
          throw new RuntimeException(e);
      }
  }

  public Cars doCar(Body detail1, Engine detail2, Accessory detail3){
      return new Cars(detail1,detail2,detail3);
  }

  public void sendCar(Cars c){
      try {
          carsWarehouse.put(c);
      } catch (InterruptedException e) {
          throw new RuntimeException(e);
      }
  }



}

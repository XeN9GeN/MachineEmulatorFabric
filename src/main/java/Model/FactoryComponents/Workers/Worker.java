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
  private final Cars cars;

  public Worker(Warehouse<Body> bw, Warehouse<Engine> ew, Warehouse<Accessory> aw, Cars c){
      this.bodyWarehouse=bw;
      this.engineWarehouse=ew;
      this.accessoryWarehouse=aw;
      this.cars=c;
  }

  @Override
  public void run(){
      try{
          while (!Thread.currentThread().isInterrupted()){
              try{

              }
          }
      }

  }



}

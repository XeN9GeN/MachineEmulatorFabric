package Model.FactoryComponents.FinishedProducts;

import Model.FactoryComponents.Details.Accessory;
import Model.FactoryComponents.Details.Body;
import Model.FactoryComponents.Details.Engine;

import java.util.concurrent.atomic.AtomicInteger;

public class Car {
    private final Engine e;
    private final Body b;
    private final Accessory a;

    //Два потока могут одновременно делать new Car(), одновременно прочитать один и тот же ID
    //и будет так, что у двух разных машин одинаковое carID. Solved: AtomicInteger
    //без остановки потоков и всяких wait()
    private static final AtomicInteger totalCounter = new AtomicInteger(0);
    private static final AtomicInteger carIDgen = new AtomicInteger(0);
    private final int carID;

    public Car(Body d1, Engine d2, Accessory d3){
        this.b=d1;
        this.e=d2;
        this.a=d3;
        this.carID = carIDgen.incrementAndGet();//во время while поток может улететь и запишется прошлое значение
        totalCounter.incrementAndGet();

        System.out.println("[FACTORY] Car created | ID: " + carID);
    }

    public static int getTotal(){
        return totalCounter.get();
    }
    public int getCarID(){
        return carID;
    }

}

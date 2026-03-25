package Model.FactoryComponents.Dealers;
import Model.FactoryComponents.FinishedProducts.Car;
import Model.FactoryComponents.Warehouse.Warehouse;

public class Dealer implements  Runnable {
    private final Warehouse<Car> carsWarehouse;
    private final Inspection dealingprocess;
    private final int delay;

    public Dealer(Warehouse<Car> c, Inspection i, int d){
        this.carsWarehouse = c;
        this.dealingprocess =i;
        this.delay=d;
    }

    public void run(){
        while (!Thread.currentThread().isInterrupted()){
            try {
                Car c = carsWarehouse.take();
                dealingprocess.sellCar(c);
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}

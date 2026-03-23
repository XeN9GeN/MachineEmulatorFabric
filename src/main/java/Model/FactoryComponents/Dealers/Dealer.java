package Model.FactoryComponents.Dealers;
import Model.FactoryComponents.FinishedProducts.Cars;
import Model.FactoryComponents.Warehouse.Warehouse;

public class Dealer implements  Runnable {
    private final Warehouse<Cars> carsWarehouse;

    public Dealer(Warehouse<Cars> c){
        this.carsWarehouse = c;
    }
    public void run(){

    }

}

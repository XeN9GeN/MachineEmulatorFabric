package Utils.Log;


import Model.FactoryComponents.FinishedProducts.Car;
import Model.Observers.FactoryObserver;

public class FactoryLog extends MainLogger implements FactoryObserver {

    @Override
    public void updateCar(int carID, int totalCreated){
        MainLogger.info("[FACTORY] Car created | ID: " + carID);
    }

    @Override
    public void updateWorker(int workerID, Car car, int total){
        MainLogger.info(String.format("[WORKER] #%d finished Car #%d | Total:%d%n", workerID, car.getCarID(), Car.getTotal()));

    }
}

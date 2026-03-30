package Model.Observers;

import Model.FactoryComponents.FinishedProducts.Car;

public interface FactoryObserver {
    void updateCar(int carID, int totalCreated);
    void updateWorker(int workerID, Car car, int total);
}
    
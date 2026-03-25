package Model.FactoryComponents.Dealers;


import Model.FactoryComponents.FinishedProducts.Car;

@FunctionalInterface
public interface Inspection {
    void sellCar(Car c);
}

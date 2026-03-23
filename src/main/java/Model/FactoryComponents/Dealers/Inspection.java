package Model.FactoryComponents.Dealers;


import Model.FactoryComponents.FinishedProducts.Cars;

@FunctionalInterface
public interface Inspection {
    void sellCar(Cars c);
}

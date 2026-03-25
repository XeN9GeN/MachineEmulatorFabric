package Model.FactoryComponents.Workers;

import Model.FactoryComponents.Details.Accessory;
import Model.FactoryComponents.Details.Body;
import Model.FactoryComponents.Details.Engine;
import Model.FactoryComponents.FinishedProducts.Car;

@FunctionalInterface
public interface CarCreator {
    Car doCar(Body detail1, Engine detail2, Accessory detail3);
}

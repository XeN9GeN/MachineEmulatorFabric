package Model.FactoryComponents.Workers;

import Model.FactoryComponents.Details.Accessory;
import Model.FactoryComponents.Details.Body;
import Model.FactoryComponents.Details.Engine;
import Model.FactoryComponents.FinishedProducts.Cars;

@FunctionalInterface
public interface CarCreator {
    Cars doCar(Body detail1, Engine detail2, Accessory detail3);
}

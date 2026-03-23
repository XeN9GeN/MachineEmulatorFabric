package Model.FactoryComponents.FinishedProducts;

import Model.FactoryComponents.Details.Accessory;
import Model.FactoryComponents.Details.Body;
import Model.FactoryComponents.Details.Detail;
import Model.FactoryComponents.Details.Engine;

import java.util.LinkedList;

public class Cars {
    private final Engine e;
    private final Body b;
    private final Accessory a;
    private static int carID=0;

    public Cars(Body d1,Engine d2,Accessory d3){
        this.b=d1;
        this.e=d2;
        this.a=d3;
        carID++;
        System.out.println("carID: " + carID);
    }
}

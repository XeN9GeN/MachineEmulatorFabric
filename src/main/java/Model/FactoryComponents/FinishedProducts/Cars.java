package Model.FactoryComponents.FinishedProducts;

import Model.FactoryComponents.Details.Accessory;
import Model.FactoryComponents.Details.Body;
import Model.FactoryComponents.Details.Engine;

public class Cars {
    private final int id;
    private Body body;
    private Engine engine;
    private Accessory accessory;
    private int price;


    public Cars(int id, Body b, Engine e, Accessory a) {
        this.id = id;
        this.body =b;
        this.engine=e;
        this.accessory=a;
    }


}

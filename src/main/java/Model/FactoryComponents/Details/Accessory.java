package Model.FactoryComponents.Details;

import java.util.concurrent.atomic.AtomicInteger;

public class Accessory extends Detail {
    private static final AtomicInteger idGen = new AtomicInteger(1);

    public Accessory(){
        super(idGen.getAndIncrement());
    }
}

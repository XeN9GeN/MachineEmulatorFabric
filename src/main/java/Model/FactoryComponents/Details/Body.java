package Model.FactoryComponents.Details;

import java.util.concurrent.atomic.AtomicInteger;

public class Body extends Detail {
    private static final AtomicInteger idGen = new AtomicInteger(1);

    public Body(){
        super(idGen.getAndIncrement());
    }
}

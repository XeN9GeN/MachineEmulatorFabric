package Model.FactoryComponents.Details;

import java.util.concurrent.atomic.AtomicInteger;

public class Engine extends Detail{
    private static final AtomicInteger idGen = new AtomicInteger(1);

    public Engine(){
        super(idGen.getAndIncrement());
    }
}

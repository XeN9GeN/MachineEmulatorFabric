package Model.FactoryComponents.Details;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Detail {
    private final int id;


    public Detail(int idd){
        this.id = idd;
    }

    public int getId() {
        return id;
    }
}

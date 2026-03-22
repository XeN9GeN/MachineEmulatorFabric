package Model.FactoryComponents.Suppliers;

import Model.FactoryComponents.Details.Detail;
import Model.FactoryComponents.Warehouse.Warehouse;

public class Supplier<T extends Detail> implements Runnable{
    private final Warehouse<T> w;
    private final int delay;
    private final Deliver<T> deliver;
    private int idC=0;

    public Supplier(Warehouse<T> w, int delay, Deliver<T> d) {
        this.w=w;
        this.delay=delay;
        this.deliver=d;
    }

    @Override
    public void run(){
        while(!Thread.currentThread().isInterrupted()){
            try{
                T detail = deliver.create(idC++);
                w.put(detail);
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
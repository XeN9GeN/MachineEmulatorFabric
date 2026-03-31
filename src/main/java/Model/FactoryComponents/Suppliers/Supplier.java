package Model.FactoryComponents.Suppliers;

import Model.FactoryComponents.Details.Detail;
import Model.FactoryComponents.Warehouse.Warehouse;

public class Supplier<T extends Detail> implements Runnable{
    private final Warehouse<T> w;
    private volatile int delay;
    private final Deliver<T> deliver;

    public Supplier(Warehouse<T> w, int delay, Deliver<T> d) {
        this.w=w;
        this.delay=delay;
        this.deliver=d;//под функ. интерфейс, лямбда находится в main
    }

    @Override
    public void run(){
        while(!Thread.currentThread().isInterrupted()){
            try{
                T detail = deliver.create();
                w.put(detail);
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


    public void setDelay(int n){
        this.delay = n;
    }
}
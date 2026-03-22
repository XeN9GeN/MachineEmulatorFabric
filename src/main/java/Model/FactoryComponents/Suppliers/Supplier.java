package Model.FactoryComponents.Suppliers;

import Model.FactoryComponents.Details.Detail;
import Model.FactoryComponents.Warehouse.Warehouse;

public class Supplier<T extends Detail> implements Runnable{
    private final Warehouse<T> w;
    private final int delay;
    private final Deliver<T> deliver;
    private int idCounter=0;

    public Supplier(Warehouse<T> w, int d,Deliver<T> deliverLogic){
        this.w=w;
        this.delay=d;
        this.deliver=deliverLogic;//для лямбда функции
    }

    @Override
    public void run(){
        while (!Thread.currentThread().isInterrupted()){
            try {
                T detail = deliver.create(idCounter++);
                w.put(detail);
                Thread.sleep(delay);
            }catch (InterruptedException e ){}
        }
    }
}

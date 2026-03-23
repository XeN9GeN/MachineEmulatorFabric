package Model.FactoryComponents.Warehouse;


import Model.FactoryComponents.Details.Detail;

import java.util.LinkedList;


public class Warehouse<T>{
    private final LinkedList<T> list = new LinkedList<>();
    private final int capacity;

    public Warehouse(int c){ this.capacity =c; }

    public synchronized void put(T item) throws InterruptedException{
        try {
            while (list.size()>=capacity) wait();
        }catch (InterruptedException e){}

        list.add(item);
        System.out.println("Supplier delivered 1 detail");
        System.out.println("Amount in warehouse " + getSize());
        notifyAll();
    }

    public synchronized T take() throws InterruptedException{
        try {
            while (list.isEmpty()) wait();
        }catch (InterruptedException e ){}

        T obj = list.removeFirst();
        System.out.println("Worker took 1 detail\n");
        notifyAll();
        return obj;
    }

    public int getSize(){ return list.size();}



}

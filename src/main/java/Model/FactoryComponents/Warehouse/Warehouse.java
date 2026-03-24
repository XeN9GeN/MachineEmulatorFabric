package Model.FactoryComponents.Warehouse;


import Model.FactoryComponents.Details.Detail;
import Model.FactoryComponents.FinishedProducts.Cars;

import java.util.LinkedList;


public class Warehouse<T>{
    private final LinkedList<T> list = new LinkedList<>();
    private final int capacity;

    public Warehouse(int c){ this.capacity =c; }

    public synchronized void put(T item) throws InterruptedException{
        try {
            while (list.size()>=capacity) wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        list.add(item);
        System.out.println("Supplier delivered 1 item: " + item.getClass().getSimpleName());
        System.out.println("Amount in warehouse " + getSize());
        notifyAll();
    }

    public synchronized T take() throws InterruptedException{
        try {
            while (list.isEmpty()) wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        T obj = list.removeFirst();
        System.out.println("Worker took 1 item");
        notifyAll();
        return obj;
    }

    public int getSize(){ return list.size();}


    public boolean isFull() {
        if(list.size()==capacity) return true;
        return false;
    }
}

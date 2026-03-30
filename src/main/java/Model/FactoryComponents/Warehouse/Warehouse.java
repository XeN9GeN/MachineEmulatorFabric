package Model.FactoryComponents.Warehouse;


import Model.Observers.WarehouseObserver;
import Utils.Log.MainLogger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Warehouse<T>{
    private final LinkedList<T> list = new LinkedList<>();
    private final int capacity;
    private final String name;
    private final List<WarehouseObserver> obs = new ArrayList<>();//Можно приклеить что угодно


    public Warehouse(String n,int c){
        this.name = n;
        this.capacity =c;
    }


    public void addObs(WarehouseObserver o){
        obs.add(o);
        o.update(name, list.size(), capacity);
    }
    private void notifyObs(){
        for(WarehouseObserver o : obs){
            o.update(name, list.size(),capacity);
        }
    }



    public synchronized void put(T item) throws InterruptedException{
        try {
            while (list.size()>=capacity) wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        list.add(item);
        notifyObs();
        notifyAll();
    }

    public synchronized T take() throws InterruptedException{
        try {
            while (list.isEmpty()) wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        T obj = list.removeFirst();
        notifyObs();
        notifyAll();
        return obj;
    }

    public boolean isFull() {
        return list.size() == capacity;
    }
}

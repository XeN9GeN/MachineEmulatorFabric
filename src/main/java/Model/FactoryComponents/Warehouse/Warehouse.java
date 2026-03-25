package Model.FactoryComponents.Warehouse;


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

        String T = item.getClass().getSimpleName();
        if(T.equals("Car")) {
            System.out.println(String.format("[CAR STORAGE] %s posted | %s;PUT;%d", T, T, list.size()));
        }else System.out.println(String.format("[DETAIL STORAGE] %s delivered | %s;PUT;%d", T, T, list.size()));

        notifyAll();
    }

    public synchronized T take() throws InterruptedException{
        try {
            while (list.isEmpty()) wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        T obj = list.removeFirst();

        String T = obj.getClass().getSimpleName();
        if(T.equals("Car")){
            System.out.println(String.format("[CAR STORAGE] %s taken " + "for sale | %s;TAKE;%d", T, T, list.size()));
        }else System.out.println(String.format("[DETAIL STORAGE] %s taken | %s;TAKE;%d", T, T, list.size()));

        notifyAll();
        return obj;
    }

    public boolean isFull() {
        return list.size() == capacity;
    }
}

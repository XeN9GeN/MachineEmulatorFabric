package Model.FactoryComponents.Warehouse;

public interface WarehouseInt<T> {
    //void put(T item);
    //void get();
    int getId();
    int getSize();
    int getCap();
    WarehouseType getType();
}

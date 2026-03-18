package Model.FactoryComponents.Warehouse;

public abstract class WarehouseAbstract implements WarehouseInt {
    private final int id;
    private final int size;
    private final int capacity;
    private final WarehouseType type;

    public WarehouseAbstract(int id, int size, int cap, WarehouseType t){
        this.id=id;
        this.size=size;
        this.capacity = cap;
        this.type = t;
    }

    public int getId(){
        return this.id;
    }
    public int getSize(){
        return this.size;
    }
    public int getCap(){
        return this.capacity;
    }
    public WarehouseType getType(){
        return this.type;
    }
}

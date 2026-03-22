
import Model.FactoryComponents.Details.*;
import Model.FactoryComponents.Suppliers.*;
import Model.FactoryComponents.Warehouse.Warehouse;

public class Main {
    static void main(){
        Warehouse<Body> bodyWarehouse = new Warehouse<>(10);

        Supplier<Body> supplier = new Supplier<>(bodyWarehouse,100,(id) -> new Body(id));

        supplier.run();
    }
}
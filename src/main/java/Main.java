
import Model.FactoryComponents.Details.*;
import Model.FactoryComponents.Suppliers.*;
import Model.FactoryComponents.Warehouse.Warehouse;

public class Main {
    static void main(){
        Warehouse<Body> bodyWarehouse = new Warehouse<>(10);

        Supplier<Body> bodySupplier = new Supplier<>(bodyWarehouse,1000,(id) -> new Body(id));//labmda func

        bodySupplier.run();
    }
}
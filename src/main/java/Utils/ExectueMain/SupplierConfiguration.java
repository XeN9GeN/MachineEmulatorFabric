package Utils.ExectueMain;

import Model.FactoryComponents.Details.Accessory;
import Model.FactoryComponents.Details.Body;
import Model.FactoryComponents.Details.Engine;
import Model.FactoryComponents.Suppliers.Supplier;
import Model.FactoryComponents.Warehouse.Warehouse;
import Utils.ThreadPool;

import java.util.ArrayList;
import java.util.List;

public class SupplierConfiguration {
    private final ArrayList<Supplier<?>> allSupps = new ArrayList<>();
    private final ThreadPool as_poll;
    private final FactoryConfiguration fc;

    public SupplierConfiguration(FactoryConfiguration f) {
        this.fc = f;
        this.as_poll = new ThreadPool(fc.getAccessorySuppliers());
    }



    public Supplier<Body> createBodySupplier(Warehouse<Body> warehouse) {
        Supplier<Body> s = new Supplier<>(warehouse, 1000, () -> new Body());
        allSupps.add(s);
        return s;
    }

    public Supplier<Engine> createEngineSupplier(Warehouse<Engine> warehouse) {
        Supplier<Engine> s = new Supplier<>(warehouse, 1000, () -> new Engine());
        allSupps.add(s);
        return s;
    }

    public void createAccessorySupplier(Warehouse<Accessory> warehouse) {
        for (int i = 0; i < fc.getAccessorySuppliers(); i++) {
            Supplier<Accessory> a = new Supplier<>(warehouse, 1000, () -> new Accessory());
            as_poll.submit(a);
            allSupps.add(a);
        }
    }


    public ArrayList<Supplier<?>> getAllSuppliers() {
        return allSupps;
    }

    public void startSuppsThreads(Supplier<Body> s, Supplier<Engine> e, List<Thread> t) {
        Thread tBody = new Thread(s);
        Thread tEngine = new Thread(e);

        t.add(tBody);
        t.add(tEngine);

        tBody.start();
        tEngine.start();
    }
}
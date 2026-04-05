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
    private final ThreadPool access_pools;
    private final FactoryConfiguration fc;

    public SupplierConfiguration(FactoryConfiguration f, List<Thread> t) {
        this.fc = f;
        this.access_pools = new ThreadPool(fc.getAccessorySuppliers(), t);//ВОТ ЭТО THREAD'Ы ДЛЯ ACC_SUPPS
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
            Supplier<Accessory> a = new Supplier<>(warehouse, 1000, () -> new Accessory());//run task, не Thread!!!
            access_pools.submit(a);
            allSupps.add(a);
        }
    }


    public void startSuppsThreads(Supplier<Body> s, Supplier<Engine> e, List<Thread> t) {
        Thread tBody = new Thread(s);
        Thread tEngine = new Thread(e);

        t.add(tBody);
        t.add(tEngine);

        tBody.start();
        tEngine.start();
    }

    public ArrayList<Supplier<?>> getAllSuppliers() {
        return allSupps;
    }
}
package Utils.ExectueMain;

import Controller.WarehouseController;
import Model.FactoryComponents.Details.Accessory;
import Model.FactoryComponents.Details.Body;
import Model.FactoryComponents.Details.Engine;
import Model.FactoryComponents.FinishedProducts.Car;
import Model.FactoryComponents.Suppliers.Supplier;
import Model.FactoryComponents.Warehouse.Warehouse;
import Utils.FIleWork.CSV;
import Utils.FIleWork.Config;
import Utils.Log.FactoryLog;
import Utils.Log.WareHouseLog;
import Utils.ThreadPool;
import View.AdditPanel.FabricPanel;
import View.AdditPanel.SliderPanel;

import java.util.ArrayList;
import java.util.List;

public class Initializer {
    private final FactoryConfiguration factoryConfiguration;
    private final WarehouseConfiguration warehouseConfiguration;
    private final DealerConfiguration dealerConfiguration;
    private final SupplierConfiguration supplierConfiguration;

    private final FabricPanel gui;
    private SliderPanel slider;
    private final FactoryLog factoryLog;
    private final WareHouseLog wareHouseLog;

    private Warehouse<Body> bodyWarehouse;
    private Warehouse<Engine> engineWarehouse;
    private Warehouse<Accessory> accessoryWarehouse;
    private Warehouse<Car> carWarehouse;

    public static volatile boolean isPaused = false;
    private final List<Thread> threadList = new ArrayList<>();



    public Initializer() {
        this.factoryConfiguration = new FactoryConfiguration(new Config());
        this.warehouseConfiguration = new WarehouseConfiguration();
        this.dealerConfiguration = new DealerConfiguration(factoryConfiguration, threadList);
        this.supplierConfiguration = new SupplierConfiguration(factoryConfiguration, threadList);

        this.gui = new FabricPanel();
        this.factoryLog = new FactoryLog();
        this.wareHouseLog = new WareHouseLog();
        isPaused = false;
    }


    public void GO(){
        storageCreate();
        supplsCreate();
        controllerCreate();
        dealersCreate();
        GUI();
        isPaused = false;
    }

    public void STOP(){
        System.out.println("DONE");
        for(Thread t : threadList){
            t.interrupt();
        }
        factoryLog.close();
        wareHouseLog.close();
        isPaused=true;

        new CSV().BD();
    }



    public void storageCreate() {
        bodyWarehouse = warehouseConfiguration.createBodyWarehouse(factoryConfiguration, wareHouseLog, gui);
        engineWarehouse = warehouseConfiguration.createEngineWarehouse(factoryConfiguration, wareHouseLog, gui);
        accessoryWarehouse = warehouseConfiguration.createAccessoryWarehouse(factoryConfiguration, wareHouseLog, gui);
        carWarehouse = warehouseConfiguration.createCarWarehouse(factoryConfiguration, wareHouseLog, gui);
    }

    public void supplsCreate() {
        Supplier<Body> bodySupplier = supplierConfiguration.createBodySupplier(bodyWarehouse);
        Supplier<Engine> engineSupplier = supplierConfiguration.createEngineSupplier(engineWarehouse);
        supplierConfiguration.createAccessorySupplier(accessoryWarehouse);
        supplierConfiguration.startSuppsThreads(bodySupplier, engineSupplier,threadList);
    }

    public void dealersCreate(){
        dealerConfiguration.createDealers(carWarehouse);
    }

    public void controllerCreate() {
        ThreadPool w_pool = new ThreadPool(factoryConfiguration.getWorkersAmount(), threadList);
        WarehouseController warehouseController = new WarehouseController(carWarehouse, bodyWarehouse,
                engineWarehouse, accessoryWarehouse, w_pool);
        warehouseController.addObs(factoryLog);

        Thread t = new Thread(warehouseController);

        threadList.add(t);
        t.start();
    }



    public void GUI() {
        this.slider = new SliderPanel(supplierConfiguration.getAllSuppliers().toArray(new Supplier[0]));
    }

    public FabricPanel getFabricPanel() {
        return gui;
    }

    public SliderPanel getSlider() {
        return slider;
    }
    public Boolean getPause(){
        return isPaused;
    }
}

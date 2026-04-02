package Utils.ExectueMain;

import Controller.WarehouseController;
import Model.FactoryComponents.Details.Accessory;
import Model.FactoryComponents.Details.Body;
import Model.FactoryComponents.Details.Engine;
import Model.FactoryComponents.FinishedProducts.Car;
import Model.FactoryComponents.Suppliers.Supplier;
import Model.FactoryComponents.Warehouse.Warehouse;
import Utils.FIleWork.Config;
import Utils.Log.FactoryLog;
import Utils.Log.WareHouseLog;
import Utils.ThreadPool;
import View.FabricPanel;
import View.Slider;

import javax.swing.*;

public class Initializer {
    private final FactoryConfiguration factoryConfiguration;
    private final WarehouseConfiguration warehouseConfiguration;
    private final DealerConfiguration dealerConfiguration;
    private final SupplierConfiguration supplierConfiguration;

    private final FabricPanel gui;
    private final FactoryLog factoryLog;
    private final WareHouseLog wareHouseLog;

    private Warehouse<Body> bodyWarehouse;
    private Warehouse<Engine> engineWarehouse;
    private Warehouse<Accessory> accessoryWarehouse;
    private Warehouse<Car> carWarehouse;


    public Initializer() {
        this.factoryConfiguration = new FactoryConfiguration(new Config());
        this.warehouseConfiguration = new WarehouseConfiguration();
        this.dealerConfiguration = new DealerConfiguration(factoryConfiguration);
        this.supplierConfiguration = new SupplierConfiguration(factoryConfiguration);

        this.gui = new FabricPanel();
        this.factoryLog = new FactoryLog();
        this.wareHouseLog = new WareHouseLog();
    }


    public void GO(){
        storageCreate();
        supplsCreate();
        controllerCreate();
        dealersCreate();
        GUI();
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
        supplierConfiguration.startSuppsThreads(bodySupplier, engineSupplier);
    }

    public void controllerCreate() {
        ThreadPool w_pool = new ThreadPool(factoryConfiguration.getWorkersAmount());
        WarehouseController warehouseController = new WarehouseController(carWarehouse, bodyWarehouse,
                engineWarehouse, accessoryWarehouse, w_pool);
        warehouseController.addObs(factoryLog);
        new Thread(warehouseController).start();
    }

    public void dealersCreate(){
        dealerConfiguration.createDealers(carWarehouse);
    }

    public void GUI() {
        SwingUtilities.invokeLater(() -> {
            new Slider(supplierConfiguration.getAllSuppliers().toArray(new Supplier[0]));
        });
    }
}

package Utils.ExectueMain;

import Model.FactoryComponents.Dealers.Dealer;
import Model.FactoryComponents.FinishedProducts.Car;
import Model.FactoryComponents.Warehouse.Warehouse;
import Utils.Log.MainLogger;
import Utils.ThreadPool;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DealerConfiguration {
    private final FactoryConfiguration fc;
    private final ThreadPool d_pool;

    public DealerConfiguration(FactoryConfiguration f, List<Thread> t){
        this.fc =f;
        this.d_pool = new ThreadPool(fc.getDealersAmount(), t);
    }



    public void createDealers(Warehouse<Car> c){
        for(int i=0;i<fc.getDealersAmount();i++){
            int dealerID=i;
            d_pool.submit(new Dealer(c, (Car car) -> {
                String time = LocalTime.now().format(DateTimeFormatter.ofPattern("H:mm:ss"));

                String logMessage = String.format(
                        "Time: %s Dealer %d: Auto %d (Body: %d, Motor: %d, Accessory: %d)",
                        time,
                        dealerID, car.getCarID(), car.getBody().getId(), car.getEngine().getId(), car.getAccessory().getId()
                );
                MainLogger.info(logMessage);
            },10000));
        }
    }
}

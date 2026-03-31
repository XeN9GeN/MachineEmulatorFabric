package View;

import Controller.WarehouseController;
import Model.Observers.WarehouseObserver;

import javax.swing.*;
import java.awt.*;

public class FabricPanel extends JFrame {
    private final JProgressBar bodyBar = new JProgressBar();
    private final JProgressBar engineBar = new JProgressBar();
    private final JProgressBar accessoryBar = new JProgressBar();
    private final JProgressBar carBar = new JProgressBar();

    public FabricPanel(){
        setTitle("Factory Monitor");
        setSize(400,300);
        setLayout(new GridLayout(4,2));

        add(new JLabel(" Bodies:")); add(bodyBar);
        add(new JLabel(" Engines:")); add(engineBar);
        add(new JLabel(" Accessories:")); add(accessoryBar);
        add(new JLabel(" Cars:")); add(carBar);

        setupBar(bodyBar);
        setupBar(engineBar);
        setupBar(accessoryBar);
        setupBar(carBar);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setupBar(JProgressBar bar){
        bar.setStringPainted(true);
        bar.setForeground(new Color(50,150,50));
    }

    public WarehouseObserver addObsBar(JProgressBar bar) {
        return (name, current, max) -> {
            //метод updateWare
            SwingUtilities.invokeLater(() -> {
                bar.setMaximum(max);
                bar.setValue(current);
                bar.setString(current + " / " + max);
            });
        };
    }

    public JProgressBar getBodyBar() {
        return bodyBar;
    }
    public JProgressBar getEngineBar() {
        return engineBar;
    }
    public JProgressBar getAccessoryBar() {
        return accessoryBar;
    }
    public JProgressBar getCarBar() {
        return carBar;
    }
}

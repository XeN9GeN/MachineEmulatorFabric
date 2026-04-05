package View.AdditPanel;

import Model.Observers.WarehouseObserver;

import javax.swing.*;
import java.awt.*;

public class FabricPanel extends JPanel {
    private final JProgressBar bodyBar = new JProgressBar();
    private final JProgressBar engineBar = new JProgressBar();
    private final JProgressBar accessoryBar = new JProgressBar();
    private final JProgressBar carBar = new JProgressBar();

    public FabricPanel(){
        setLayout(new GridLayout(4, 2, 5, 5));
        setBorder(BorderFactory.createTitledBorder("Warehouse Monitor"));

        add(new JLabel(" Bodies:")); add(bodyBar);
        add(new JLabel(" Engines:")); add(engineBar);
        add(new JLabel(" Accessories:")); add(accessoryBar);
        add(new JLabel(" Cars:")); add(carBar);

        setupBar(bodyBar);
        setupBar(engineBar);
        setupBar(accessoryBar);
        setupBar(carBar);
    }

    private void setupBar(JProgressBar bar){
        bar.setStringPainted(true);
        bar.setForeground(new Color(50,150,50));
    }

    public WarehouseObserver addObsBar(JProgressBar bar) {
        return (name, current, max) -> {
            //метод updateWare через лямбду
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

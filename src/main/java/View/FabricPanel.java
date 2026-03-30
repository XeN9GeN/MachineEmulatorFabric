package View;

import Controller.WarehouseController;

import javax.swing.*;
import java.awt.*;

public class FabricPanel extends JPanel {
    private final WarehouseController wc;



    public FabricPanel(WarehouseController w){
        this.wc = w;
        setPreferredSize(new Dimension(100,100));
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics gr){
        JProgressBar jpb = new JProgressBar();



    }

}

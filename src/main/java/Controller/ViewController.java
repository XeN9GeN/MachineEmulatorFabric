package Controller;

import Utils.ExectueMain.Initializer;
import View.FabricPanel;
import View.FactoryInterface.ComponentsPanel;
import View.SliderPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ViewController {
    private final Initializer init;
    private final FabricPanel fp;
    private final SliderPanel s;
    private final ComponentsPanel cp;
    private final JFrame frame;


    public ViewController(Initializer i, FabricPanel fabricPanel, SliderPanel s, ComponentsPanel cp){
        this.init= i;
        this.fp =fabricPanel;
        this.s = s;
        this.cp =cp;

        this.frame = new JFrame("FACTORY");


        setFrame();
        setupKey();
    }


    private void setFrame(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,500);

        JPanel main = new JPanel(new BorderLayout());
        main.add(fp,BorderLayout.EAST);
        main.add(s,BorderLayout.WEST);
        main.add(cp, BorderLayout.CENTER);

        frame.add(main);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
    }

    private void setupKey() {
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent k){

                if(k.getKeyCode() == KeyEvent.VK_ESCAPE || k.getKeyCode() == KeyEvent.VK_F){
                    init.STOP();
                    frame.dispose();
                }
            }
        });
    }
}
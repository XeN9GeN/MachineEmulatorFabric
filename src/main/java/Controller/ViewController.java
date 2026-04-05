package Controller;

import Utils.ExectueMain.Initializer;
import View.FabricPanel;
import View.Slider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ViewController {
    private final Initializer init;
    private final FabricPanel fp;
    private final Slider s;
    private final JFrame frame;


    public ViewController(Initializer i, FabricPanel fabricPanel,Slider s){
        this.init= i;
        this.fp =fabricPanel;
        this.s = s;

        this.frame = new JFrame("FACTORY");

        setFrame();
        setupKey();
    }


    private void setFrame(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel main = new JPanel(new BorderLayout());
        main.add(fp,BorderLayout.EAST);
        main.add(s,BorderLayout.WEST);

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
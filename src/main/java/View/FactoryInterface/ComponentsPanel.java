package View.FactoryInterface;

import Model.Observers.WarehouseObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ComponentsPanel extends JPanel implements WarehouseObserver {
    private final List<Movement> movs = new ArrayList<>();
    private int lastB, lastE, lastA;
    private Timer timer;

    public ComponentsPanel(){
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(800, 300));

        timer = new Timer(50, e->{
            movs.removeIf(m->{//условие после -> должно возвращать True по определению
                m.update();
                return m.getStatus();
            });
                repaint();
        });
        timer.start();
    }

    //не является потоком демонов внутри моих трэдов, юзается отдельный JVM таймер,
    //  поэтому надо самому останавливать
    public void stopTimer(){
        if(timer!=null){
            timer.stop();
        }
    }


    @Override
    public void updateWare(String n, int cur, int max) {
        if (n.contains("Body") && cur < lastB) movs.add(new Movement(100, 520, Color.RED));//tx - last dest
        if (n.contains("Engine") && cur < lastE) movs.add(new Movement(250, 520, Color.BLUE));
        if (n.contains("Accessory") && cur < lastA) movs.add(new Movement(400, 520, Color.ORANGE));

        if (n.contains("Body")) lastB = cur;
        if (n.contains("Engine")) lastE = cur;
        if (n.contains("Accessory")) lastA = cur;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.drawRect(50, 100, 80, 50);
        g2.drawRect(200, 100, 80, 50);
        g2.drawRect(350, 100, 80, 50);
        g2.drawRect(520, 100, 100, 50);// 520 - last dest
        for(Movement m : movs){
            m.draw(g2);
        }
    }
}

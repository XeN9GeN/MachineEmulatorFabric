package View.FactoryInterface;

import java.awt.*;

public class Movement {
    private double x;
    private int targetX;
    private Color color;
    private boolean arrived = false;

    public Movement(int x, int tx,Color c){
        this.x=x;
        this.targetX=tx;
        this.color = c;
    }

    public void update(){
        if(!arrived){
            double dx = targetX-x;
            if(Math.abs(dx)<5){
                x=targetX;
                arrived = true;
            }
            else{
                x+=dx*5/Math.abs(dx);
            }
        }

    }

    public void draw(Graphics2D gr2){
        gr2.setColor(color);
        gr2.fillOval((int)x - 5,80,10, 10);
    }

    public boolean getA(){
        return arrived;
    }
}

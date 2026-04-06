package View.FactoryInterface;

import java.awt.*;

public class Movement {
    private double x,y;
    private int targetX, targetY;
    private Color color;
    private boolean arrived = false;

    public Movement(int x, int y, int tx, int ty,Color c){
        this.x=x;
        this.y=y;
        this.targetX=tx;
        this.targetY=ty;
        this.color = c;
    }

    public void update(){
        double dx = targetX-x;
        double dy=targetY-y;
        double d = Math.hypot(dx,dy);

        if(d<5){
            x=dx;
            y=dy;
            arrived=true;
        }
        else{
            x=dx*2/d;
            y=dy*2/y;
        }
    }

    public void draw(Graphics2D gr2){
        gr2.setColor(color);
        gr2.fillOval((int)x - 5, (int)y - 5, 10, 10);
    }

    public boolean getA(){
        return arrived;
    }

}

import java.awt.*;
import java.awt.geom.Ellipse2D;
import static java.lang.Math.max;
import static java.lang.Math.min;

public final class Ovalo extends FiguraB {
    public Ovalo(int x, int y, boolean relleno, Paint color, Stroke trazo) {
        super(x, y, relleno, color, trazo);
    }

    public Ovalo(int x1, int y1, int x2, int y2, boolean relleno, Paint color, Stroke trazo) {
        super(min(x1, x2), min(y1, y2), max(x1, x2), max(y1, y2), relleno, color, trazo);
    }

    @Override
    public void dibujar(Graphics2D g) {
        super.dibujar(g);
        int Ax = punto2.x - punto1.x;
        int Ay = punto2.y - punto1.y;
        int x;
        int y;
        if(Ax < 0 && Ay < 0){
            Ax = -Ax;
            Ay = -Ay;
            x = punto2.x;
            y = punto2.y;
        }
        else if(Ax < 0){
            Ax = -Ax;
            x = punto2.x;
            y = punto1.y;
        }
        else if(Ay < 0){
            Ay = -Ay;
            x = punto1.x;
            y = punto2.y;
        }
        else{
            x = punto1.x;
            y = punto1.y;
        }
        Ellipse2D ellipse = new Ellipse2D.Double(x,y,Ax,Ay);
        if (getRelleno()) {g.fill(ellipse);} 
        else {g.draw(ellipse);}
    }
}
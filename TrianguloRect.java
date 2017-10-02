import java.awt.*;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class TrianguloRect extends FiguraB {
    public TrianguloRect(int x, int y, boolean relleno, Paint color, Stroke trazo) {
        super(x, y, relleno, color, trazo);
    }
    public TrianguloRect(int x1, int y1, int x2, int y2, boolean relleno, Paint color, Stroke trazo) {
        super(min(x1, x2), min(y1, y2), max(x1, x2), max(y1, y2), relleno, color, trazo);
    }

    @Override
    public void dibujar(Graphics2D g) {
        super.dibujar(g);
        int[] X = {punto1.x, punto2.x, punto1.x};
        int[] Y = {punto1.y, punto2.y, punto2.y};
        Polygon polygon = new Polygon(X, Y, 3);

        if (getRelleno()) {
            g.fill(polygon);
        } 
        else {
            g.draw(polygon);
        }
    }
}
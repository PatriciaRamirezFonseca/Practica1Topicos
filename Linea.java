import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Line2D;
import java.awt.Stroke;

public class Linea extends Figura{
    public Linea(int x, int y, Paint color, Stroke trazo) {
        super(x, y, 0, 0, color, trazo);
    }

    public Linea(int x1, int y1, int x2, int y2, Paint color, Stroke trazo) {
        super(x1, y1, x2, y2, color, trazo);
    }

    @Override
    public void dibujar(Graphics2D g) {
        super.dibujar(g);
        g.draw(new Line2D.Double(punto1.x, punto1.y, punto2.x, punto2.y));
    }
}
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.*;

public class Figura implements Externalizable {
    public Point punto1;
    public Point punto2;
    private Paint color;
    private Stroke trazo;

    public Figura(int x1, int y1, int x2, int y2, Paint color, Stroke trazo) {
        punto1 = new Point(x1, y1);
        punto2 = new Point(x2, y2);
        setColor(color);
        setTrazo(trazo);
    }

    public final void setTrazo(Stroke t) {
        trazo = t == null ? new BasicStroke (2.0f) : t;
    }
    public final Stroke getTrazo() {return trazo;}
    public final void setColor(Paint c) {color = c;}
    public final Paint getColor() {return color;}

    public final void setPunto2(int x, int y) {
        punto2.x = x;
        punto2.y = y;
    }

    public void dibujar(Graphics2D g) {
        g.setStroke(trazo);
        g.setPaint(getColor());
    }

    @Override
    public void writeExternal(final ObjectOutput ou) throws IOException {
        ou.writeObject(punto1);
        ou.writeObject(punto2);
        ou.writeBoolean(color instanceof Color);

        if (color instanceof Color) {
            ou.writeObject((Color) color);
        } 
        else if (color instanceof GradientPaint) {
            GradientPaint gp = (GradientPaint) color;

            ou.writeObject(gp.getPoint1());
            ou.writeObject(gp.getColor1());
            ou.writeObject(gp.getPoint2());
            ou.writeObject(gp.getColor2());
            ou.writeBoolean(gp.isCyclic());
        }
        if (trazo instanceof BasicStroke) {
            BasicStroke bs = (BasicStroke) trazo;

            ou.writeBoolean(bs.getDashArray() == null);
            ou.writeFloat(bs.getLineWidth());
            ou.writeInt(bs.getEndCap());
            ou.writeInt(bs.getLineJoin());

            if (bs.getDashArray() != null) {
                ou.writeFloat(bs.getMiterLimit());
                ou.writeObject(bs.getDashArray());
                ou.writeFloat(bs.getDashPhase());
            }
        }
    }

    @Override
    public void readExternal(final ObjectInput oi) throws IOException, ClassNotFoundException {
        punto1 = (Point) oi.readObject();
        punto2 = (Point) oi.readObject();

        if (oi.readBoolean()) {
            setColor((Color) oi.readObject());
        } 
        else {
            setColor(new GradientPaint((Point2D) oi.readObject(),
                    (Color) oi.readObject(),(Point2D) oi.readObject(),
                    (Color) oi.readObject(),oi.readBoolean()));
        }

        if (oi.readBoolean()) {
            setTrazo(new BasicStroke(oi.readFloat(),oi.readInt(),oi.readInt()));
        } 
        else {
            setTrazo(new BasicStroke(oi.readFloat(),oi.readInt(),oi.readInt(),
                    oi.readFloat(),(float[]) oi.readObject(),oi.readFloat()));
        }
    }
}
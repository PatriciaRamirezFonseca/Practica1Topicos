import java.awt.*;
import java.io.*;

public class FiguraB extends Figura {
    private boolean relleno;
    
    public FiguraB(int x1, int y1, int x2, int y2, boolean r, Paint color, Stroke trazo) {
        super(x1, y1, x2, y2, color, trazo);
        setRelleno(relleno);
    }

    public FiguraB(int x, int y, boolean r, Paint color, Stroke trazo) {
        super(x, y, 0, 0, color, trazo);
        setRelleno(r);
    }

    public final void setRelleno(boolean r) {relleno = r;}
    public final boolean getRelleno() {return relleno;}

    @Override
    public void writeExternal(final ObjectOutput ou){
        try {
            super.writeExternal(ou);
            ou.writeBoolean(getRelleno());
        } 
        catch (IOException ex) {}
    }

    @Override
    public void readExternal(final ObjectInput oi){
        try {
            super.readExternal(oi);
            setRelleno(oi.readBoolean());
        } 
        catch (IOException | ClassNotFoundException ex) {}
    }
}
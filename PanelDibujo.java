import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelDibujo extends JPanel {
    private Figura[] figura;
    private Figura figuraActual;
    private Stroke trazoActual;
    private int numFiguras;
    private int tipoFigura;
    private Paint colorActual;
    private boolean figuraRellena;
    private final JLabel etiquetaEstado;

    public PanelDibujo() {
        etiquetaEstado = new JLabel();
        figura = new Figura[100];
        numFiguras = 0;
        tipoFigura = 0;
        colorActual = Color.BLACK;
        setBackground(Color.WHITE);
        Eventos e = new Eventos();
        addMouseListener(e);
        addMouseMotionListener(e);
    }

    public void setFigura(int f) {tipoFigura = f;}
    public void setColor(Paint c) {colorActual = c;}
    public void setTrazo(Stroke t) {trazoActual = t;}
    public void setRelleno(boolean r) {figuraRellena = r;}
    public JLabel getEstado(){return etiquetaEstado;}
    
    @Override
    public void paintComponent(Graphics h) {
        super.paintComponent(h);
        Graphics2D g = (Graphics2D) h;

        if (figuraActual != null) {
            figuraActual.dibujar(g);
        }
        for (byte j = 0; figura[j] != null && j < figura.length; ++j) {
            figura[j].dibujar(g);
        }
    }

    public void borrarUltimaFigura() {
        if (--numFiguras < 0) {
            numFiguras = 0;
        }
        figura[numFiguras] = null;
        repaint();
    }

    public void borrarDibujo() {
        numFiguras = 0;
        figuraActual = null;
        figura = new Figura[100];
        repaint();
    }

    class Eventos extends MouseAdapter implements MouseMotionListener {
        @Override
        public void mousePressed(MouseEvent e) {
            switch (tipoFigura) {
                case 0:
                    figuraActual = new Linea(e.getX(), e.getY(), colorActual, 
                            trazoActual);
                    break;
                case 1:
                     figuraActual = new Rectangulo(e.getX(), e.getY(), 
                             figuraRellena, colorActual, trazoActual);
                    break;
                case 2:
                    figuraActual = new Ovalo(e.getX(), e.getY(), figuraRellena, 
                            colorActual, trazoActual);
                    break;
                case 3:
                    figuraActual = new Triangulo(e.getX(), e.getY(), 
                            figuraRellena, colorActual, trazoActual);
                    break;
                case 4:
                    figuraActual = new TrianguloRect(e.getX(), e.getY(), 
                            figuraRellena, colorActual, trazoActual);
                    break;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            figuraActual.setPunto2(e.getX(), e.getY());
            figura[numFiguras++] = figuraActual;
            figuraActual = null;
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            etiquetaEstado.setText("("+e.getX()+","+e.getY()+")");
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            figuraActual.setPunto2(e.getX(), e.getY());
            etiquetaEstado.setText("("+e.getX()+","+e.getY()+")");
            repaint();
        }
    }
}
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FrameDibujo extends JFrame {
    private final JPanel menu;
    private final JButton Deshacer;
    private final JButton Limpiar;
    private final JComboBox<String> Colores;
    private final JComboBox<String> Figuras;
    private final JCheckBox Relleno;
    private final String[] nameColores = {
        "Negro","Azul","Cyan","Gris Obscuro","Gris","Verde","Gris Claro",
        "Magenta","Naranja","Rosa","Rojo","Blanco","Amarillo"
    };
    private final String[] nameFiguras = {
        "Linea","Rectangulo","Ovalo","Triangulo","TrianguloRect"
    };
    private final Color[] colores = {
            Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY,
            Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
            Color.PINK, Color.RED, Color.WHITE, Color.YELLOW
        };

    public FrameDibujo() {
        super("Mini Paint");
        final PanelDibujo panel = new PanelDibujo(); 
        menu = new JPanel();
        Deshacer = new JButton("Deshacer");
        Deshacer.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        panel.borrarUltimaFigura();
                    }
                }
        );
        Limpiar = new JButton("Limpiar");
        Limpiar.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        panel.borrarDibujo();
                    }
                }
        );
        Colores = new JComboBox<>(nameColores);
        Colores.setMaximumRowCount(3);
        Colores.addItemListener(
                new ItemListener(){
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        panel.setColor(colores[Colores.getSelectedIndex()]);
                    }
                }
        );
        Figuras = new JComboBox<>(nameFiguras);
        Figuras.setMaximumRowCount(3);
        Figuras.addItemListener(
                new ItemListener(){
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        panel.setFigura(Figuras.getSelectedIndex());
                    }
                }
        );
        Relleno = new JCheckBox("Relleno");
        Relleno.addItemListener(
                new ItemListener(){
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        panel.setRelleno(Relleno.isSelected());
                    }
                }
        );
        
        menu.add(Deshacer);
        menu.add(Limpiar);
        menu.add(Colores);
        menu.add(Figuras);
        menu.add(Relleno);
        add(menu, BorderLayout.NORTH);
        add(panel , BorderLayout.CENTER);
        add(panel.getEstado(), BorderLayout.SOUTH);  
    }
}
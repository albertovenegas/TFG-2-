package app;

import javax.swing.*;
import java.awt.*;

public class ventana extends JFrame {

    public ventana() {
        super("Ventana principal"); // título de la ventana principal
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(1000,1000);

        // agregar texto explicativo usando una etiqueta JLabel
        JLabel textoExplicativo = new JLabel("Información de la auditoría realizada");

        // crear un nuevo panel que contendrá todas las otras ventanas y gráficas
        JPanel panelContenedor = new JPanel();
        panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));

        // agregar cada ventana y gráfica al panel contenedor
        DatabaseList dbList = new DatabaseList();
        DatabaseList3 dbList2 = new DatabaseList3();
        DatabaseList4 dbList3 = new DatabaseList4();
        DatabaseList2 dbList4 = new DatabaseList2();
        Grafico graf = new Grafico();
        panelContenedor.add(dbList3);
        panelContenedor.add(dbList);
        panelContenedor.add(dbList2);
        panelContenedor.add(dbList4);
        panelContenedor.add(graf);
        dbList.setBorder(BorderFactory.createTitledBorder("Artículos con menor y mayor grado de madurez (no se incluyen No Conformidades)"));
        dbList2.setBorder(BorderFactory.createTitledBorder("Artículos con observaciones rellenas"));
        graf.setBorder(BorderFactory.createTitledBorder("Gráficas con Grado de Madurez"));
        dbList4.setBorder(BorderFactory.createTitledBorder("Artículos con No Conformidad"));
        dbList3.setBorder(BorderFactory.createTitledBorder("Auditoría completa"));
        
      //  panelContenedor.add(new PanelGrafica1());
//        panelContenedor.add(new PanelGrafica2());

        // agregar el panel contenedor al JScrollPane para permitir desplazamiento
        JScrollPane scrollPane = new JScrollPane(panelContenedor);

        // agregar todo al panel principal
        add(textoExplicativo, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // ajustar tamaño y mostrar la ventana
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ventana();
    }
}
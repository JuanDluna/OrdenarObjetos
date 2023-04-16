package Principales;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;


public class Main{

    public static void main(String[] args) {
        JFrame ventana = new JFrame("Examen 2do parcial - Juan De Luna - ID: 261589");
        Ventana principal = new Ventana();

        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.add(principal);
        ventana.setVisible(true);
        ventana.setResizable(false);
        ventana.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                ventana.setLocation(0, 0);                      // En caso de que la ventana se mueva, se reinicia su posicion a la 0,0 relativo al monitor
                //  (si no está ahi los objetos se mueven raro profe :c)
            }
        });
        ventana.pack();
        
        principal.logicaJuego();

        System.exit(0);
    }

}



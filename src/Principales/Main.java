package Principales;

import javax.swing.JFrame;

public class Main{

    public static void main(String[] args) {
        JFrame ventana = new JFrame("Examen 2do parcial - Juan De Luna - ID: 261589");
        Ventana principal = new Ventana();

        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.add(principal);
        ventana.setVisible(true);
        ventana.setResizable(false);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        
        principal.logicaJuego();

        System.exit(0);
    }

}



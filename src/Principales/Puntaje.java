package Principales;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Puntaje extends JComponent {

    int puntaje;

    public Puntaje(){
        this.puntaje = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 26));
        g.setColor(Color.WHITE);
        g.drawString("Puntaje:" + puntaje, 1080 / 2 - 60, 26);
    }


    public void sumarPuntaje(int tiempoRestante, int multiplicadorDificultad){
        puntaje += 100 * tiempoRestante * multiplicadorDificultad;
    }

    public void restarPuntaje(int tiempoRestante, int multiplicadorDificultad){
        puntaje -= 100 * tiempoRestante * multiplicadorDificultad;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
    
}

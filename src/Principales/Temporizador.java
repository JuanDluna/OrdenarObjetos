package Principales;

import javax.swing.JComponent;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Temporizador extends JComponent implements ActionListener {
    private int tiempoRestante;
    private Timer timer;

    public Temporizador(float tiempoEnMinutos) {
        tiempoRestante = (int) (tiempoEnMinutos * 60);
        timer = new Timer(1000, this);
        timer.start();
        setBounds(30, 36, 360, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (tiempoRestante > 0) {
            tiempoRestante--;
        } else {
            timer.stop();
        }
    }

    public String getTiempoFormateado() {
        int minutos = tiempoRestante / 60;
        int segundos = tiempoRestante % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 26));
        g.drawString(getTiempoFormateado(), getX(), getY());
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

}

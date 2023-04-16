package Principales;

import Plantillas.*;
import Objetos.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Ventana extends JPanel{

    private final int WIDTH = 1080, HEIGHT = 720;
    private Caja cajas;
    private Objeto objetoX;
    private Puntaje puntaje;
    private Temporizador temp;
    private BufferedImage fondo;
    private int JugarDeNuevo, vecesJugado = 1, modoJuego;
    private float minutosAJugar = 1;
    String[] options = { "Facil", "Dificil" };

    public Ventana(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        puntaje = new Puntaje();
        temp = new Temporizador(minutosAJugar);

        modoJuego = JOptionPane.showOptionDialog(null,
                                    "Selecciona un modo de juego:\nFacil: Se usan los objetos y se ingresan como guste.\nDificil:Los objetos tienen orden de entrada (mayor a menor)", 
                                    "Bienvenido!", 
                                    JOptionPane.DEFAULT_OPTION, 
                                    JOptionPane.PLAIN_MESSAGE, null, 
                                    options, 
                                    options[0]);
        
        try {
            fondo = ImageIO.read(new File("resources/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for (int subcategoria = 1; subcategoria <= 3; subcategoria++) {
            objetoX =  new Peluches(subcategoria, modoJuego);   
            add(objetoX);
            // Se agrega un nuevo peluche
            objetoX = new Ropa(subcategoria, modoJuego);
            add(objetoX);
            // Se agrega una nueva prenda de ropa
            objetoX = new Comida(subcategoria, modoJuego);
            add(objetoX);
            // Se agrega una nueva comida
            objetoX = new Escuela(subcategoria, modoJuego);
            add(objetoX);
            // Se agrega un nuevo articulo de escuela
            objetoX = new Juguetes(subcategoria, modoJuego);
            add(objetoX);
            // Se agrega un nuevo juguete.
        }
        
        for (int i = 1; i <= 5; i++) {
            cajas = new Caja(i, modoJuego);
            add(cajas);
        }
        
        // Se instancian las cajas por aparte y despues de los objetos para que aparezcan por detras de los objetos.
        setLayout(null);
        // Sin manejo de capas para independencia de labels.
        
    }

    public void logicaJuego(){
        
        // Las cajas y los objetos se guardan en un array de componentes de la ventana
        // Los objetos al ser instanciados primero que las cajas, ocupan los primeros 15 lugares del array
        // Las cajas por otro lado al ser las ultimas en instanciarse, ocupan los ultimos 5 lugares del Array
        Random rand = new Random();                                                                   

        while(true){

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(getComponentCount() <= 5){
                JOptionPane.showMessageDialog(null, "FELICIDADES GANASTE\nPuntuación final: " + puntaje.getPuntaje(), 
                                            "JUEGO FINALIZADO",
                                            JOptionPane.INFORMATION_MESSAGE);
                break;
            }

            if(temp.getTiempoRestante() <= 0){
                JOptionPane.showMessageDialog(null, "Puntuación final: " + puntaje.getPuntaje(), "TIEMPO FINALIZADO", JOptionPane.INFORMATION_MESSAGE);
                break;
            }

            for (int i = getComponentCount() - 5; i < getComponentCount(); i++) {
                cajas = (Caja) getComponent(i);
                for (int j = 0; j < getComponentCount() - 5; j++) {
                    objetoX = (Objeto) getComponent(j);
                    if(cajas.sobreCaja(objetoX)){
                        if(!objetoX.getDraggin()){
                            if(cajas.addObjeto(objetoX)){
                                puntaje.sumarPuntaje(temp.getTiempoRestante());
                                remove(j);
                            }else{
                                objetoX.setLocation(rand.nextInt(WIDTH - 200), rand.nextInt(570));
                            }
                        }
                    }
                    else if(cajas.CajaIncorrecta(objetoX)){
                        
                        if(!objetoX.getDraggin()){
                            objetoX.setLocation(rand.nextInt(WIDTH - 200), rand.nextInt(570));
                            puntaje.restarPuntaje(temp.getTiempoRestante());
                        }
                    }
                }
            }
            repaint();
            
        }

        JugarDeNuevo = JOptionPane.showConfirmDialog(null,"¿Deseas jugar de nuevo? (Se aumentará la dificultad reduciendo tiempo)" ,
                                                                    "Juego finalizado",
                                                                    JOptionPane.YES_NO_OPTION,
                                                                    JOptionPane.INFORMATION_MESSAGE);
                                                                    
        if(JugarDeNuevo == JOptionPane.YES_OPTION){
            vecesJugado++;
            nuevoJuego();
            logicaJuego();
        }

    }

    public void nuevoJuego(){
        removeAll();
        // Se instancian todos los componentes de nuevo.
        puntaje = new Puntaje();                    // Reinicia el puntaje
        temp = new Temporizador(minutosAJugar / vecesJugado);     // Reinicia el temporizador en el tiempo
        
        JOptionPane.showMessageDialog(null, "Dificultad aumentada.\nTiempo inicial: " + temp.getTiempoFormateado(), "Juego nuevo.", JOptionPane.QUESTION_MESSAGE);

        for (int subcategoria = 1; subcategoria <= 3; subcategoria++) {
            objetoX =  new Peluches(subcategoria, modoJuego);   
            add(objetoX);
            // Se agrega un nuevo peluche
            objetoX = new Ropa(subcategoria, modoJuego);
            add(objetoX);
            // Se agrega una nueva prenda de ropa
            objetoX = new Comida(subcategoria, modoJuego);
            add(objetoX);
            // Se agrega una nueva comida
            objetoX = new Escuela(subcategoria, modoJuego);
            add(objetoX);
            // Se agrega un nuevo articulo de escuela
            objetoX = new Juguetes(subcategoria, modoJuego);
            add(objetoX);
            // Se agrega un nuevo juguete.
        }
        
        for (int i = 1; i <= 5; i++) {
            cajas = new Caja(i, modoJuego);
            add(cajas);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0,getWidth(), getHeight(), null);
        puntaje.paintComponent(g);
        temp.paintComponent(g);
    }

}

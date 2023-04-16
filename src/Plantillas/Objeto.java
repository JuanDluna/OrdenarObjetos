package Plantillas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Objeto extends JLabel implements MouseListener, MouseMotionListener{

    private static HashMap<String, Integer> categorias = new HashMap<>();
    static {
        categorias.put("Ropa"       , 1 );
        categorias.put("Juguetes"   , 2 );
        categorias.put("Comida"     , 3 );
        categorias.put("Peluches"   , 4 );
        categorias.put("Escuela"    , 5 );
    }

    private int modoJuego;
    private int posX, posY;                     // Posicion en X y en Y del Label
    private final int ANCHO = 150, ALTO = 150;  // Ancho y Alto del label (el mismo en todos los casos)
    private int Categoria, Subcategoria;        // Categoria del objeto para guardar en caja
    private int offsetX, offsetY;               // Variables para mejor uso con mouse (se mantendra el draggin en donde se agarre el objeto)
    private boolean dragging;                   // Booleano para saber si se esta arrastrando el objeto

    public Objeto(String StrCategoria, int subcategoria, int modoJuego) {
        this.modoJuego = modoJuego;
        Categoria = categorias.get(StrCategoria);   // Categoria que se obtiene del constructor
        this.Subcategoria = subcategoria;           
        setIcon(new ImageIcon(new ImageIcon("resources/" + this.Categoria + "_" + subcategoria + ".png").getImage().getScaledInstance(ANCHO, ALTO, Image.SCALE_DEFAULT)));
        // Imagen a mostrar por el tipo de objeto 
        int numeroRandom =(int) (Math.random() * 950);
        posX = numeroRandom <= 50 ? numeroRandom + 100 : numeroRandom;
        // Se establece la posicion en X
        numeroRandom = (int) (Math.random() * 420);
        posY = numeroRandom <= 50 ? numeroRandom + 100 : numeroRandom;
        //Se establece la posicion en Y
        // Posicion en X y en Y aleatorias en la pantalla
        setBounds(posX, posY, ANCHO, ALTO);
        addMouseListener(this);
        addMouseMotionListener(this);
        setName(Categoria + "_" + subcategoria);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(modoJuego == 1){
            g.setColor(Color.white);
            g.fillRect(50, 125, 50, 25);
            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.BOLD, 25));
            g.drawString(String.valueOf(Subcategoria), 67,147);
        }
    }


    public int getCategoria() {
        return Categoria;
    }

    public int getSubcategoria(){
        return Subcategoria;
    }

    public boolean getDraggin(){
        return dragging;
    }

    public void mouseDragged(MouseEvent e) {
        setLocation(e.getXOnScreen() - offsetX, e.getYOnScreen() - offsetY);
        dragging = true;
    }
    public void mouseReleased(MouseEvent e) {
        dragging = false;
    }
    public void mousePressed(MouseEvent e){
        offsetX = e.getX();
        offsetY = e.getY();
    }
    
    // Métodos de la interfaz MouseListener y MouseMotionListener que no se utilizan
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    
}
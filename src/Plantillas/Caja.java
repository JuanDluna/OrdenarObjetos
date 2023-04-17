package Plantillas;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Caja extends JLabel {

    private HashMap<Integer, String> categorias = new HashMap<Integer, String>() {{
        put(1, "Ropa");
        put(2, "Juguetes");
        put(3, "Comida");
        put(4, "Peluches");
        put(5, "Escuela");
    }};

    private final int ANCHO = 150, ALTO = 150;
    private ArrayList<Objeto> listaObjetos;
    private int Categoria, modoJuego;
    private int objetosRestantes = 3;
    
    public Caja(int Categoria, int modoJuego){
        this.modoJuego = modoJuego;
        this.listaObjetos = new ArrayList<>(3);
        this.Categoria = Categoria;
        // Instanciado de Label
        setName("Caja " + categorias.get(Categoria));
        setIcon(new ImageIcon( new ImageIcon("resources/caja_abierta.png").getImage().getScaledInstance(ANCHO, ALTO, Image.SCALE_FAST)));
        setBounds( (Categoria-1) * 235,570 ,getIcon().getIconWidth(), getIcon().getIconHeight()); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.cyan);
        g.fillRect(0, 125, 150, 25);
        g.setColor(Color.black);
        g.setFont((new Font("Arial", Font.BOLD, 20)));
        g.drawString(categorias.get(Categoria) + " ("  + listaObjetos.size() + ")", 25,145);
    }

    public boolean sobreCaja(Objeto obj){
        return (getBounds().intersects(obj.getBounds()) && obj.getCategoria() == this.Categoria);
    }

    public boolean CajaIncorrecta(Objeto obj){
        return (getBounds().intersects(obj.getBounds()) && obj.getCategoria() != this.Categoria);
    }

    public void setIconLleno(){
        if(listaObjetos.size() == 3){
            setIcon(new ImageIcon( new ImageIcon("resources/caja_cerrada.png").getImage().getScaledInstance(ANCHO, ALTO, Image.SCALE_FAST)));
        }
    }

    public boolean addObjeto(Objeto e){

        if(modoJuego == 1 && e.getSubcategoria() == objetosRestantes){
            listaObjetos.add(e);
            objetosRestantes--;
            setIconLleno();
            return true;
        }
        if(modoJuego == 0){
            listaObjetos.add(e);
            objetosRestantes--;
            setIconLleno();
            return true;
        }
        
        return false;
    }

}

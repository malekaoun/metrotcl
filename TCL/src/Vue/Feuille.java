
package Vue;

import Model.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 * Cette classe hérite la classe JPanel. Elle affiche le réseau de transport
 * avec toutes les lignes, toutes les stations, les metros qui se déplacent et tous les usagers.
 * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
 */
public class Feuille extends JPanel {

    private Graphe graphe;
    private Image drawingImage;

    /**
     * Constructeur de la classe Feuille qui prend un Graphe en paramètre
     * @param graphe Graphe
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public Feuille(Graphe graphe) {
        this.graphe = graphe;

    }

    /**
     * Reset le JPanel
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    void reset() {
        Dimension dim = getSize();
        drawingImage = this.createImage(dim.width, dim.height);
        Graphics g = drawingImage.getGraphics();
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, dim.width, dim.height);
        dessineGraphe();
    }

    /**
     * Surcharge de la méthode paintComponents
     * @param g Graphics
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    @Override
    public void paintComponent(Graphics g) {
        if (drawingImage == null) {
            reset();
        }
        g.drawImage(drawingImage, 0, 0, null);
    }

    /**
     * Dessine l'image
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void drawIt() {
        Graphics g = this.getGraphics();
        g.drawImage(drawingImage, 0, 0, null);
    }

    /**
     * Renvoie l'image
     * @return Graphics
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public Graphics getImageGraphics() {
        if (drawingImage == null) {
            reset();
        }
        return drawingImage.getGraphics();
    }

    /**
     * Dessine le Graphe
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void dessineGraphe() {
        Graphics g = this.getImageGraphics();
        if (graphe != null) {
            for (int i = 0; i < graphe.getLignes().size(); i++) {
                dessineChemin(graphe.getLignes().get(i), decodeColor(i));
            }
            for (int i = 0; i < graphe.getSommets().size(); i++) {
                dessineStation(graphe.getSommets().get(i), i + 1);
            }
        }
    }

    /**
     * Dessine une station avec son indice dans la liste des stations
     * grâce à leurs coordonnées géométrique.
     * @param station Station
     * @param i Entier
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void dessineStation(Station station, int i) {
        Graphics g = this.getImageGraphics();
        g.setColor(Color.gray);
        g.drawOval(station.getX() - 10, station.getY() - 10, 30, 30);
        g.fillOval(station.getX() - 10, station.getY() - 10, 30, 30);
        g.setColor(Color.black);
        g.drawString("" + i, station.getX()-15, station.getY()-10);
        g.drawString("" + station.getNom(), station.getX()+15, station.getY()-10);
        g.setColor(Color.white);
        g.drawString("" + station.getListUsager().size(), station.getX(), station.getY()+10);
    }

    /**
     * Dessine un metro avec son indice dans la liste des metros
     * grâce à leurs coordonnées géométriques.
     * @param m Metro
     * @param i Entier
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void dessineMetro(Metro m, int i) {
        Graphics g = this.getImageGraphics();
        g.setColor(Color.RED);
        g.draw3DRect(m.getX()-10, m.getY() - 5, 20, 15, true);
        g.drawString("" + i, m.getX(), m.getY() - 5);
        g.setColor(Color.black);
        g.drawString("" + m.getListPassager().size(), m.getX()-5, m.getY() + 7);
    }

    /**
     * Efface un metro et son indice.
     * @param m Metro
     * @param i Entier
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void eraseMetro(Metro m, int i) {
        Graphics g = this.getImageGraphics();
        g.setColor(Color.lightGray);
        g.drawRect(m.getOldx()-10, m.getOldy() - 5, 20, 15);
        g.drawString("" + i, m.getOldx(), m.getOldy() - 5);
        g.drawString("" + m.getListPassager().size(), m.getOldx()-5, m.getOldy() + 7);
    }

    /**
     * Renvoie une couleur à partir d'un entier.
     * @param c Entier
     * @return une couleur
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    private Color decodeColor(int c) {
        switch (c) {
            case 0:
                return (Color.black);
            case 1:
                return (Color.blue);
            case 2:
                return (Color.cyan);
            case 3:
                return (Color.yellow);
            case 4:
                return (Color.red);
            case 5:
                return (Color.green);
            case 6:
                return (Color.lightGray);
            case 7:
                return (Color.magenta);
            case 8:
                return (Color.orange);
            case 9:
                return (Color.gray);
            case 10:
                return (Color.pink);
            case 11:
                return (Color.darkGray);
            default:
                return (Color.black);
        }
    }

    /**
     * Dessine avec une couleur tous les chemins entre les stations d'une Ligne.
     * @param l Ligne
     * @param c Couleur
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void dessineChemin(Ligne l, Color c) {
        Graphics g = this.getImageGraphics();
        for (int j = 0; j < l.getListStation().size() - 1; j++) {
            g.setColor(c);
            g.drawLine(l.getListStation().get(j).getX(), l.getListStation().get(j).getY(), l.getListStation().get(j + 1).getX(), l.getListStation().get(j + 1).getY());
            g.drawLine(l.getListStation().get(j).getX() + 1, l.getListStation().get(j).getY() + 1, l.getListStation().get(j + 1).getX() + 1, l.getListStation().get(j + 1).getY() + 1);
            g.drawLine(l.getListStation().get(j).getX() - 1, l.getListStation().get(j).getY() - 1, l.getListStation().get(j + 1).getX() - 1, l.getListStation().get(j + 1).getY() - 1);
            g.drawString(""+l.getAretes().get(j).getDistance(), l.getListStation().get(j).getX()-(l.getListStation().get(j).getX()-l.getListStation().get(j+1).getX())/2, l.getListStation().get(j).getY()-(l.getListStation().get(j).getY()-l.getListStation().get(j+1).getY())/2);
        }
    }
}

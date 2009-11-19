/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Model.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class Feuille extends JPanel {

    private Graphe graphe;
    Image drawingImage;

    public Feuille(Graphe graphe) {
        this.graphe = graphe;

    }

    /**
     * Reset le JPanel
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
     * @param g (Graphics)
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
     */
    public void drawIt() {

        Graphics g = this.getGraphics();
        g.drawImage(drawingImage, 0, 0, null);

    }

    /**
     * Renvoie l'image
     * @return Graphics
     */
    public Graphics getImageGraphics() {
        if (drawingImage == null) {
            reset();
        }
        return drawingImage.getGraphics();
    }

    public void dessineGraphe() {

        Graphics g = this.getImageGraphics();
        //System.out.println(graphe);
        if (graphe != null) {
            for (int i = 0; i < graphe.getLignes().size(); i++) {
                dessineChemin(graphe.getLignes().get(i), decodeColor(i));
            }
            for (int i = 0; i < graphe.getSommets().size(); i++) {
                dessineStation(graphe.getSommets().get(i), i + 1);
            }
        }
    }


    public void dessineStation(Station station, int i) {
        Graphics g = this.getImageGraphics();
        g.setColor(Color.BLACK);
        g.fillOval(station.getX() - 10, station.getY() - 10, 30, 30);
        g.drawString("" + i, station.getX()-15, station.getY()-10);
        g.setColor(Color.white);
        g.drawString("" + station.getListUsager().size(), station.getX(), station.getY()+10);
    }

    public void dessineMetro(Metro m, int i) {

        Graphics g = this.getImageGraphics();

        g.setColor(Color.RED);
        g.draw3DRect(m.getX()-10, m.getY() - 5, 20, 15, true);
        g.drawString("" + i, m.getX(), m.getY() - 5);
        g.setColor(Color.black);
        g.drawString("" + m.getListPassager().size(), m.getX()-5, m.getY() + 7);
    }

    void eraseMetro(Metro m, int i) {
        Graphics g = this.getImageGraphics();

        g.setColor(Color.lightGray);
        g.drawRect(m.getOldx()-10, m.getOldy() - 5, 20, 15);
        g.drawString("" + i, m.getOldx(), m.getOldy() - 5);
        g.drawString("" + m.getListPassager().size(), m.getOldx()-5, m.getOldy() + 7);

    }

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

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
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author p0505657
 */
public class Feuille extends JPanel{
    private Graphe graphe;

        boolean showTurtles=true;

    public Feuille(Graphe graphe) {
        this.graphe = graphe;
    }

        Image drawingImage;

        void reset(){
		Dimension dim=getSize();
		drawingImage = this.createImage(dim.width,dim.height);
		Graphics g = drawingImage.getGraphics();
		Color c = g.getColor();
		g.setColor(Color.lightGray);
		g.fillRect(0,0,dim.width, dim.height);
		g.setColor(c);
        //dessineGraphe(g);
	}

    @Override
	public void paintComponent(Graphics g){
	    if (drawingImage==null)
                reset();
            g.drawImage(drawingImage,0,0,null);
		dessineGraphe(g);
	}

	public void drawIt(){
            Graphics g = getGraphics();
            g.drawImage(drawingImage,0,0,null);
            if (showTurtles)
		showTurtles(g);
	}

	public Graphics getImageGraphics(){
            if (drawingImage == null)
		reset();
            return drawingImage.getGraphics();
	}

	void showTurtles(Graphics g){
	}
    public void dessineGraphe(Graphics g){
        //Graphics g = jpanel.getGraphics();
        System.out.println(graphe);
        if(graphe!=null){
            for(int i=0; i<graphe.getSommets().size(); i++){
                dessineStation(graphe.getSommets().get(i),g);
                               
            }
             for(int i=0; i<graphe.getLignes().size(); i++){
                dessineChemin(graphe.getLignes().get(i),g);
                       
            }

        }
    }

    public void dessineStation(Station station, Graphics g){
        g.setColor(Color.BLACK);
        g.fillOval(station.getX()-10, station.getY()-10, 20,20);
    }

    public void dessineChemin(Ligne l, Graphics g){
        g.setColor(Color.black);
        for(int j=0; j<l.getListStation().size()-1; j++){

        g.drawLine(l.getListStation().get(j).getX(), l.getListStation().get(j).getY(),l.getListStation().get(j+1).getX(), l.getListStation().get(j+1).getY());
    }
}
}

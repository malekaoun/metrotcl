/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import Vue.Controle;
import Vue.Interface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author p0505657
 */
public class ReseauController implements ActionListener {

    private ReseauView Interface = null;
    private ReseauView Controle = null;
    private Reseau model = null;


    public ReseauController(Reseau r) {
        this.model = r;

        Interface = new Interface(this);
        Controle = new Controle(this);

    }

    /**
     * Renvoie le model utilisé par le controller
     * @return Jeu
     */
    public Reseau getModel() {

        return model;
    }

    /**
     * Affiche les différentes vues
     */
    public void displayViews() {
        Interface.display();

    }

    /**
     * Ferme les différentes vues
     */
    public void closeViews() {
        Interface.close();

    }

    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();
        if (c.equals("Quitter")) {
            quitter();
        }
    }

    private void quitter() {
        System.exit(0);
    }

    /*
    private int targetDir(Station s, int x,int y){
    int d=0;
    if (s.getX()>x){
    if (s.getY()>y){
    double q = (double)((double)s.getX()-x)/((double)s.getY()-y);
    d = (int) (((Math.atan(1/q))/Math.PI)*180+180);
    }
    else {
    double q = (double)((double)s.getX()-x)/((double)y-s.getY());
    d = (int) (((Math.atan(q))/Math.PI)*180+90);
    }
    }
    else {
    if (s.getY()>y){
    double q = (double)((double)s.getX()-x)/((double)s.getY()-y);
    d = (int) (((Math.atan(1/q))/Math.PI)*180);
    }
    else {
    double q = (double)((double)s.getX()-x)/((double)y-s.getY());
    d = (int) (((Math.atan(q))/Math.PI)*180+90);
    }
    }
    return d;
    }
     */
}

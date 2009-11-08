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
        this.model.setF(((Interface)Interface).getFeuille());
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

}

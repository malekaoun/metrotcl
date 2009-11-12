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
import java.util.Observer;

/**
 *
 * @author p0505657
 */
public class ReseauController implements ActionListener {

    private ReseauView Interface = null;
    private Controle Controle = null;
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
        Controle.display();

    }

    /**
     * Ferme les différentes vues
     */
    public void closeViews() {
        Interface.close();
        Controle.close();

    }

    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();
        if (c.equals("Ajouter la personne")) {

            int i = Controle.getListStationDepart().getSelectedIndex();
            int j = Controle.getListStationArrivee().getSelectedIndex();

            Station depart = this.getModel().getGraphe().getSommets().get(i);
            Station arrivee = this.getModel().getGraphe().getSommets().get(j);

            Usager u = new Usager(depart.getX(), depart.getY(), depart, arrivee);

            depart.addUsagerToStation(u);

        }
        if (c.equals("Ajouter un Metro")) {

            int i = Controle.getListLignes().getSelectedIndex();

            Metro m = this.model.addMetro(i+1);

            m.addObserver((Observer) this.Interface);

        }


        if (c.equals("Quitter")) {
            quitter();
        }
    }

    private void quitter() {
        System.exit(0);
    }
}

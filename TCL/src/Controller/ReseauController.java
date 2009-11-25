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

            int depart = Controle.getListStationDepart().getSelectedIndex();
            int arrivee = Controle.getListStationArrivee().getSelectedIndex();
            Usager u = null;

                u = new Usager(depart, arrivee);
                
            

            this.getModel().getGraphe().getSommets().get(depart).addUsagerToStation(u);

        }
        if (c.equals("Ajouter un Metro")) {

            int i = Controle.getListLignes().getSelectedIndex();

            Metro m = this.model.addMetro(i + 1);

            m.addObserver((Observer) this.Interface);

        }


        if (c.equals("Quitter")) {
            quitter();
        }
        if (c.equals("Supprime Station")) {

            /*
            Station MaStation;
            Station S1;
            Station S2;
            Distance d;
            int i = Controle.getListLignesAsupprimer().getSelectedIndex();

            model.getGraphe().getSommets().remove(i);
            for (int j = 0; j < model.getGraphe().getLignes().size(); j++) {
            System.out.println("je rentre dans le premier for");

            for (int k = 0; k < model.getGraphe().getLignes().get(j).getListStation().size(); k++) {
            System.out.println("je rentre dans le 2eme for ");
            if (model.getGraphe().getLignes().get(j).indiceStation(model.getGraphe().getLignes().get(j).getListStation().get(k)) == i) {
            System.out.println("je trouver ma station");
            MaStation = model.getGraphe().getLignes().get(j).getListStation().get(k);

            model.getGraphe().getLignes().get(j).removeStationToLigne(MaStation);
            S1 = model.getGraphe().getLignes().get(j).getAretes().get(k).getS1();
            S2 = model.getGraphe().getLignes().get(j).getAretes().get(k).getS2();
            System.out.println("S1 : " +S1);
            System.out.println("S2 : " +S2);
            if( model.getGraphe().getLignes().get(j).getDistance(S1, S2)  > 0){
            System.out.println("j'ai trouver Deux station");
            System.out.println("k = : " +k);
            d = model.getGraphe().getLignes().get(j).getAretes().get(k);
            model.getGraphe().getLignes().get(j).rmArete(d);
            }


            }*/
        }
    }

    private void quitter() {
        System.exit(0);
    }
}

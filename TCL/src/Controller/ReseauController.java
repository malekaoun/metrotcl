package Controller;

import Model.*;
import Vue.Controle;
import Vue.Interface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observer;

/**
 * Controleur de l'application.
 * Fait le lien entre les actions de l'utilisateur (vue Controle) et les modifications des états des modèles.
 * Implémente ActionListener.
 * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Tai NGUYEN DAC CONG
 */
public class ReseauController implements ActionListener {

    private ReseauView Interface = null;
    private Controle Controle = null;
    private Reseau model = null;

    /**
     * Constructeur de controleur.
     * @param r Reseau
     */
    public ReseauController(Reseau r) {
        this.model = r;
        Interface = new Interface(this);
        Controle = new Controle(this);
    }

    /**
     * Renvoie le modele utilisé par le controleur
     * @return Reseau
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

    /**
     * Gestion des actions/évenements utilisateurs (boutons de l'interface)
     * @param e ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();
        if (c.equals("Ajouter la personne")) {

            int depart = Controle.getListStationDepart().getSelectedIndex();
            int arrivee = Controle.getListStationArrivee().getSelectedIndex();
            Usager u = null;

            ArrayList<Integer> listLignesDep = model.getGraphe().getIdLigneFrIdStation(depart);
            ArrayList<Integer> listLignesArr = model.getGraphe().getIdLigneFrIdStation(arrivee);
            if (listLignesArr.size() > 0 && listLignesDep.size() > 0) {
                u = new Usager(depart, arrivee);
                model.getGraphe().CalculTrajet(u);
                model.getGraphe().getCorres(depart, arrivee);
                this.getModel().getGraphe().getSommets().get(depart).addUsagerToStation(u);
            } else {
                System.out.println("Le station de depart ou d'arrivee n'existe pas.");
            }
        }

        if (c.equals("Ajouter un Metro")) {
            int i = Controle.getListLignes().getSelectedIndex();
            Metro m = this.model.addMetro(i + 1);
            m.addObserver((Observer) this.Interface);
        }

        if (c.equals("Quitter")) {
            quitter();
        }
    }

    /**
     * Fermeture de l'application.
     */
    private void quitter() {
        System.exit(0);
    }
}

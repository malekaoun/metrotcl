package Model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Cette classe définit un usager qui contient une station de départ, une station de destinantaire,
 * la liste des correspondances et l'indice d'une correspondance s'ils existent,
 * un metro s'il est dans un metro.
 * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
 */
public class Usager extends Observable {

    private int stationDepart;
    private int stationDestination;
    private Metro metro;
    private ArrayList<Correspondance> trajet = new ArrayList<Correspondance>();
    private int indiceTrajet;


    /**
     * Constructeur de la classe Usager qui prend en paramètre
     * un entier de station de départ et celui de destinataire.
     * @param dep Entier de station de départ
     * @param des Entier de station destinataire
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public Usager(int dep, int des) {
        this.stationDepart = dep;
        this.stationDestination = des;
        this.metro = null;
        this.indiceTrajet=0;
    }

    /**
     * Renvoie l'entier de la station de départ d'un Usager.
     * @return Entier
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public int getDepart() {
        return stationDepart;
    }

    /**
     * Met un entier au station de départ d'un Usager.
     * @param depart Entier
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void setDepart(int depart) {
        this.stationDepart = depart;
    }

    /**
     * Renvoie l'entier de la station destinataire d'un Usager.
     * @return Entier
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public int getDestination() {
        return stationDestination;
    }

    /**
     * Met un entier au station destinataire d'un Usager.
     * @param destination Entier
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void setDestination(int destination) {
        this.stationDestination = destination;
    }

    /**
     * L'Usager monte dans un metro à une station.
     * @param m Metro
     * @param s Station
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void usagerMonteDansMetro(Metro m, Station s) {
        this.setMetro(m);
        m.addUsagerToMetro(this);
        s.removeUsagerFrStation(this);
        m.setNbPlaceRestante(m.getNbPlaceRestante() - 1);
    }

    /**
     * L'Usager descend d'un metro à une station
     * @param m Metro
     * @param s Station
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void usagerDescendDuMetro(Metro m, Station s) {
        this.metro = null;
        m.removeUsagerFrMetro(this);
        s.addUsagerToStation(this);
        m.setNbPlaceRestante(m.getNbPlaceRestante() + 1);
    }

    /**
     * Ajoute un metro d'un Usager
     * @param m Metro
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void setMetro(Metro m) {
        this.metro = m;
    }

    /**
     * Renvoie le metro d'un Usager
     * @return Metro
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public Metro getMetro() {
        return metro;
    }

    /**
     * Renvoie la liste des Correspondances d'un Usager
     * @return la liste des Correspondances
     */
    public ArrayList<Correspondance> getTrajet() {
        return trajet;
    }

    /**
     * Ajoute une liste des Correspondances dans l'Usager
     * @param trajet la liste des Correspondances
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void setTrajet(ArrayList<Correspondance> trajet) {
        this.trajet = trajet;
    }

    /**
     * Renvoie l'indice d'un Correspondance dans la liste des Correspondances d'un Usager
     * @return Entier
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public int getIndiceTrajet() {
        return indiceTrajet;
    }

    /**
     * Met un indice d'un Correspondance
     * @param indiceTrajet Entier
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void setIndiceTrajet(int indiceTrajet) {
        this.indiceTrajet = indiceTrajet;
    }
}

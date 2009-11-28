package Model;

/**
 * Modèle : correspondance
 * Une correspondance est un couple (Station, Ligne)
 * Elle sert à définir un point d'un trajet.
 * @author Mattias
 */
public class Correspondance {

    private Station station;
    private Ligne ligne;

    /**
     * Constructeur par paramètres
     * @param station
     * @param ligne
     */
    public Correspondance(Station station, Ligne ligne) {
        this.station = station;
        this.ligne = ligne;
    }

    /**
     * Constructeur par défault
     */
    public Correspondance() {
        this.station = null;
        this.ligne = null;
    }

    /**
     * Renvoie la ligne (getter)
     * @return
     */
    public Ligne getLigne() {
        return ligne;
    }

    /**
     * Définit la ligne (setter)
     * @param ligne
     */
    public void setLigne(Ligne ligne) {
        this.ligne = ligne;
    }

    /**
     * Renvoie la station (getter)
     * @return
     */
    public Station getStation() {
        return station;
    }

    /**
     * Définit la station (setter)
     * @param station
     */
    public void setStation(Station station) {
        this.station = station;
    }
}

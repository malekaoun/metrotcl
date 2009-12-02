package Model;

/**
 * Modèle : classe Distance
 * Une distance entre deux stations adjacente
 * i.e. le poids de l'arète du graphe
 * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
 */
public class Distance {

    private int distance;

    /**
     * Constructeur par paramètre
     * @param d
     */
    public Distance(int d) {
        this.distance = d;
    }

    /**
     * Renvoie la distance (getter)
     * @return
     */
    public int getDistance() {
        return this.distance;
    }

    /**
     * Définit la distance (setter)
     * @param d
     */
    public void setDistance(int d) {
        this.distance = d;
    }
}

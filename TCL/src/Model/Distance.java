package Model;

/**
 * Modèle : distance
 * Un distance entre deux stations adjacente
 * i.e. le poid de l'arète du graphe
 * @author Mattias
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
    public int getDistance() { return this.distance; }

    /**
     * Définit la distance (setter)
     * @param d
     */
    public void setDistance(int d) { this.distance = d; }

}

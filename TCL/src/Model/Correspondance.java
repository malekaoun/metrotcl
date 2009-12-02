package Model;

/**
 * Modèle : classe Correspondance
 * Une correspondance est un triplet (booleen sens, entier Ligne, entier indiceStationCorres)
 * Elle sert à définir la correspondance d'un trajet pour que l'usager puisse prendre le metro suivant
 * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
 */
public class Correspondance {

    private boolean sensInverse;
    private int ligne;
    private int indiceStationCorres;

    /**Constructeur de la classe Correspondance
     * 
     * @param sens booleen sens à prendre pour l'usager
     * @param ligne entier indice de la ligne à prendre pour l'usager
     * @param indiceStationCorres entier indice de la station de correspondance
     */
    public Correspondance(boolean sens, int ligne, int indiceStationCorres) {
        this.sensInverse = sens;
        this.ligne = ligne;
        this.indiceStationCorres=indiceStationCorres;
    }

    /** Getter de l'entier ligne
     *
     * @return ligne entier indice de la ligne à prendre
     */
    public int getLigne() {
        return ligne;
    }

    /**Setter de ligne
     *
     * @param ligne entier à affecter à ligne dans l'instance de la classe
     */
    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    /** Getter du booleen sens
     *
     * @return vrai si le sens à prendre est le sens de départ du metro
     *         faux si c'est le sens inverse
     */
    public boolean isSensInverse() {
        return sensInverse;
    }

    /**Setter de sens
     *
     * @param sens booleen à affecter à sens dans l'instance de la classe
     */
    public void setSensInverse(boolean sens) {
        this.sensInverse = sens;
    }

    /** Getter de l'entier indiceStationCorres
     *
     * @return indiceStationCorres entier correspondant à l'indice de la station de correspondance dans la liste de station de la ligne
     */
    public int getIndiceStationCorres() {
        return indiceStationCorres;
    }

    /**Setter de indiceStationCorres
     *
     * @param indiceStationCorres entier à affecter à indiceStationCorres dans l'instance de la classe
     */
    public void setIndiceStationCorres(int indiceStationCorres) {
        this.indiceStationCorres = indiceStationCorres;
    }
}

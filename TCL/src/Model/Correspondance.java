package Model;

/**
 * Modèle : correspondance
 * Une correspondance est un couple (Station, Ligne)
 * Elle sert à définir un point d'un trajet.
 * @author Mattias
 */
public class Correspondance {

    private boolean sensInverse;
    private int ligne;

    public Correspondance(boolean sens, int ligne) {
        this.sensInverse = sens;
        this.ligne = ligne;
    }

    public int getLigne() {
        return ligne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public boolean isSensInverse() {
        return sensInverse;
    }

    public void setSensInverse(boolean sens) {
        this.sensInverse = sens;
    }

    
}

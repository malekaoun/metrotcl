package Controller;

/**
 * Classe abstraite ReseauView, généralisation des vues de la couche Vue.
 * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Tai NGUYEN DAC CONG
 */
public abstract class ReseauView {
    protected ReseauController controller = null;

    /**
     * Constructeur de ReseauView
     * @param controller (ReseauController)
     */
    public ReseauView(ReseauController controller) {
        super();
        this.controller = controller;
    }

    /**
     * Renvoie le controleur utilisé
     * @return ReseauController
     */
    public final ReseauController getController() {
        return controller;
    }

    /**
     * Affiche une vue
     */
    public abstract void display();

    /**
     * Ferme une vue
     */
    public abstract void close();
}

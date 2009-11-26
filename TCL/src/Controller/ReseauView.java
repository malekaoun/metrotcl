package Controller;


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
     * Renvoie le controller utilisé
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

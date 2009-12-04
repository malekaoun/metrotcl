package tcl;

import Controller.ReseauController;
import Model.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Exécute l'application TCL :
 * Simulation du traffic d'un réseau de transport en commun.
 * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Tai NGUYEN DAC CONG
 */
public class Main {

    /**
     * Fonction main d'execution du programme
     * @param args String[]
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Init();
    }

    /**
     * Fonction d'initialisation du programme (appellée par le constructeur)
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void Init() throws FileNotFoundException, IOException {
        Reseau reseau = new Reseau();
        String chemin = System.getProperty("user.dir") + File.separator + "src" + File.separator + "tcl" + File.separator + "metroLyon.txt";
        reseau.AjoutStationParLectureFichier(chemin, true);
        ReseauController controller = new ReseauController(reseau);
        controller.displayViews();
        reseau.start();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcl;

import Controller.ReseauController;
import Model.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author p0505657
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        Init();
    }

    public static void Init() throws FileNotFoundException, IOException {

        Reseau reseau = new Reseau();

        String chemin = System.getProperty("user.dir") + File.separator + "src" + File.separator + "tcl" + File.separator + "ListStation.txt";

        reseau.AjoutStationParLectureFichier(chemin);

        //reseau.addMetro(2);
        reseau.addMetro(3);

        ReseauController controller = new ReseauController(reseau);

        controller.displayViews();

        reseau.start();
        //Usager Usager1 = new Usager(10, 20, Station1, Station2);
        //Usager Usager2 = new Usager(10, 30, Station3, Station4);
    }

    
}

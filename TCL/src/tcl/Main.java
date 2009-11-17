/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcl;

import Controller.ReseauController;
import Model.*;

/**
 *
 * @author p0505657
 */
public class Main {

    public static void main(String[] args) {

        Init();


        /*System.out.println("Ligne A :" +ligneA.getListStation());
        System.out.println("Ligne A nextStation 1 :" +ligneA.nextStation(Station1));
        System.out.println("Ligne A :" + metroPremierA);
        System.out.println("Usager1 metro:" + Usager1.getMetro());
        Usager1.descendreMetro(metroPremierA);
        System.out.println("Usager1 metro:" + Usager1.getMetro());
        System.out.println("indice Station3:" + ligneA.indiceStation(Station3));
        System.out.println("usager 1" + Usager1);

        Station1.addUsagerToStation(Usager1);
        System.out.println("liste usager Station1:" + Station1.getListUsager());
        metroPremierA.addUsagerToMetro(Usager2);
        System.out.println("liste usager metropremierA :" +metroPremierA.getListPassager());
        System.out.println("Station arrivée usager 1" + Usager1.getDestination());
        //System.out.println("Station Courante MetroPremierA" + metroPremierA.getLigne().);*/


    }

    public static void Init() {

        Reseau reseau = new Reseau();

        //Ligne 1 Noire
        reseau.addStation(30, 100, 1, 0);
        reseau.addStation(100, 100, 1, 20);
        reseau.addStation(250, 100, 1, 60);
        reseau.addStation(400, 100, 1, 50);
        reseau.addStation(600, 100, 1, 40);

        //Ligne 2 Bleue
        reseau.addStation(50, 20, 2, 0);
        reseau.addStation(100, 100, 2, 20);
        reseau.addStation(50, 400, 2, 40);
        reseau.addStation(50, 600, 2, 80);

        //Ligne 3 Bleu Clair
        reseau.addStation(50, 400, 3, 0);
        reseau.addStation(160, 400, 3, 80);
        reseau.addStation(290, 400, 3, 80);
        reseau.addStation(360, 400, 3, 40);
        reseau.addStation(520, 400, 3, 20);
               

        //Ligne 4 Jaune
        reseau.addStation(400, 50, 4, 0);
        reseau.addStation(400, 100, 4, 20);
        reseau.addStation(360, 400, 4, 40);
        reseau.addStation(400, 600, 4, 80);
        reseau.addStation(400, 700, 4, 80);

        //reseau.addMetro(2);
        reseau.addMetro(3);

        ReseauController controller = new ReseauController(reseau);

        controller.displayViews();


        reseau.start();
        //Usager Usager1 = new Usager(10, 20, Station1, Station2);
        //Usager Usager2 = new Usager(10, 30, Station3, Station4);

    }
}

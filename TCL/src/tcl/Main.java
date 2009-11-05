/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tcl;

import Model.*;
import Vue.*;
import java.util.ArrayList;

/**
 *
 * @author p0505657
 */
public class Main {

public static void main(String[] args)  {

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
public static void Init(){
Station Station1 = new Station(10, 100);
Station Station2 = new Station(100, 100);
Station Station3 = new Station(250, 100);
Station Station4 = new Station(400, 100);
Station Station5 = new Station(600, 100);

Station Station6 = new Station(50, 20);
Station Station7 = new Station(50, 150);
Station Station8 = new Station(50, 400);
Station Station9 = new Station(50, 600);
Station Station10 = new Station(50, 800);

Station Station11 = new Station(650, 400);
Station Station12 = new Station(520, 400);
Station Station13 = new Station(350, 400);
Station Station14 = new Station(290, 400);
Station Station15 = new Station(160, 400);

Station Station16 = new Station(400, 50);
Station Station17 = new Station(400, 600);
Station Station18 = new Station(400, 700);
Station Station19 = new Station(400, 800);
Station Station20 = new Station(400, 4000);


ArrayList<Ligne> lignes = new ArrayList<Ligne>();

Ligne ligneA = new Ligne();
Ligne ligneB = new Ligne();
Ligne ligneC = new Ligne();
Ligne ligneD = new Ligne();

ligneA.addStationToLigne(Station1);
ligneA.addStationToLigne(Station2);
ligneA.addStationToLigne(Station3);
ligneA.addStationToLigne(Station4);
ligneA.addStationToLigne(Station5);
lignes.add(ligneA);

ligneB.addStationToLigne(Station6);
ligneB.addStationToLigne(Station2);
ligneB.addStationToLigne(Station8);
ligneB.addStationToLigne(Station9);
ligneB.addStationToLigne(Station10);
lignes.add(ligneB);

ligneC.addStationToLigne(Station8);
ligneC.addStationToLigne(Station12);
ligneC.addStationToLigne(Station13);
ligneC.addStationToLigne(Station14);
ligneC.addStationToLigne(Station15);
lignes.add(ligneC);

ligneD.addStationToLigne(Station16);
ligneD.addStationToLigne(Station4);
ligneD.addStationToLigne(Station13);
ligneD.addStationToLigne(Station17);
ligneD.addStationToLigne(Station18);
ligneD.addStationToLigne(Station19);
lignes.add(ligneD);

Graphe g=new Graphe(lignes);
Interface windows = new Interface(g);



Usager Usager1 = new Usager(10, 20, Station1, Station2);
Usager Usager2 = new Usager(10, 30, Station3, Station4);

Metro metroPremierA = new Metro(10, 20, ligneA);


}
}

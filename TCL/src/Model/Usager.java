package Model;

import java.util.ArrayList;
import java.util.Observable;

public class Usager extends Observable {

    private int stationdepart;
    private int stationdestination;
    private Metro metro;
    private ArrayList<Correspondance> correspondances = new ArrayList<Correspondance>();

    public Usager(int d, int des) {

        this.stationdepart = d;
        this.stationdestination = des;
        this.metro = null;
    }

    //GETTERS and SETTERS
    public int getDepart() {
        return stationdepart;
    }

    public void setDepart(int depart) {
        this.stationdepart = depart;
    }

    public int getDestination() {
        return stationdestination;
    }

    public void setDestination(int destination) {
        this.stationdestination = destination;
    }

    public void UsagerMonteDansMetro(Metro m, Station s) {

        this.MonterMetro(m);
        m.addUsagerToMetro(this);
        s.removeUsagerFrStation(this);
        m.setNbPlaceRestante(m.getNbPlaceRestante() - 1);
    }

    public void UsagerDescendDuMetro(Metro m, Station s) {

        this.descendreMetro();
        m.removeUsagerFrMetro(this);
        s.addUsagerToStation(this);
        m.setNbPlaceRestante(m.getNbPlaceRestante() + 1);
    }

    public void MonterMetro(Metro m) {
        this.metro = m;
    }

    public void descendreMetro(){

        this.metro = null;
    }

    public Metro getMetro() {
        return metro;
    }
}

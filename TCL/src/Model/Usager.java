package Model;

import java.util.ArrayList;
import java.util.Observable;

public class Usager extends Observable {

    private int stationDepart;
    private int stationDestination;
    private Metro metro;
    private ArrayList<Correspondance> correspondances = new ArrayList<Correspondance>();

    public Usager(int d, int des) {

        this.stationDepart = d;
        this.stationDestination = des;
        this.metro = null;
    }

    //GETTERS and SETTERS
    public int getDepart() {
        return stationDepart;
    }

    public void setDepart(int depart) {
        this.stationDepart = depart;
    }

    public int getDestination() {
        return stationDestination;
    }

    public void setDestination(int destination) {
        this.stationDestination = destination;
    }

    public void usagerMonteDansMetro(Metro m, Station s) {

        this.MonterMetro(m);
        m.addUsagerToMetro(this);
        s.removeUsagerFrStation(this);
        m.setNbPlaceRestante(m.getNbPlaceRestante() - 1);
    }

    public void usagerDescendDuMetro(Metro m, Station s) {

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

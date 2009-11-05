package Model;

import java.util.ArrayList;

public class Usager {

    private String nom;
    private int x;
    private int y;
    private Station depart;
    private Station destination;
    private Metro metro;
    private ArrayList<Correspondance> correspondances=new ArrayList<Correspondance>();

    public Usager(int x, int y, Station d, Station des) {
        this.nom = "guest";
        this.x = x;
        this.y = y;
        this.depart = d;
        this.destination = des;
        this.metro = null;
    }

    public Usager(int x, int y, String n, Station d, Station des) {
        this.nom = n;
        this.x = x;
        this.y = y;
        this.depart = d;
        this.destination = des;
    }


    //GETTERS and SETTERS
    public Station getDepart() {
        return depart;
    }

    public void setDepart(Station depart) {
        this.depart = depart;
    }

    public Station getDestination() {
        return destination;
    }

    public void setDestination(Station destination) {
        this.destination = destination;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void MonterMetro(Metro m){

        this.metro = m ;
    }
    public void descendreMetro(Metro m){
        this.metro = null;
    }
    public Metro getMetro(){
        return metro;
    }
}

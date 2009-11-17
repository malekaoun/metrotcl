package Model;

import java.util.ArrayList;
import java.util.Observable;

public class Usager extends Observable{

    private double convDegGrad = 0.0174533;
    private int x;
    private int y;
    private double dir;
    private int Oldx;
    private int Oldy;
    private String nom;
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
    public void monterMetro(Metro m){

        this.metro = m ;
    }
    public void descendreMetro(Metro m){
        this.metro = null;
    }
    public Metro getMetro(){
        return metro;
    }
     public void avancer(int dist) {
        int newX = (int) Math.round(x + dist * Math.cos(convDegGrad * this.dir));
        int newY = (int) Math.round(y + dist * Math.sin(convDegGrad * this.dir));

        this.Oldx = x;
        this.Oldy = y;
        x = newX;
        y = newY;

        setChanged();
        notifyObservers();
    }
}

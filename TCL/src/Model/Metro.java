/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author p0505657
 */
public class Metro extends Observable {

    private double convDegGrad = 0.0174533;
    private int x;
    private int y;
    private int Oldx;
    private int Oldy;
    private ArrayList<Usager> listPassager = new ArrayList<Usager>();
    private static final int maxPlace = 50;
    private Ligne ligne;
    private int compteur = 1;
    private boolean avance = true;

    public Metro(int x, int y, Ligne l) {
        this.x = x;
        this.y = y;
        this.ligne = l;
    }

    public Metro(int x, int y, ArrayList<Usager> list, Ligne l) {
        this.x = x;
        this.y = y;
        this.listPassager = list;
        this.ligne = l;
    }

    public void addUsagerToMetro(Usager u) {
        if (listPassager.size() < maxPlace) {
            this.listPassager.add(u);
        }
    }

    public void removeUsagerFrMetro(Usager u) {
        this.listPassager.remove(u);
    }

    public void avancer(int dist, int dir) {
        int newX = (int) Math.round(x + dist * Math.cos(convDegGrad * dir));
        int newY = (int) Math.round(y + dist * Math.sin(convDegGrad * dir));

        this.Oldx = x;
        this.Oldy = y;
        x = newX;
        y = newY;

        setChanged();
        notifyObservers();
    }

    public void recaler(int dist, int dir) {
        int newX = (int) Math.round(x - dist * Math.cos(convDegGrad * dir));
        int newY = (int) Math.round(y - dist * Math.sin(convDegGrad * dir));
        x = newX;
        y = newY;
    }

    //GETTERS and SETTERS
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

    public ArrayList<Usager> getListPassager() {
        return listPassager;
    }

    public void setListPassager(ArrayList<Usager> listPassager) {
        this.listPassager = listPassager;
    }

    public Ligne getLigne() {
        return ligne;
    }

    public void setLigne(Ligne ligne) {
        this.ligne = ligne;
    }

    public int getMaxPlace() {
        return maxPlace;
    }

    public int getCompteur() {
        return compteur;
    }

    public void setCompteur(int compteur) {
        this.compteur = compteur;
    }

    public boolean isAvance() {
        return avance;
    }

    public void setAvance(boolean avance) {
        this.avance = avance;
    }

    public int targetDir(int x, int y) {
        int d = 0;
        if (this.getX() > x) {
            if (this.getY() > y) {
                double q = (double) ((double) this.getX() - x) / ((double) this.getY() - y);
                d = (int) (((Math.atan(1 / q)) / Math.PI) * 180 + 180);
            } else {
                double q = (double) ((double) this.getX() - x) / ((double) y - this.getY());
                d = (int) (((Math.atan(q)) / Math.PI) * 180 + 90);
            }
        } else {
            if (this.getY() > y) {
                double q = (double) ((double) this.getX() - x) / ((double) this.getY() - y);
                d = (int) (((Math.atan(1 / q)) / Math.PI) * 180 + 180);
            } else {
                double q = (double) ((double) this.getX() - x) / ((double) y - this.getY());
                d = (int) (((Math.atan(q)) / Math.PI) * 180 + 90);
            }
        }
        return d;
    }

    public int getOldx() {
        return this.Oldx;
    }

    public int getOldy() {
        return this.Oldy;
    }
}

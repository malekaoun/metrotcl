
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
    private double dir;
    private int Oldx;
    private int Oldy;
    private ArrayList<Usager> listPassager = new ArrayList<Usager>();
    private static final int maxPlace = 50;
    private int compteur = 0;
    private boolean avance = true;
    private boolean sensinverse = false;
    private int tempsArret = 5;
    private int NbPlaceRestante;

    public Metro(int x, int y) {
        this.x = x;
        this.y = y;
        NbPlaceRestante = 50;
    }

    public Metro(int x, int y, ArrayList<Usager> list) {
        this.x = x;
        this.y = y;
        this.listPassager = list;
        NbPlaceRestante = 50 ;
    }

    public void addUsagerToMetro(Usager u) {
        if (listPassager.size() < maxPlace) {
            this.listPassager.add(u);
        }
    }

    public void removeUsagerFrMetro(Usager u) {
        this.listPassager.remove(u);
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

    public double getdir() {
        return this.dir;
    }

    public void setdir(double d) {
        this.dir = d;
    }

    public ArrayList<Usager> getListPassager() {
        return listPassager;
    }

    public void setListPassager(ArrayList<Usager> listPassager) {
        this.listPassager = listPassager;
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
   
    public double VaVers(int x, int y) {
        double adjacent = x - this.x;
        double oppose = y - this.y;

        double angle = 0;

        double angle2 = 0;

        if (adjacent > 0 && oppose <= 0) {

            angle = 180 * Math.atan(-oppose / adjacent) / Math.PI;


            angle2 = ((double) (360 - angle - this.dir));
        }
        if (adjacent > 0 && oppose >= 0) {

            angle = 180 * Math.atan(oppose / adjacent) / Math.PI;

            angle2 = ((double) (-this.dir + angle));
        }
        if (adjacent < 0 && oppose <= 0) {

            angle = 180 * Math.atan(oppose / adjacent) / Math.PI;

            angle2 = ((double) (180 - this.dir + angle));
        }

        if (adjacent < 0 && oppose >= 0) {

            angle = 180 * Math.atan(-oppose / adjacent) / Math.PI;


            angle2 = ((double) (180 - angle - this.dir));
        }

        double newdir = (this.dir + angle2) % 360;

        return newdir;
    }

    public int getOldx() {
        return this.Oldx;
    }

    public int getOldy() {
        return this.Oldy;
    }

    public boolean getSensInverse() {
        return this.sensinverse;
    }

    public void setSensInverse(boolean b) {
        this.sensinverse = b;
    }

    public int gettempsArret() {
        return this.tempsArret;
    }

    public void settempsArret(int b) {
        this.tempsArret = b;
    }

    public boolean estAUneStation(Station s, int i) {

        return ((this.getX() >= (s.getX() - i)) && (this.getX() <= (s.getX() + i)) && (this.getY() >= (s.getY() - i)) && (this.getY() <= (s.getY() + i)));
    }

    public int getNbPlaceRestante(){

        return NbPlaceRestante;
    }
    public void setNbPlaceRestante(int NbPlaceRestante){

        this.NbPlaceRestante = NbPlaceRestante ;
    }
}

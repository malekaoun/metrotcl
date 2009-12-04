package Model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Cette classe définit un metro qui contient ses coordonnées géométriques actuelle, ses anciennes coordonnées pour l'affichage et qui herite de Observable pour le réaffichage
 *
 * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
 */
public class Metro extends Observable {

    private double convDegGrad = 0.0174533;
    private int x;
    private int y;
    private double dir;
    private int Oldx;
    private int Oldy;
    private ArrayList<Usager> listPassager;
    private static final int maxPlace = 50;
    private int compteur = 0;
    private boolean avance;
    private boolean sensinverse = false;
    private int tempsArret = 5;
    private int NbPlaceRestante;

    /**Constructeur de la classe metro avec ses coordonnées de départ
     *
     * @param x entier coordonnée x
     * @param y entier coordonnée y
     */
    public Metro(int x, int y) {
        this.x = x;
        this.y = y;
        NbPlaceRestante = maxPlace;
        listPassager = new ArrayList<Usager>();
        avance = true;
    }

    /**Ajout d'un usager dans un metro si il reste de la place
     *
     * @param u Usager
     */
    public void addUsagerToMetro(Usager u) {
        if (listPassager.size() < maxPlace) {
            this.listPassager.add(u);
        }
    }

    /**Enlever un usager du metro
     *
     * @param u Usager
     */
    public void removeUsagerFrMetro(Usager u) {
        this.listPassager.remove(u);
    }

    /** Methode permettant au metro d'avancer d'une distance dist, avec appel à la vue pour actualiser son affichage
     *
     * @param dist
     */
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

    //GETTERS and SETTERS
    /**Getter coordonnée X
     *
     * @return entier X
     */
    public int getX() {
        return x;
    }

    /**Setter coordonnée X
     *
     * @param x entier
     */
    public void setX(int x) {
        this.x = x;
    }

    /**Getter coordonnée Y
     *
     * @return entier y
     */
    public int getY() {
        return y;
    }

    /**Setter coordonnée Y
     *
     * @param y entier
     */
    public void setY(int y) {
        this.y = y;
    }

    /**Getter de la direction
     *
     * @return double dir direction
     */
    public double getdir() {
        return this.dir;
    }

    /**Setter de la direction
     *
     * @param d double direction
     */
    public void setdir(double d) {
        this.dir = d;
    }

    /**Getter de la liste des passager du metro
     *
     * @return Arraylist d'usager
     */
    public ArrayList<Usager> getListPassager() {
        return listPassager;
    }

    /**Setter de la liste des passager du metro
     *
     * @param listPassager Arraylist d'usager
     */
    public void setListPassager(ArrayList<Usager> listPassager) {
        this.listPassager = listPassager;
    }

    /**Getter capacité du metro en usager
     *
     * @return entier capacité du metro en usager
     */
    public int getMaxPlace() {
        return maxPlace;
    }

    /**Getter compteur correspondant à l'indice courant dans la liste de correspondance
     *
     * @return entier compteur
     */
    public int getCompteur() {
        return compteur;
    }

    /**Setter compteur correspondant à l'indice courant dans la liste de correspondance
     *
     * @param compteur entier
     */
    public void setCompteur(int compteur) {
        this.compteur = compteur;
    }

    /**Getter du booleen vrai si le metro avance, faux sinon
     *
     * @return booleen vrai si le metro avance faux sinon
     */
    public boolean isAvance() {
        return avance;
    }

    /**Setter du booleen vrai si le metro avance, faux sinon
     *
     * @param avance booleen
     */
    public void setAvance(boolean avance) {
        this.avance = avance;
    }

    /**Retourne la direction que doit prendre le metro pour aller aux coordonnées x et y
     *
     * @param x entier coordonnée
     * @param y entier coordonnée
     * @return double la direction
     */
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

        if (adjacent == 0 && oppose <= 0) {
            angle2 = ((double) (190));
        }

        double newdir = (this.dir + angle2) % 360;

        return newdir;
    }

    /**Getter de l'ancienne coordonnée x du metro
     *
     * @return entier coordonnée x
     */
    public int getOldx() {
        return this.Oldx;
    }

    /**Getter de l'ancienne coordonnée y du metro
     *
     * @return entier coordonnée y
     */
    public int getOldy() {
        return this.Oldy;
    }

    /**Getter du sens du metro sur sa ligne, faux sens de départ, vrai  sens inverse
     *
     * @param b booleen sens du metro
     */
    public boolean getSensInverse() {
        return this.sensinverse;
    }

    /**Setter du sens du metro sur sa ligne, faux sens de départ, vrai  sens inverse
     *
     * @param b booleen sens du metro
     */
    public void setSensInverse(boolean b) {
        this.sensinverse = b;
    }

    /**Getter du temps d'arret du metro dans chaque station
     *
     * @return entier unité de temps
     */
    public int gettempsArret() {
        return this.tempsArret;
    }

    /**Setter du temps d'arret du metro dans chaque station
     *
     * @param b entier unité de temps
     */
    public void settempsArret(int b) {
        this.tempsArret = b;
    }

    /**Renvoie vrai si le metro se trouve dans la station s à i unité d'affichage pret
     *
     * @param s Station
     * @param i entier unité arbitraire d'affichage
     * @return vrai si le metro se trouve dans s, faux sinon
     */
    public boolean estAUneStation(Station s, int i) {

        return ((this.getX() >= (s.getX() - i)) && (this.getX() <= (s.getX() + i)) && (this.getY() >= (s.getY() - i)) && (this.getY() <= (s.getY() + i)));
    }

    /**Getter du nombre de places restantes dans le metro
     *
     * @return entier nombre de places
     */
    public int getNbPlaceRestante() {

        return NbPlaceRestante;
    }

    /**Setter du nombre de places restantes dans le metro
     *
     * @param NbPlaceRestante entier
     */
    public void setNbPlaceRestante(int NbPlaceRestante) {

        this.NbPlaceRestante = NbPlaceRestante;
    }
}

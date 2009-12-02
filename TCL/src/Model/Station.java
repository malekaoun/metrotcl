package Model;

import java.util.ArrayList;

/**
 * Cette classe définit une station qui contient leurs coordonnées géométriques, un nom,
 * une liste des usagers, un metro s'elle en a, un booléen pour dire s'elle est une correspondance,
 * un autre booléen pour dire s'elle est pris par un metro.
 * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
 */
public class Station {

    private int x;
    private int y;
    private String nom;
    private ArrayList<Usager> listUsager = new ArrayList<Usager>();
    private boolean prisMetro;
    private Metro metro;
    private boolean correspondante = false;

    /**
     * Constructeur de la classe Station avec leurs coordonnées géométriques.
     * @param x Entier
     * @param y Entier
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public Station(int x, int y) {
        this.x = x;
        this.y = y;
        this.prisMetro = false;
        this.nom="";
    }

    /**
     * Contructeur de la classe Station avec leurs coordonnées géométriques et son nom.
     * @param x Entier
     * @param y Entier
     * @param nom String
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public Station(int x, int y, String nom) {
        this.x = x;
        this.y = y;
        this.prisMetro = false;
        this.nom = nom;
    }

    /**
     * Constructeur da le classe Station avec leurs coordonnées géométriques et sa liste des Usagers.
     * @param x Entier
     * @param y Entier
     * @param list liste des Usagers
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public Station(int x, int y, ArrayList<Usager> list) {
        this.x = x;
        this.y = y;
        this.listUsager = list;
        this.prisMetro = false;
    }

    /**
     * Ajoute un Usager dans la liste des usagers d'une station.
     * @param u Usager
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void addUsagerToStation(Usager u) {
        this.listUsager.add(u);
    }

    /**
     * Supprime un Usager de la liste des usagers d'une station.
     * @param u Usager
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void removeUsagerFrStation(Usager u) {
        this.listUsager.remove(u);
    }

    /**
     * Renvoie l'abscisse de la station
     * @return Entier
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public int getX() {
        return x;
    }

    /**
     * Met une abscisse de la station
     * @param x Entier
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Renvoie l'ordonnée de la station.
     * @return Entier
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public int getY() {
        return y;
    }

    /**
     * Met une ordonnée de la station
     * @param y Entier
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Renvoie la liste des Usagers de la station
     * @return la liste des Usagers
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public ArrayList<Usager> getListUsager() {
        return listUsager;
    }

    /**
     * Ajoute une liste des Usagers dans la station
     * @param listUsager liste des Usagers
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void setListUsager(ArrayList<Usager> listUsager) {
        this.listUsager = listUsager;
    }

    /**
     * Renvoie le metro de la station
     * @return Metro
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public Metro getMetro() {
        return metro;
    }

    /**
     * Ajoute une metro dans la station
     * @param metro Metro
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void setMetro(Metro metro) {
        this.metro = metro;
    }

    /**
     * Renvoie vrai si la station est pris par un metro, faux sinon.
     * @return Booléen
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public boolean isPrisMetro() {
        return prisMetro;
    }

    /**
     * Met un booléen au attribut prisMetro dans la station.
     * @param prisMetro Booléen
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void setPrisMetro(boolean prisMetro) {
        this.prisMetro = prisMetro;
    }

    /**
     * Renvoie vrai si la station est une correspondance (appartient à plus de 2 Lignes), faux sinon.
     * @return Booléen
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public boolean isCorrespondante() {
        return correspondante;
    }

    /**
     * Met un booléen au attribut correspondance dans la station.
     * @param correspondante Booléen
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void setCorrespondante(boolean correspondante) {
        this.correspondante = correspondante;
    }

    /**
     * Renvoie le nom de la station.
     * @return String
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public String getNom() {
        return nom;
    }

    /**
     * Met un nom à la station.
     * @param nom String
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}

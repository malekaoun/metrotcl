package Model;

import java.util.ArrayList;

/**
 * Cette classe définit une ligne de transport (ou ligne de metro) qui contient
 * une liste des stations, une listes des distances entre les stations et une liste des metros.
 * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
 */
public class Ligne {

    private ArrayList<Station> listStation;
    private ArrayList<Distance> aretes;
    private ArrayList<Metro> metros;

    /**
     * Constructeur de la classe Ligne.
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public Ligne() {
        listStation= new ArrayList<Station>();
        aretes= new ArrayList<Distance>();
        this.metros = new ArrayList<Metro>();

    }

    /**
     * Constructeur de la classe Ligne avec une liste des stations en paramètre.
     * @param ls la liste des stations
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public Ligne(ArrayList<Station> ls) {
        this.listStation = ls;
    }

    /**
     * Ajoute une station dans la liste des stations de la Ligne.
     * @param s Station
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void addStationToLigne(Station s) {
        this.listStation.add(s);
    }

    /**
     * Supprime une station de la liste des stations de la Ligne.
     * @param s Station
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void removeStationToLigne(Station s) {
        this.listStation.remove(s);
    }

    /**
     * Renvoie l'indice d'une station dans la liste des stations de la Ligne.
     * @param s Station
     * @return Entier de l'indice
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public int indiceStation(Station s) {
        int result = -1;
        if (listStation.contains(s)) {
            for (int i = 0; i < listStation.size(); i++) {
                if (listStation.get(i).equals(s)) {
                    return i;
                }
            }
        }
        return result;
    }

    /**
     * Renvoie la station suivante à partir de la direction et la station ectuelle du metro.
     * @param m Metro
     * @return Station suivante
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public Station nextstation(Metro m) {
        if(m.getCompteur()>=this.getListStation().size()-1 || m.getCompteur()<0){
            m.setSensInverse(!m.getSensInverse());
            if(!m.getSensInverse()){
                m.setCompteur(m.getCompteur()+1);
            } else{
                m.setCompteur(m.getCompteur()-1);
            }
        } if(!m.getSensInverse()){
           return this.getListStation().get(m.getCompteur()+1);
        } else{
            return this.getListStation().get(m.getCompteur());
        }
    }

    /**
     * Renvoie la liste des stations de la Ligne.
     * @return la liste des stations
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public ArrayList<Station> getListStation() {
        return listStation;
    }

    /**
     * Renvoie une station à partir d'un entier.
     * @param i Entier de l'indice de la station
     * @return Station
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public Station getStationById(int i) {
        return listStation.get(i);
    }

    /**
     * Met une liste des stations dans la Ligne.
     * @param listStation Liste des stations
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void setListStation(ArrayList<Station> listStation) {
        this.listStation = listStation;
    }

    /**
     * Ajoute une distance dans la liste des distances de la Ligne.
     * @param d Distance
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void addArete (Distance d) {
            aretes.add(d); 
    }

    /**
     * Supprime une distance de la liste des distances de la Ligne.
     * @param d Distance
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void rmArete (Distance d) {
        aretes.remove(d);
    }

    /**
     * Renvoie la distance entre deux stations de la même ligne
     * @return Entier de la somme des aretes entre les deux stations
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public int getDistance (Station s1, Station s2) {
         int nb=0;
         boolean calculdist=false;
         for(int i=0; i<listStation.size(); i++){
             if(calculdist) {
                 nb=nb+aretes.get(i-1).getDistance();
             } if(listStation.get(i)==s1 || listStation.get(i)==s2){
                 calculdist=!calculdist;
             }
         }
         return nb;
    }

    /**
     * Renvoie la liste des distances de la Ligne.
     * @return la liste des distances
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public ArrayList<Distance> getAretes() {
        return aretes;
    }

    /**
     * Met une liste des distances dans la Ligne.
     * @param aretes Liste des distances
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void setAretes(ArrayList<Distance> aretes) {
        this.aretes = aretes;
    }

    /**
     * Renvoie la liste des metros de la Ligne.
     * @return la liste des metros
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public ArrayList<Metro> getMetros() {
        return metros;
    }

    /**
     * Met une liste des metros dans la Ligne.
     * @param m Liste des metros
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void addMetro(Metro m) {
        this.getMetros().add(m);
    }
}

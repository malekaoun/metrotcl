package Model;

import java.util.ArrayList;

public class Ligne {

    private ArrayList<Station> listStation;
    private ArrayList<Distance> aretes;
    private ArrayList<Metro> metros;


    public Ligne() {
        listStation= new ArrayList<Station>();
        aretes= new ArrayList<Distance>();
        this.metros = new ArrayList<Metro>();

    }

    public Ligne(ArrayList<Station> ls) {

        this.listStation = ls;
    }

    public void addStationToLigne(Station s) {
        this.listStation.add(s);
    }

    public void InsertStationToLigne(Station s, int id){


    }

    public void removeStationToLigne(Station s) {
        this.listStation.remove(s);
    }



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

    public Station nextstation(Metro m)
    {

        
        if(m.getCompteur()>=this.getListStation().size()-1 || m.getCompteur()<0)
        {
            m.setSensInverse(!m.getSensInverse());
            if(!m.getSensInverse())
            {
                m.setCompteur(m.getCompteur()+1);

            }
            else{
                m.setCompteur(m.getCompteur()-1);
            }
        }

        if(!m.getSensInverse())
        {
           return this.getListStation().get(m.getCompteur()+1);
        }
        else
        {
            return this.getListStation().get(m.getCompteur());
        }


    }

    //GETTERS and SETTERS


    public ArrayList<Station> getListStation() {
        return listStation;
    }

    public Station getStationById(int i) {
        return listStation.get(i);
    }

    public void setListStation(ArrayList<Station> listStation) {
        this.listStation = listStation;
    }


    public void addArete (Distance d) {

            aretes.add(d); 
    }

    public void rmArete (Distance d) {
        aretes.remove(d);
    }

    /**Renvoie la distance entre deux stations de la même ligne
     *
     * @return int Somme des aretes entre les deux stations
     */
    public int getDistance (Station s1, Station s2) {

         int nb=0;
         boolean calculdist=false;

         for(int i=0; i<listStation.size(); i++){

             if(calculdist)
             {
                 nb=nb+aretes.get(i-1).getDistance();
             }
             if(listStation.get(i)==s1 || listStation.get(i)==s2)
             {
                 calculdist=!calculdist;
             }
         }

         return nb;
    }

    public ArrayList<Distance> getAretes() {
        return aretes;
    }

    public void setAretes(ArrayList<Distance> aretes) {
        this.aretes = aretes;
    }

    public ArrayList<Metro> getMetros() {
        return metros;
    }

    public void addMetro(Metro m) {

        this.getMetros().add(m);
    }
}

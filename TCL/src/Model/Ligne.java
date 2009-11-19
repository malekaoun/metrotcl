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
        //System.out.println(m.getCompteur());
        //System.out.println(m.getSensInverse());
        
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
       // System.out.println(m.getSensInverse());
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

    public void setListStation(ArrayList<Station> listStation) {
        this.listStation = listStation;
    }


    public void addArete (Distance d) {

            for (int i = 0 ; i < aretes.size(); i++) {   /*Mise à jour de la distance entre 2 sommets
                                                         * si celle-ci a déjà été précisée antérieurement*/
            if (aretes.get(i).getS1() == d.getS1())
                if (aretes.get(i).getS2() == d.getS2()) {
                    aretes.get(i).setDistance(d.getDistance());
                    return;
                }
            else if (aretes.get(i).getS1() == d.getS2())
                if (aretes.get(i).getS2() == d.getS1()) {
                    aretes.get(i).setDistance(d.getDistance());
                    return;
                }
            }
            aretes.add(d); // Sinon on la rajoute
    }

    public void rmArete (Distance d) {
        aretes.remove(d);
    }

    public int getDistance (Station s1, Station s2) {
         for (int i = 0 ; i < aretes.size(); i++) {
            if (aretes.get(i).getS1() == s1)
                if (aretes.get(i).getS2() == s2)
                    return aretes.get(i).getDistance();
            else if (aretes.get(i).getS1() == s2)
                if (aretes.get(i).getS2() == s1)
                    return aretes.get(i).getDistance();
            }
         return -1;
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

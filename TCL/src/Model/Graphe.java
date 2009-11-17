package Model;

import java.util.ArrayList;

public class Graphe {

    private ArrayList<Station> sommets;
    private ArrayList<Ligne> lignes;

    public Graphe() {
        sommets = new ArrayList<Station>();
        lignes = new ArrayList<Ligne>();
    }

    public Station chercheStationDansList(int x, int y){

        for(int i=0; i<sommets.size();i++){
            if(sommets.get(i).getX()== x && sommets.get(i).getY()== y)
                return sommets.get(i);
        }

        return null;
    }

    public void addSommet(Station s) {
        if (!sommets.contains(s)) {
            sommets.add(s);
        } else {
            System.out.println("addSommet() : sommet existant");
        }
    }

    public void rmSommet(Station s) {
        sommets.remove(s);
    }

    public ArrayList<Station> getSommets() {
        return sommets;
    }

    public void setSommets(ArrayList<Station> sommets) {
        this.sommets = sommets;
    }

    public void addLigne(Ligne l) {
        this.lignes.add(l);
    }

    public ArrayList<Ligne> getLignes() {
        return lignes;
    }
}

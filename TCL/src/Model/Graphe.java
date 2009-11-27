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

    public int GetIdOfStation(Station s){

        for(int i=0; i<sommets.size();i++){
            if(sommets.get(i).getX()== s.getX() && sommets.get(i).getY()== s.getY())
                return i;
        }

        return -1;
    }

    public int getIdLigneFrMetro(Metro m){
        for (int i=0; i<lignes.size(); i++){
            if(lignes.get(i).getMetros().contains(m)){
                return i;
            }
        }
        return -1;
    }

    public Station getStationFrId (int id){
        if(id<sommets.size()){
            return sommets.get(id);
        } else {
            return null;
        }
    }

    public ArrayList getIdLigneFrIdStation(int id){
        Station s = getStationFrId(id);
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(s!=null){
            res = getIdLigneFrStation(s);
        }
        return res;
    }

    public ArrayList getIdLigneFrStation(Station s){
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i=0; i<lignes.size(); i++){
                if(lignes.get(i).getListStation().contains(s)){
                    res.add(i);
                }
            }
        return res;
    }

    public void addSommet(Station s) {
        if (!sommets.contains(s)) {
            sommets.add(s);
        } else {
            System.out.println("addSommet() : sommet existant");
        }
    }

    public Correspondance getCorres(int depart, int arrivee){
        Correspondance res = new Correspondance();
        ArrayList<Integer> listLignesDep = getIdLigneFrIdStation(depart);
        ArrayList<Integer> listLignesArr = getIdLigneFrIdStation(arrivee);
        for (int i=0; i<listLignesArr.size(); i++){
            Ligne ligne = lignes.get(listLignesArr.get(i));
            for (int j=0; j<ligne.getListStation().size(); j++){
                if(ligne.getListStation().get(j).isCorrespondante()){
                    Station tmp = ligne.getListStation().get(j);
                    ArrayList<Integer> listLignes = getIdLigneFrStation(tmp);
                    for(int k=0; k<listLignes.size(); k++){
                        if (listLignesDep.contains(listLignes.get(k))){
                            res.setStation(tmp);
                            res.setLigne(lignes.get(listLignes.get(k)));
                        }
                    }
                }
            }
        }
        return res;
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

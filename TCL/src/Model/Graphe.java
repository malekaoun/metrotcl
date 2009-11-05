package Model;

import java.util.ArrayList;

public class Graphe {
    private ArrayList<Station> sommets;
    private ArrayList<Distance> aretes;
    private ArrayList<Ligne> lignes;

    public Graphe () {
        sommets = new ArrayList<Station>();
        aretes = new ArrayList<Distance>();
    }

    public Graphe (ArrayList<Ligne> l) {
        lignes = l;
         sommets = new ArrayList<Station>();
        for(int i=0; i<lignes.size(); i++){
            for(int j=0; j< lignes.get(i).getListStation().size(); j++){
                sommets.add(lignes.get(i).getListStation().get(j));
            }
        }
        aretes = new ArrayList<Distance>();
    }

    public Graphe (ArrayList<Station> s, ArrayList<Distance> d) {
        sommets = s;
        aretes = d;
    }

    public void addSommet (Station s) {
        if (!sommets.contains(s))
            sommets.add(s);
        else System.out.println("addSommet() : sommet existant");
    }

    public void rmSommet (Station s) {
        sommets.remove(s);
    }

    public void addArete (Distance d) {

        if (sommets.contains(d.getS1()) && sommets.contains(d.getS2())) {
            for (int i = 0 ; i < aretes.size(); i++) {  /* Mise à jour de la distance entre 2 sommets
                                                         * si celle-ci a déjà été précisée antérieurement */
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
        else System.out.println("addArete() : sommet(s) inexistant(s)");
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

    public ArrayList<Station> getSommets() {
        return sommets;
    }

    public void setSommets(ArrayList<Station> sommets) {
        this.sommets = sommets;
    }

    public ArrayList<Ligne> getLignes() {
        return lignes;
    }

}
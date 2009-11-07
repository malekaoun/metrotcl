package Model;

import java.util.ArrayList;

public class Reseau extends Thread{

    private Graphe graphe;
    private ArrayList<Metro> metros;
    private ArrayList<Ligne> lignes;

    public Reseau(Graphe g) {
        this.graphe = g;
        this.metros = new ArrayList<Metro>();
        this.lignes = new ArrayList<Ligne>();
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < metros.size(); i++) {
                // metros.get(i).avancer(i);
            }
        }
    }

    public Graphe getGraphe() {
        return this.graphe;
    }

    public void addMetro(Metro m) {

        metros.add(m);
    }
}

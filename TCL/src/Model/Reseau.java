package Model;

import Vue.Feuille;
import java.util.ArrayList;

public class Reseau extends Thread {

    private Feuille f;
    private Graphe graphe;
    private ArrayList<Metro> metros;

    public Reseau() {

        this.graphe = new Graphe();
        this.metros = new ArrayList<Metro>();
    }

    @Override
    public void run() {
        while (true) {
            //int j=1;
            for (int i = 0; i < metros.size(); i++) {
                Metro m = metros.get(i);
                if (m.isAvance()) {
                    if (m.getX() < m.getLigne().getListStation().get(m.getCompteur()).getX() || m.getY() < m.getLigne().getListStation().get(m.getCompteur()).getY()) {
                        int dir = m.targetDir(m.getLigne().getListStation().get(m.getCompteur()).getX(), m.getLigne().getListStation().get(m.getCompteur()).getY());
                        m.avancer(10, dir);
                    } else {
                        m.setCompteur(m.getCompteur() + 1);
                        if (m.getCompteur() == m.getLigne().getListStation().size()) {
                            m.setAvance(false);
                            m.setCompteur(m.getCompteur() - 1);
                        }
                    }
                } else {
                    System.out.println("recaler " + m.getCompteur() + " x=" + m.getLigne().getListStation().get(m.getCompteur()).getX() +
                            " y=" + m.getLigne().getListStation().get(m.getCompteur()).getY() + " m.x=" + m.getX() + " m.y=" + m.getY());
                    if (m.getX() > m.getLigne().getListStation().get(m.getCompteur()).getX() || m.getY() > m.getLigne().getListStation().get(m.getCompteur()).getY()) {
                        int dir = m.targetDir(m.getLigne().getListStation().get(m.getCompteur()).getX(), m.getLigne().getListStation().get(m.getCompteur()).getY());
                        m.avancer(10, dir);
                    } else {
                        m.setCompteur(m.getCompteur() - 1);
                        if (m.getCompteur() == -1) {
                            m.setAvance(true);
                            m.setCompteur(m.getCompteur() + 1);
                        }
                    }
                }

                try {
                    Thread.sleep(50);
                } catch (Exception e) {
                    System.err.println("erreur: " + e);
                }
            }
        }

    }

    public Graphe getGraphe() {
        return this.graphe;
    }

    public Metro addMetro(int idLigne) {

        Station stationDepart = this.graphe.getLignes().get(idLigne - 1).getListStation().get(0);

        Metro m = new Metro(stationDepart.getX(), stationDepart.getY(), this.graphe.getLignes().get(idLigne - 1));

        metros.add(m);

        return m;
    }

    public ArrayList<Metro> getMetros() {
        return metros;
    }

    public void setMetros(ArrayList<Metro> metros) {
        this.metros = metros;
    }

    public void addStation(int x, int y, int idLigne, int distance) {

        Station S = this.getGraphe().ChercheStationdansList(x, y);

        if (S == null) {

            S = new Station(x, y);
        }

        this.graphe.addSommet(S);

        if (idLigne > this.graphe.getLignes().size()) {
            Ligne l = new Ligne();
            l.addStationToLigne(S);
            this.graphe.addLigne(l);
        } else {

            ArrayList<Station> listStation = this.graphe.getLignes().get(idLigne - 1).getListStation();

            Distance d = new Distance(listStation.get(listStation.size() - 1), S, distance);

            this.graphe.getLignes().get(idLigne - 1).addArete(d);
            this.graphe.getLignes().get(idLigne - 1).addStationToLigne(S);
        }

    }



    public Feuille getF() {
        return f;
    }

    public void setF(Feuille f) {
        this.f = f;
    }
}

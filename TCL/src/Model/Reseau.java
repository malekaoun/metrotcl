package Model;

import java.util.ArrayList;

public class Reseau extends Thread {

    private Graphe graphe;
    private boolean stop;
    private int tempsArret = 20;
    private int vitesseMetro = 500;

    public Reseau() {

        this.graphe = new Graphe();
        this.stop = false;
    }

    @Override
    public void run() {

        while (!stop) {
            for (int i = 0; i < this.graphe.getLignes().size(); i++) {

                Ligne l = this.graphe.getLignes().get(i);

                for (int j = 0; j < l.getMetros().size(); j++) {

                    Metro m = l.getMetros().get(j);
                    if (m.isAvance()) {

                        Station s = l.nextstation(m);
                        int distanceToNextStation = l.getAretes().get(m.getCompteur()).getDistance();
                        m.setdir(m.VaVers(s.getX(), s.getY()));
                        System.out.println("disttonext" + distanceToNextStation);

                        m.avancer(this.vitesseMetro / distanceToNextStation);


                        if (m.estAUneStation(s, 15)) {

                            for(int n=0; n<m.getListPassager().size();n++){
                                Usager u = m.getListPassager().get(n);
                                if(u.getDestination().equals(s)){
                                    m.getListPassager().remove(u);
                                }
                            }
                            if (!s.getListUsager().isEmpty()) {

                                if (m.getNbPlaceRestante() > 0) {

                                    if (m.getNbPlaceRestante() > s.getListUsager().size()) { // on peut faire rentrer tout le monde

                                        int nombrePersonneEntrante = s.getListUsager().size();

                                        for (int k = nombrePersonneEntrante - 1; k >= 0 ; k--) {
                                            System.out.println("k est egal a :" + k + "size egale a :" + s.getListUsager().size());
                                            s.getListUsager().get(k).monterMetro(m);
                                            m.addUsagerToMetro(s.getListUsager().get(k));
                                            s.getListUsager().remove(s.getListUsager().get(k));
                                            m.setNbPlaceRestante(m.getNbPlaceRestante()-1);
                                        }
                                    } else {
                                        System.out.println("NbplaceRestante :" + m.getNbPlaceRestante());
                                        System.out.println("NbPersonne qui attende  :" + s.getListUsager().size());
                                        int nombrePersonneEntrante = m.getNbPlaceRestante();
                                        for (int k = nombrePersonneEntrante - 1; k >= 0; k--) {
                                        System.out.println("NbPersonne qui attende dans la boucle  :" + s.getListUsager().size());
                                        System.out.println("NbplaceRestante :" + m.getNbPlaceRestante());
                                         System.out.println("k est egal a :" + k );
                                            s.getListUsager().get(k).monterMetro(m);
                                            m.addUsagerToMetro(s.getListUsager().get(k));
                                            s.getListUsager().remove(s.getListUsager().get(k));
                                            m.setNbPlaceRestante(m.getNbPlaceRestante()-1);
                                        }
                                    }


                                }
                            }


                            if (!m.getSensInverse()) {
                                m.setCompteur(m.getCompteur() + 1);
                                m.setAvance(false);
                            } else {
                                m.setCompteur((m.getCompteur() - 1));
                                m.setAvance(false);
                            }
                        }

                    } else {

                        if (m.gettempsArret() == 0) {
                            m.setAvance(true);
                            m.settempsArret(this.gettempsArret());
                        } else {
                            m.settempsArret(m.gettempsArret() - 1);
                        }
                    }
                }
            }
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.err.println("erreur: " + e);
            }


        }
    }

    public Graphe getGraphe() {
        return this.graphe;
    }

    public Metro addMetro(int idLigne) {

        Metro m = null;

        if (idLigne <= this.graphe.getLignes().size()) {
            Ligne l = this.graphe.getLignes().get(idLigne - 1);

            Station stationDepart = l.getListStation().get(0);

            m = new Metro(stationDepart.getX(), stationDepart.getY());

            l.addMetro(m);

        } else {
            System.out.println("Cette ligne n'existe pas: impossible de créer le métro");
        }

        return m;
    }

    public void addStation(int x, int y, int idLigne, int distance) {

        Station S = this.getGraphe().chercheStationDansList(x, y);

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

    public int gettempsArret() {
        return this.tempsArret;
    }

    public void settempsArret(int b) {
        this.tempsArret = b;
    }
}

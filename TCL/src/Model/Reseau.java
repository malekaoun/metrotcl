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

                        m.avancer(this.vitesseMetro / distanceToNextStation);


                        if (m.estAUneStation(s, 15)) {

                            int y = 0;
                            while ( y < m.getListPassager().size()) {
                                Usager userDescend = m.getListPassager().get(y);

                                if (userDescend.getDestination() == this.graphe.GetIdOfStation(s)) {
                                    userDescend.usagerDescendDuMetro(m, s);
                                    System.out.println("descend");
                                } else {
                                    /*if (userDescend.getCorrespondances().size()>0){
                                        System.out.println("size cor:"+userDescend.getCorrespondances().size()) ;
                                        for (int r=0; r<userDescend.getCorrespondances().size(); r++){*/
                                            //System.out.print("id station corr: "+this.graphe.GetIdOfStation(userDescend.getCorrespondances().get(r).getStation()));
                                            //System.out.println(", id station actu: "+this.graphe.GetIdOfStation(s));
                                            if(userDescend.getCorrespondances().get(0).getStation().equals(s)){
                                                System.out.println("dans if: "+this.graphe.GetIdOfStation(s));
                                                userDescend.usagerDescendDuMetro(m, s);
                                                System.out.println(userDescend.getMetro());
                                                //userDescend.getCorrespondances().remove(r);
                                                //break;
                                           // }

                                       // }
                                       // y++;
                                    }else {
                                        y++;
                                    }
                                }
                            }

                            int k = 0;

                            if (!s.getListUsager().isEmpty()) {

                            while (m.getNbPlaceRestante() > 0 && s.getListUsager().size() > k) {
                                Usager userMonte = s.getListUsager().get(k);
                                if (userMonte.getDestination() != this.graphe.GetIdOfStation(s)) {
                                    if(userMonte.getCorrespondances().size()>0){
                                        // co corr
                                        for (int z=0; z<userMonte.getCorrespondances().size(); z++){
                                            if(this.graphe.getIdLigneFrStation(userMonte.getCorrespondances().get(z).getStation()).contains(this.graphe.getIdLigneFrMetro(m))){
                                                userMonte.usagerMonteDansMetro(m, s);
                                                break;
                                            }
                                        }
                                    } else {
                                        //ko co corr
                                        if(this.graphe.getIdLigneFrIdStation(userMonte.getDestination()).contains(this.graphe.getIdLigneFrMetro(m))){
                                            userMonte.usagerMonteDansMetro(m, s);
                                        }
                                    }
                                    
                                } else {
                                    k++;

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
        } else {
            S.setCorrespondante(true);
        }

        this.graphe.addSommet(S);

        if (idLigne > this.graphe.getLignes().size()) {
            Ligne l = new Ligne();
            l.addStationToLigne(S);
            this.graphe.addLigne(l);
        } else {

            ArrayList<Station> listStation = this.graphe.getLignes().get(idLigne - 1).getListStation();

            Distance d = new Distance(distance);

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

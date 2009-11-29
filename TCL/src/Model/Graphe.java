package Model;

import java.util.ArrayList;

public class Graphe {

    private ArrayList<Station> sommets;
    private ArrayList<Ligne> lignes;

    public Graphe() {
        sommets = new ArrayList<Station>();
        lignes = new ArrayList<Ligne>();
    }

    public Station chercheStationDansList(int x, int y) {

        for (int i = 0; i < sommets.size(); i++) {
            if (sommets.get(i).getX() == x && sommets.get(i).getY() == y) {
                return sommets.get(i);
            }
        }

        return null;
    }

    public int GetIdOfStation(Station s) {

        for (int i = 0; i < sommets.size(); i++) {
            if (sommets.get(i).getX() == s.getX() && sommets.get(i).getY() == s.getY()) {
                return i;
            }
        }

        return -1;
    }

    public int getIdLigneFrMetro(Metro m) {
        for (int i = 0; i < lignes.size(); i++) {
            if (lignes.get(i).getMetros().contains(m)) {
                return i;
            }
        }
        return -1;
    }

    public Station getStationFrId(int id) {
        if (id < sommets.size()) {
            return sommets.get(id);
        } else {
            return null;
        }
    }

    public ArrayList getIdLigneFrIdStation(int id) {
        Station s = getStationFrId(id);
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (s != null) {
            res = getIdLigneFrStation(s);
        }
        return res;
    }

    public ArrayList getIdLigneFrStation(Station s) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < lignes.size(); i++) {
            if (lignes.get(i).getListStation().contains(s)) {
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

    public int[] getCorres(int depart, int arrivee) {

        ArrayList<Integer> listLignesDep = getIdLigneFrIdStation(depart);
        ArrayList<Integer> listLignesArr = getIdLigneFrIdStation(arrivee);
        for (int i = 0; i < listLignesArr.size(); i++) {
            Ligne ligne = lignes.get(listLignesArr.get(i));
            for (int j = 0; j < ligne.getListStation().size(); j++) {
                if (ligne.getListStation().get(j).isCorrespondante()) {
                    Station tmp = ligne.getListStation().get(j);
                    ArrayList<Integer> listLignes = getIdLigneFrStation(tmp);
                    for (int k = 0; k < listLignes.size(); k++) {
                        if (listLignesDep.contains(listLignes.get(k))) {
                            int[] res = {j, listLignes.get(k), listLignesArr.get(i)};
                            System.out.println("j"+j+"k"+ listLignes.get(k)+"arr"+ listLignesArr.get(i));
                            return res;
                        }
                    }
                }
            }
        }
        return null;
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

    public void CalculTrajet(Usager u) {

        Station stationCourante = this.getStationFrId(u.getDepart());

        Station arrivee = this.getStationFrId(u.getDestination());

        ArrayList<Integer> l = this.getIdLigneFrStation(stationCourante);

        ArrayList<Integer> Larrivee = this.getIdLigneFrStation(arrivee);

        ArrayList<Integer> ltrajet = new ArrayList<Integer>();

        for (int i = 0; i < l.size(); i++) {
            for (int j = 0; j < Larrivee.size(); j++) {

                if (l.get(i) == Larrivee.get(j)) {
                    ltrajet.add(l.get(i));
                }
            }

        }

        if (ltrajet.size() > 0) {


            int ligne;

            int distmin = this.getLignes().get(ltrajet.get(0)).getDistance(stationCourante, arrivee);
            ligne = ltrajet.get(0);

            for (int k = 1; k < ltrajet.size(); k++) {

                int dist = this.getLignes().get(ltrajet.get(k)).getDistance(stationCourante, arrivee);

                if (distmin > dist) {
                    distmin = dist;
                    ligne = ltrajet.get(k);
                }
            }

            int indiceCourante = this.getLignes().get(ligne).indiceStation(stationCourante);
            int indiceArrivee = this.getLignes().get(ligne).indiceStation(arrivee);

            Correspondance c = new Correspondance((indiceCourante > indiceArrivee), ligne,u.getDestination());

            u.getTrajet().add(c);

        } else {

            int[] res = this.getCorres(u.getDepart(), u.getDestination());

            if (res != null) {

                int indiceCourante = this.getLignes().get(res[1]).indiceStation(stationCourante);

                Station corres = this.getLignes().get(res[2]).getStationById(res[0]);

                int indiceCorres = this.getLignes().get(res[1]).indiceStation(corres);

                int indiceCorresdansGraphe=this.GetIdOfStation(corres);

                Correspondance c = new Correspondance((indiceCourante > indiceCorres), res[1], indiceCorresdansGraphe);

                u.getTrajet().add(c);

                int indiceArrivee = this.getLignes().get(res[2]).indiceStation(arrivee);

                Correspondance c2 = new Correspondance((res[0] > indiceArrivee), res[2], u.getDestination());

                u.getTrajet().add(c2);
            }

        }

    }
}

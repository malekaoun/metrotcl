package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Cette classe hérite de la classe Thread. Elle définit un réseau qui contient un Graphe,
 * un temps d'arrêt, une vitesse des metros et un booléen pour commencer ou arrêter le réseau.
 * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
 */
public class Reseau extends Thread {

    private Graphe graphe;
    private boolean stop;
    private int tempsArret = 20;
    private int vitesseMetro = 500;

    /**
     * Constructeur d'un réseau.
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public Reseau() {
        this.graphe = new Graphe();
        this.stop = false;
    }

    /**
     * Lance le thread, démarrre le réseau.
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
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
                            while (y < m.getListPassager().size()) {
                                Usager userDescend = m.getListPassager().get(y);
                                int indicetrajetDescend = userDescend.getIndiceTrajet();
                                if (userDescend.getDestination() == this.graphe.GetIdOfStation(s)) {
                                    userDescend.usagerDescendDuMetro(m, s);
                                } else {
                                    if (userDescend.getTrajet().get(indicetrajetDescend).getIndiceStationCorres() == this.graphe.GetIdOfStation(s)) {
                                        userDescend.usagerDescendDuMetro(m, s);
                                        if (indicetrajetDescend < userDescend.getTrajet().size() - 1) {
                                            userDescend.setIndiceTrajet(indicetrajetDescend + 1);
                                        }
                                    } else {
                                        y++;
                                    }
                                }
                            }

                            int k = 0;
                            if (!s.getListUsager().isEmpty()) {
                                while (m.getNbPlaceRestante() > 0 && s.getListUsager().size() > k) {
                                    Usager userMonte = s.getListUsager().get(k);
                                    if (userMonte.getDestination() != this.graphe.GetIdOfStation(s)) {
                                        if (userMonte.getTrajet().size() > 0) {
                                            boolean monte;
                                            int indicetrajetMonte = userMonte.getIndiceTrajet();
                                            if (l.indiceStation(s) > 0 && l.indiceStation(s) < l.getListStation().size() - 1) {
                                                monte = this.graphe.getIdLigneFrMetro(m) == userMonte.getTrajet().get(indicetrajetMonte).getLigne() && m.getSensInverse() == userMonte.getTrajet().get(indicetrajetMonte).isSensInverse();
                                            } else {
                                                monte = this.graphe.getIdLigneFrMetro(m) == userMonte.getTrajet().get(indicetrajetMonte).getLigne();
                                            }
                                            if (monte) {
                                                userMonte.usagerMonteDansMetro(m, s);
                                            } else {
                                                k++;
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

    /**
     * Renvoie le graphe du réseau.
     * @return Graphe
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public Graphe getGraphe() {
        return this.graphe;
    }

    /**
     * Ajoute un metro dans une ligne du réseau.
     * @param idLigne Entier
     * @return Metro
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
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

    /**
     * Ajoute une station avec leurs coordonnées géométriques, un nom, une distance
     * et la ligne qui appartient.
     * @param x Entier
     * @param y Entier
     * @param idLigne Entier de la ligne
     * @param distance Entier
     * @param nom String
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void addStation(int x, int y, int idLigne, int distance, String nom) {
        Station S = this.getGraphe().chercheStationDansList(x, y);
        if (S == null) {
            if (nom == null) {
                S = new Station(x, y);
            } else {
                S = new Station(x, y, nom);
            }
        } else {
            S.setCorrespondante(true);
        }

        this.graphe.addSommet(S);

        if (idLigne > this.graphe.getLignes().size()) {
            Ligne l = new Ligne();
            l.addStationToLigne(S);
            this.graphe.addLigne(l);
        } else {
            Ligne ligne = this.graphe.getLignes().get(idLigne - 1);
            Distance d = new Distance(distance);
            ligne.addArete(d);
            ligne.addStationToLigne(S);
        }
    }

    /**
     * Renvoie le temps d'arrêt.
     * @return Entier
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public int gettempsArret() {
        return this.tempsArret;
    }

    /**
     * Met un temps d'arrêt.
     * @param b Entier
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void settempsArret(int b) {
        this.tempsArret = b;
    }

    /**
     * Ajoute des stations à partir d'un fichier texte.
     * @param cheminfichier String
     * @param avecNom Booléen
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
     */
    public void AjoutStationParLectureFichier(String cheminfichier, boolean avecNom) throws FileNotFoundException, IOException {
        try {
            //lecture du fichier texte
            File f = new File(cheminfichier);
            InputStream ips = new FileInputStream(f);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);

            char[] charXstation = new char[3];
            char[] charYstation = new char[3];
            char[] charlignestation = new char[1];
            char[] chardistance = new char[2];
            char[] charnom = new char[13];

            //Tant qu'il reste des lignes à lire
            while (br.read(charXstation) > 0) {

                //On saute les lignes de commentaires
                if (charXstation[0] == '/') {

                    br.readLine();

                } else {
                    // lecture du premier entier de la ligne puis conversion char[]->String->int
                    br.skip(1);
                    String strXstation = String.valueOf(charXstation);
                    int Xstation = Integer.parseInt(strXstation);

                    // lecture du deuxieme entier de la ligne puis conversion char[]->String->int
                    br.read(charYstation);
                    br.skip(1);
                    String strYstation = String.valueOf(charYstation);
                    int Ystation = Integer.parseInt(strYstation);

                    // lecture du troisieme entier de la ligne puis conversion char[]->String->int
                    br.read(charlignestation);
                    br.skip(1);
                    String strlignestation = String.valueOf(charlignestation);
                    int lignestation = Integer.parseInt(strlignestation);

                    // lecture du quatrieme entier de la ligne puis conversion char[]->String->int
                    br.read(chardistance);
                    br.skip(1);
                    String strdistance = String.valueOf(chardistance);
                    int distance = Integer.parseInt(strdistance);

                    if(avecNom){
                    // lecture du nom de la station char[]->String
                    br.read(charnom);
                    br.skip(2);
                    String strnom = String.valueOf(charnom);

                    // ajout de la station à partir des 4 entiers lus et du nom
                    this.addStation(Xstation, Ystation, lignestation, distance,strnom);

                    }
                    else
                    {
                        br.skip(1);

                        // ajout de la station à partir des 4 entiers lus
                        this.addStation(Xstation, Ystation, lignestation, distance, null);

                    }
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}

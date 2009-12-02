package Model;

import java.util.ArrayList;

/**Modèle : classe Graphe
 * Classe contenant une liste sommets qui sont les stations à afficher et la liste des lignes du graphe
 * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Dac Cong Tai NGUYEN
 */
public class Graphe {

    private ArrayList<Station> sommets;
    private ArrayList<Ligne> lignes;

    /**Constructeur de la classe Graphe
     *
     */
    public Graphe() {
        sommets = new ArrayList<Station>();
        lignes = new ArrayList<Ligne>();
    }

    /**Methode qui renvoie la réference de la station de coordonnee x et y si elle se trouve dans la liste des stations du graphe, null sinon
     *
     * @param x entier coordonnée x de la station recherchée
     * @param y entier coordonnée y de la station recherchée
     * @return Station la réference de la station recherché, null si elle n'est pas dans la liste
     */
    public Station chercheStationDansList(int x, int y) {

        for (int i = 0; i < sommets.size(); i++) {
            if (sommets.get(i).getX() == x && sommets.get(i).getY() == y) {
                return sommets.get(i);
            }
        }

        return null;
    }

    /**Methode qui renvoie l'indice de la station en parametre dans la liste sommets des stations du graphe
     *
     * @param s Station reference de la Station cherchée
     * @return entier l'indice de la station dans la liste, -1 si elle ne s'y trouve pas
     */
    public int GetIdOfStation(Station s) {

        for (int i = 0; i < sommets.size(); i++) {
            if (sommets.get(i).getX() == s.getX() && sommets.get(i).getY() == s.getY()) {
                return i;
            }
        }

        return -1;
    }

    /**Methode qui renvoie l'indice de la ligne à laquelle correspond le metro passé en parametre
     *
     * @param m Metro reference du metro
     * @return entier l'indice de la ligne, -1 si il ne se trouve sur aucune ligne
     */
    public int getIdLigneFrMetro(Metro m) {
        for (int i = 0; i < lignes.size(); i++) {
            if (lignes.get(i).getMetros().contains(m)) {
                return i;
            }
        }
        return -1;
    }

    /** Getter de la référence de la station dans sommets la liste des stations du graphe, avec en parametre l'indice dans la liste
     *
     * @param id entier indice de la station
     * @return Station reference de la station, null si elle ne s'y trouve pas
     */
    public Station getStationFrId(int id) {
        if (id < sommets.size()) {
            return sommets.get(id);
        } else {
            return null;
        }
    }

/**Renvoie un ArrayList contenant les indices des lignes dans la list de lignes du graphe où est presente la station d'indice id dans sommets en parametre
 *
 * @param id entier indice de la station dans la liste sommets du graphe
 * @return ArrayList listes des indices des lignes ou la stion est présente, liste vide si elle n'est pas dans une des lignes
 */
    public ArrayList getIdLigneFrIdStation(int id) {
        Station s = getStationFrId(id);
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (s != null) {
            res = getIdLigneFrStation(s);
        }
        return res;
    }

    /**Renvoie un ArrayList contenant les indices des lignes dans la list de lignes du graphe où est presente la station en parametre
     *
     * @param s reference de la station à chercher
     * @return ArrayList liste des indices des lignes où elle est présente, list vide si elle n'est dans aucune ligne
     */
    public ArrayList getIdLigneFrStation(Station s) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < lignes.size(); i++) {
            if (lignes.get(i).getListStation().contains(s)) {
                res.add(i);
            }
        }
        return res;
    }

    /**Ajoute un sommet à la liste sommets du graphe, message d'avertissement si elle est déjà présente
     *
     * @param s réference de la station à ajouter
     */
    public void addSommet(Station s) {
        if (!sommets.contains(s)) {
            sommets.add(s);
        } else {
            System.out.println("addSommet() : sommet existant");
        }
    }

    /**Cherche la correspondance à prendre pour aller de la station d'indice depart à la station d'indice arrivee
     *
     * @param depart entier indice d'une station dans la liste sommets du graphe, station de départ
     * @param arrivee entier indice d'une station dans la liste sommets du graphe, station d'arrivée
     * @return tableau d'entier le premier correspond à l'indice de la station de correspondance, le deuxieme correspond à l'indice de la premiere ligne à prendre,  le troisieme à l'indice de la deuxieme ligne à prendre pour aller à la station d'arrivee
     */
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
                            return res;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**Setter permettant d'enlever la station de réference s de la liste des stations du graphe
     *
     * @param s Station reference de la station à enlever
     */
    public void rmSommet(Station s) {
        sommets.remove(s);
    }

    /**Getter de la liste des stations du graphe
     *
     * @return ArrayList de Station la liste sommets du graphe
     */
    public ArrayList<Station> getSommets() {
        return sommets;
    }

    /**Setter permettant d'initialiser la liste de stations dans le graphe via un ArrayList de Station
     *
     * @param sommets ArrayList de Station
     */
    public void setSommets(ArrayList<Station> sommets) {
        this.sommets = sommets;
    }

    /**Setter permettant d'ajouter une ligne en parametre à la liste de ligne du graphe
     *
     * @param l Ligne à ajouter
     */
    public void addLigne(Ligne l) {
        this.lignes.add(l);
    }

    /**Getter renvoie la liste des lignes du graphe
     *
     * @return ArrayList de Lignes la list de lignes du graphe
     */
    public ArrayList<Ligne> getLignes() {
        return lignes;
    }


    /**Calcul le trajet d'un usager en fonction de son départ et son arrivee, mets à jour sa list de Correspondance pour qu'il puisse prendre le metro sur la bonne ligne et changer de metro à la bonne station
     *
     * @param u usager dont on calcule le trajet
     */
    public void CalculTrajet(Usager u) {

        Station stationCourante = this.getStationFrId(u.getDepart());

        Station arrivee = this.getStationFrId(u.getDestination());

        ArrayList<Integer> l = this.getIdLigneFrStation(stationCourante);

        ArrayList<Integer> Larrivee = this.getIdLigneFrStation(arrivee);

        ArrayList<Integer> ltrajet = new ArrayList<Integer>();

        // On cherche si l'usager peut aller à sa destination en prenant un seul metro
        for (int i = 0; i < l.size(); i++) {
            for (int j = 0; j < Larrivee.size(); j++) {

                if (l.get(i) == Larrivee.get(j)) {
                    ltrajet.add(l.get(i));
                }
            }

        }

        // si ltrajet superieur à 0 il y a une ligne qui permet à l'usager d'aller directement de sa station de départ à sa destination
        if (ltrajet.size() > 0) {


            int ligne;

            int distmin = this.getLignes().get(ltrajet.get(0)).getDistance(stationCourante, arrivee);
            ligne = ltrajet.get(0);

            // si il y a plusieurs lignes directes on cherche la plus courte
            for (int k = 1; k < ltrajet.size(); k++) {

                int dist = this.getLignes().get(ltrajet.get(k)).getDistance(stationCourante, arrivee);

                if (distmin > dist) {
                    distmin = dist;
                    ligne = ltrajet.get(k);
                }
            }

            int indiceCourante = this.getLignes().get(ligne).indiceStation(stationCourante);
            int indiceArrivee = this.getLignes().get(ligne).indiceStation(arrivee);

            Correspondance c = new Correspondance((indiceCourante > indiceArrivee), ligne, u.getDestination());

            //on ajoute alors le trajet de la ligne directe à la liste trajet de l'usager,
            u.getTrajet().add(c);

        } else {

            // si il n'y a pas de trajet direct on calcule sa correspondance en appelant la methode getCorres()
            int[] res = this.getCorres(u.getDepart(), u.getDestination());

            if (res != null) {

                int indiceCourante = this.getLignes().get(res[1]).indiceStation(stationCourante);

                Station corres = this.getLignes().get(res[2]).getStationById(res[0]);

                int indiceCorres = this.getLignes().get(res[1]).indiceStation(corres);

                int indiceCorresdansGraphe = this.GetIdOfStation(corres);

                Correspondance c = new Correspondance((indiceCourante > indiceCorres), res[1], indiceCorresdansGraphe);

                // on ajoute les caracteristiques du premier trajet de l'usager avant d'arriver à la station de correspondance
                u.getTrajet().add(c);

                int indiceArrivee = this.getLignes().get(res[2]).indiceStation(arrivee);

                Correspondance c2 = new Correspondance((res[0] > indiceArrivee), res[2], u.getDestination());

                // on ajoute les caracteristiques de deuxieme trajet de l'usager pour arriver à sa destination
                u.getTrajet().add(c2);
            }

            // Si il n'existe pas de trajet entre la station de départ et la station de destination direct ou en une seule correspondance,
            //cette methode ne fait rien. Nous n'avons pas  géré les correspondances multiples par manque de temps
        }

    }
}

package Model;

import java.util.ArrayList;

public class Ligne {

    private ArrayList<Station> listStation = new ArrayList<Station>();


    public Ligne() {
    }

    public Ligne(ArrayList<Station> ls) {

        this.listStation = ls;
    }

    public void addStationToLigne(Station s) {
        this.listStation.add(s);
    }

    public void removeStationToLigne(Station s) {
        this.listStation.remove(s);
    }



    public int indiceStation(Station s) {
        int result = -1;
        if (listStation.contains(s)) {
            for (int i = 0; i < listStation.size(); i++) {
                if (listStation.get(i).equals(s)) {
                    return i;
                }
            }
        }
        return result;
    }

    public Station nextStation(Station station_courante) {
        return listStation.get(indiceStation(station_courante)+1);
    }

    //GETTERS and SETTERS


    public ArrayList<Station> getListStation() {
        return listStation;
    }

    public void setListStation(ArrayList<Station> listStation) {
        this.listStation = listStation;
    }
}

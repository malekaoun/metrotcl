package Model;

public class Correspondance {
    private Station station;
    private Ligne ligne;

    public Correspondance(Station station, Ligne ligne){
        this.station=station;
        this.ligne=ligne;
    }

    //GETTERS and SETTERS
    public Ligne getLigne() {
        return ligne;
    }

    public void setLigne(Ligne ligne) {
        this.ligne = ligne;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}

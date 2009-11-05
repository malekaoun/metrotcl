package Model;

public class Distance {
    private Station station1;
    private Station station2;
    private int distance;
    private int defaultDist = 1;

    public Distance(Station s1, Station s2, int d) {
        this.station1 = s1;
        this.station2 = s2;
        this.distance = d;
    }

    public Distance(Station s1, Station s2) {
        this.station1 = s1;
        this.station2 = s2;
        this.distance = defaultDist;
    }

    public int getDistance() { return this.distance; }
    public void setDistance(int d) { this.distance = d; }
    public Station getS1() { return this.station1; }
    public Station getS2() { return this.station2; }

}

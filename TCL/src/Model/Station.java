/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.ArrayList;

/**
 *
 * @author p0505657
 */
public class Station {
    private int x;
    private int y;
    private ArrayList<Usager> listUsager = new ArrayList<Usager>();
    private boolean prisMetro;
    private Metro metro;


    public Station(int x, int y){
        this.x=x;
        this.y=y;
        this.prisMetro=false;

    }

    public Station(int x, int y, ArrayList<Usager> list){
        this.x=x;
        this.y=y;
        this.listUsager=list;
        this.prisMetro=false;

    }

    public void addUsagerToStation(Usager u){
        this.listUsager.add(u);
    }

    public void removeUsagerFrStation(Usager u){
        this.listUsager.remove(u);
    }

    //GETTERS and SETTERS
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ArrayList<Usager> getListUsager() {
        return listUsager;
    }

    public void setListUsager(ArrayList<Usager> listUsager) {
        this.listUsager = listUsager;
    }

    public Metro getMetro() {
        return metro;
    }

    public void setMetro(Metro metro) {
        this.metro = metro;
    }

    public boolean isPrisMetro() {
        return prisMetro;
    }

    public void setPrisMetro(boolean prisMetro) {
        this.prisMetro = prisMetro;
    }


}

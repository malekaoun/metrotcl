/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controler;

import Model.*;
import Vue.Feuille;
import java.util.ArrayList;

/**
 *
 * @author p0505657
 */
public class Moteur extends Thread{

    private Feuille feuille;
    private Graphe graphe;
    private ArrayList<Metro> metros = new ArrayList<Metro>();

    public Moteur(Graphe graphe, Feuille feuille, ArrayList<Metro> m) {
        this.feuille = feuille;
        this.graphe=graphe;
        this.metros=m;
    }

    @Override
    public void run(){
        while(true){
            for(int i=0; i<metros.size(); i++){
                metros.get(i).avancer(i);
            }
        }
    }

    private int targetDir(Station s, int x,int y){
            int d=0;
            if (s.getX()>x){
                if (s.getY()>y){
                    double q = (double)((double)s.getX()-x)/((double)s.getY()-y);
                    d = (int) (((Math.atan(1/q))/Math.PI)*180+180);
                }
                else {
                    double q = (double)((double)s.getX()-x)/((double)y-s.getY());
                    d = (int) (((Math.atan(q))/Math.PI)*180+90);
                }
            }
            else {
                if (s.getY()>y){
                    double q = (double)((double)s.getX()-x)/((double)s.getY()-y);
                    d = (int) (((Math.atan(1/q))/Math.PI)*180);
                }
                else {
                    double q = (double)((double)s.getX()-x)/((double)y-s.getY());
                    d = (int) (((Math.atan(q))/Math.PI)*180+90);
                }
            }
            return d;
        }

}

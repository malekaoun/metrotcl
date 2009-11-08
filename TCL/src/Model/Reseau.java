package Model;

import Vue.Feuille;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reseau extends Thread{
    private Feuille f;
    private Graphe graphe;
    private ArrayList<Metro> metros;
    private ArrayList<Ligne> lignes;
    //private int j=1;

    public Reseau(Graphe g) {
        //this.f=f;
        this.graphe = g;
        this.metros = g.getMetros();
        this.lignes = new ArrayList<Ligne>();
    }

    @Override
    public void run() {
        int j=1;
        while (true) {
            for (int i = 0; i < metros.size(); i++) {
                Metro m = metros.get(i);
                    if(m.getX()<m.getLigne().getListStation().get(j).getX()
                        || m.getY()<m.getLigne().getListStation().get(j).getY()){
                        int dir=targetDir(m, m.getLigne().getListStation().get(j).getX(), m.getLigne().getListStation().get(j).getY());
                        m.avancer(1, dir);
                    }else {
                        try {
                            sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Reseau.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        j++;
                    }
                    
            f.repaint();
            try {
                Thread.sleep(10);
            } catch(Exception e){
                System.err.println("erreur: "+e);
            }
            }
        }
        
    }

    public Graphe getGraphe() {
        return this.graphe;
    }

    public void addMetro(Metro m) {

       //metros.add(m);
    }

    
    private int targetDir(Metro s, int x,int y){
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

    public Feuille getF() {
        return f;
    }

    public void setF(Feuille f) {
        this.f = f;
    }
}

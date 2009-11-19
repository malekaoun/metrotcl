/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controller.*;
import Model.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @author p0401116
 */
public class Interface extends ReseauView implements Observer {

    private Feuille feuille;
    private JFrame frame;
    public static final int w = 800;
    public static final int h = 750;

    public Interface(ReseauController c) {

        super(c);
        frame = new JFrame("TCL");

        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("logo.jpg"));
        initInterface();

        // System.out.println("dans constructeur"+graphe);
    }

    private void initInterface() {
        frame.getContentPane().setLayout(new BorderLayout(10, 10));
        feuille = new Feuille(this.getController().getModel().getGraphe());
        feuille.setPreferredSize(new Dimension(w, h));
      //  System.out.println(feuille);
       // System.out.println(feuille.getBackground());

        frame.getContentPane().add(feuille, "Center");

        //Initialise les menus
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);	// on installe le menu bar

        JMenu menuFile = new JMenu("File"); // on installe le premier menu
        menubar.add(menuFile);

        addMenuItem(menuFile, "Quitter", "Quitter", KeyEvent.VK_Q);

        for (int i = 0; i < this.getController().getModel().getGraphe().getLignes().size(); i++) {

            Ligne l = this.getController().getModel().getGraphe().getLignes().get(i);

            for (int j = 0; j < l.getMetros().size(); j++) {
                l.getMetros().get(j).addObserver(this);
            }
        }


        frame.setLocation(258, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

    }

    private void addMenuItem(JMenu m, String label, String command, int key) {
        JMenuItem menuItem = new JMenuItem(label);
        m.add(menuItem);

        menuItem.setActionCommand(command);
        menuItem.addActionListener(this.getController());
        if (key > 0) {
            if (key != KeyEvent.VK_DELETE) {
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, Event.CTRL_MASK, false));
            } else {
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, 0, false));
            }
        }
    }

    public Feuille getFeuille() {
        return feuille;
    }

    @Override
    public void display() {
        frame.setVisible(true);
    }

    @Override
    public void close() {
        frame.dispose();
    }

    public void affiche() {
        feuille.dessineGraphe();
    }

    public void update(Observable o, Object arg1) {

        affiche();

        for (int i = 0; i < this.getController().getModel().getGraphe().getLignes().size(); i++) {

            Ligne l = this.getController().getModel().getGraphe().getLignes().get(i);

            for (int j = 0; j < l.getMetros().size(); j++) {

                Metro m = l.getMetros().get(j);

                if (m == o) {
                    feuille.eraseMetro(m, j + 1);
                    feuille.dessineMetro(m, j + 1);
                    feuille.drawIt();
                }
            }

        }
    }
}

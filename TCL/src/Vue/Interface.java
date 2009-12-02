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
 * Fenêtre d'affichage principale (Vue).
 * Contient une JFrame, qui elle même content une Feuille (pkg Vue).
 * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Tai NGUYEN DAC CONG
 */
public class Interface extends ReseauView implements Observer {

    private Feuille feuille;
    private JFrame frame;
    public static final int w = 1000;
    public static final int h = 850;

    /**
     * Constructeur de l'interface.
     * @param c Controleur
     */
    public Interface(ReseauController c) {
        super(c);
        frame = new JFrame("TCL");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("logo.jpg"));
        initInterface();
    }

    /**
     * Initialisation de l'interface (appellée par le constructeur).
     */
    private void initInterface() {
        frame.getContentPane().setLayout(new BorderLayout(10, 10));
        feuille = new Feuille(this.getController().getModel().getGraphe());
        feuille.setPreferredSize(new Dimension(w, h));

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

    /**
     * Ajoute un élément du menu.
     * @param m JMenu
     * @param label String
     * @param command String
     * @param key int
     */
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

    /**
     * Renvoie la Feuille.
     * @return Feuille
     */
    public Feuille getFeuille() {
        return feuille;
    }

    /**
     * Surcharge de la méthode display() du controleur.
     */
    @Override
    public void display() {
        frame.setVisible(true);
    }

    /**
     * Surcharge de la méthode close() du controleur.
     */
    @Override
    public void close() {
        frame.dispose();
    }

    /**
     * Dessin initial du graphe.
     */
    public void affiche() {
        feuille.dessineGraphe();
    }

    /**
     * Observe les changements d'états des Metro et les met à jour dans l'interface.
     * @param o Observable
     * @param arg1 Object
     */
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

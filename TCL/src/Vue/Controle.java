package Vue;

import Controller.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * Panneau des actions de utilisateur (Vue).
 * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Tai NGUYEN DAC CONG
 */
public class Controle extends ReseauView {

    private final JFrame frame;
    public static final Dimension VGAP = new Dimension(1, 5);
    public static final Dimension HGAP = new Dimension(2, 1);
    public JComboBox ListStationDepart;
    public JComboBox ListStationArrivee;
    public JComboBox ListLignes;
    public JComboBox ListLignesASupprimer;

    /**
     * Constructeur de la vue.
     * @param c Controleur du réseau
     */
    public Controle(ReseauController c) {
        super(c);
        frame = new JFrame("Controle");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("logo.jpg"));
        initControle();
    }

    /**
     * Procédure de création de la vue, appellée par le constructeur.
     */
    public void initControle() {
        frame.getContentPane().setLayout(new BorderLayout(10, 10));

        //Initialise les menus
        JPanel buttonPanel1 = new JPanel();
        JPanel StationPanel = new JPanel();
        JPanel MetroPanel = new JPanel();
        JPanel SupprimerStation = new JPanel();

        frame.getContentPane().add(buttonPanel1, BorderLayout.NORTH);
        frame.getContentPane().add(SupprimerStation, BorderLayout.CENTER);
        frame.getContentPane().add(StationPanel, BorderLayout.SOUTH);

        buttonPanel1.add(Box.createRigidArea(VGAP));
        buttonPanel1.setPreferredSize(new Dimension(250, 100));

        String[] Station = new String[controller.getModel().getGraphe().getSommets().size()];
        for (int i = 0; i < controller.getModel().getGraphe().getSommets().size(); i++) {
            Integer tmp = i + 1;
            Station[i] = tmp.toString();
        }

        String[] Lignes = new String[controller.getModel().getGraphe().getLignes().size()];
        for (int i = 0; i < controller.getModel().getGraphe().getLignes().size(); i++) {
            Integer tmp = i + 1;
            Lignes[i] = tmp.toString();
        }

        // Create the combo box
        JLabel stationDepartLabel = new JLabel(" Selectionner Station de depart: ");
        buttonPanel1.add(stationDepartLabel);

        ListStationDepart = new JComboBox(Station);
        buttonPanel1.add(ListStationDepart);
        JLabel stationArriveLabel = new JLabel(" Selectionner Station d'arrivée: ");
        buttonPanel1.add(stationArriveLabel);
        ListStationArrivee = new JComboBox(Station);
        buttonPanel1.add(ListStationArrivee);

        JButton AjouterPersonne = new JButton("Ajouter la personne");
        buttonPanel1.add(AjouterPersonne);
        AjouterPersonne.addActionListener(this.getController());

        MetroPanel.add(Box.createRigidArea(VGAP));
        JLabel AjoutMetroLabel = new JLabel("Ajouter un Metro sur la Ligne:");
        StationPanel.add(AjoutMetroLabel);
        ListLignes = new JComboBox(Lignes);
        StationPanel.add(ListLignes);

        JButton AjoutMetro = new JButton("Ajouter un Metro");
        StationPanel.add(AjoutMetro);
        StationPanel.setPreferredSize(new Dimension(200, 70));
        AjoutMetro.addActionListener(this.getController());

        SupprimerStation.add(Box.createRigidArea(VGAP));
        SupprimerStation.setPreferredSize(new Dimension(200, 70));
        JButton SupprimeStation = new JButton("Supprime Station");
        JLabel Supp = new JLabel("Simulation d'une panne : ");
        ListLignesASupprimer = new JComboBox(Station);
        SupprimeStation.addActionListener(this.getController());
        SupprimerStation.add(Supp);
        SupprimerStation.add(ListLignesASupprimer);
        SupprimerStation.add(SupprimeStation);

        // les menus
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);	// on installe le menu bar
        JMenu menuFile = new JMenu("File"); // on installe le premier menu
        menubar.add(menuFile);

        addMenuItem(menuFile, "Quitter", "Quitter", KeyEvent.VK_Q);

        JMenu menuHelp = new JMenu("Aide"); // on installe le premier menu
        menubar.add(menuHelp);
        addMenuItem(menuHelp, "Aide", "Help", -1);
        addMenuItem(menuHelp, "A propos", "About", -1);

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
     * Ajoute un bouton dans un JComponent.
     * @param p JComponent
     * @param name String
     * @param tooltiptext String
     * @param imageName String
     */
    void addButton(JComponent p, String name, String tooltiptext, String imageName) {
        JButton b;
        if ((imageName == null) || (imageName.equals(""))) {
            b = (JButton) p.add(new JButton(name));
        } else {
            java.net.URL u = this.getClass().getResource(imageName);
            if (u != null) {
                ImageIcon im = new ImageIcon(u);

                b = (JButton) p.add(new JButton(im));
            } else {
                b = (JButton) p.add(new JButton(name));
            }
            b.setActionCommand(name);
        }

        b.setToolTipText(tooltiptext);
        b.setBorder(BorderFactory.createRaisedBevelBorder());
        b.setMargin(new Insets(0, 0, 0, 0));
        b.addActionListener(this.getController());
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
     * Renvoie la liste des stations de départ.
     * @return JComboBox
     * @author Mattias GARCIA, Julien LANOISELEE, Romain JACQUET, Tai NGUYEN DAC CONG
     */
    public JComboBox getListStationDepart() {
        return this.ListStationDepart;
    }

    /**
     * Renvoie la liste des stations d'arrivée.
     * @return JComboBox
     */
    public JComboBox getListStationArrivee() {
        return this.ListStationArrivee;
    }

    /**
     * Renvoie la liste des lignes.
     * @return JComboBox
     */
    public JComboBox getListLignes() {
        return this.ListLignes;
    }

    /**
     * Renvoie la liste des lignes à supprimer (gestion des pannes).
     * @return JComboBox
     * @deprecated
     */
    public JComboBox getListLignesAsupprimer() {
        return this.ListLignesASupprimer;
    }
}
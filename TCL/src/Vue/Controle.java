/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vue;

import Controller.ReseauController;
import Controller.ReseauView;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 *
 * @author r
 */
public class Controle extends ReseauView implements ActionListener{

    private final JFrame frame;
    private JTextField inputValue;
    public static final Dimension VGAP = new Dimension(1,5);
    public static final Dimension HGAP = new Dimension(5,1);


    public Controle (ReseauController c) {

        super(c);
        frame = new JFrame("Controle");

        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("logo.jpg"));

        InitControle();
    }

   public void  InitControle(){
        frame.getContentPane().setLayout(new BorderLayout(10, 10));

        //Initialise les menus
               
                JPanel buttonPanel = new JPanel();
                JPanel StationPanel = new JPanel();
		

		frame.getContentPane().add(buttonPanel,"North");
                frame.getContentPane().add(StationPanel,"South");

		buttonPanel.setLayout(new GridLayout(0,2));
		String[] Station = {"1", "2", "3","4","5","6", "7", "8", "9","10", "11", "12"};

		// Create the combo box
		
		JLabel StationdepartLabel = new JLabel(" Selectionner Station de depart: ");
		buttonPanel.add(StationdepartLabel);
                JComboBox ListStationDepart = new JComboBox(Station);
		buttonPanel.add(ListStationDepart);


                JLabel StationarriveLabel = new JLabel(" Selectionner Station d'arrivée: ");
                buttonPanel.add(StationarriveLabel);
              
                JComboBox ListStationArrivee = new JComboBox(Station);
                buttonPanel.add(ListStationArrivee);

                JButton AjouterPersonne = new JButton("Ajouter la personne");
		buttonPanel.add(AjouterPersonne);
		AjouterPersonne.addActionListener(this);

                StationPanel.setLayout(new BorderLayout(10,10));
                JLabel SupprimerStation = new JLabel(" Supprimer une Station : ");
                JComboBox ListStation = new JComboBox(Station);
                StationPanel.add(SupprimerStation);
                StationPanel.add(ListStation);
                

		ListStationDepart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				int n = cb.getSelectedIndex();
                              //  courante.setColor(n);
				
			}
		});
                ListStationArrivee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				int n = cb.getSelectedIndex();
                              //  courante.setColor(n);

			}
		});




		// les menus
		JMenuBar menubar=new JMenuBar();
                frame.setJMenuBar(menubar);	// on installe le menu bar
		JMenu menuFile=new JMenu("File"); // on installe le premier menu
                menubar.add(menuFile);

               // addMenuItem(menuFile, "Effacer", "Effacer", KeyEvent.VK_N);
                addMenuItem(menuFile, "Quitter", "Quitter", KeyEvent.VK_Q);


		/* JMenu menuCommandes=new JMenu("Commandes"); // on installe le premier menu
                menubar.add(menuCommandes);
               addMenuItem(menuCommandes, "Avancer", "Avancer", -1);
                addMenuItem(menuCommandes, "Droite", "Droite", -1);
                addMenuItem(menuCommandes, "Gauche", "Gauche", -1);
                addMenuItem(menuCommandes, "Lever Crayon", "Lever", -1);
                addMenuItem(menuCommandes, "Baisser Crayon", "Baisser", -1);*/

		JMenu menuHelp=new JMenu("Aide"); // on installe le premier menu
                menubar.add(menuHelp);
                addMenuItem(menuHelp, "Aide", "Help", -1);
                addMenuItem(menuHelp, "A propos", "About", -1);


		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        	frame.pack();
  		display();
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
    void addButton(JComponent p, String name, String tooltiptext, String imageName) {
	  JButton b;
	  if ((imageName == null) || (imageName.equals(""))) {
              b = (JButton) p.add(new JButton(name));
	  }
	  else {
		   java.net.URL u = this.getClass().getResource(imageName);
	       if (u != null){
			  ImageIcon im = new ImageIcon (u);

			  b = (JButton) p.add(new JButton(im));
		   } else
				  b = (JButton) p.add(new JButton(name));
		   b.setActionCommand(name);
	  }

	  b.setToolTipText(tooltiptext);
	  b.setBorder(BorderFactory.createRaisedBevelBorder());
	  b.setMargin(new Insets(0,0,0,0));
	  b.addActionListener(this);
	}

    @Override
    public void display() {
        frame.setVisible(true);
    }

    @Override
    public void close() {
        frame.dispose();
    }

    public void actionPerformed(ActionEvent e) {
       String c = e.getActionCommand();

                // actions des boutons du haut
                if (c.equals("Avancer")) {
                                try {
                                  int v = Integer.parseInt(inputValue.getText());
                                  //courante.avancer(v);
                                } catch (NumberFormatException ex){
                                        System.err.println("ce n'est pas un nombre : " + inputValue.getText());
                                }
                } else if (c.equals("Droite"))  {
                                try {
                                  int v = Integer.parseInt(inputValue.getText());
                                 // courante.droite(v);
                                } catch (NumberFormatException ex){
                                        System.err.println("ce n'est pas un nombre : " + inputValue.getText());
                                }
                } else if (c.equals("Gauche"))  {
                                try {
                                  int v = Integer.parseInt(inputValue.getText());
                                //  courante.gauche(v);
                                } catch (NumberFormatException ex){
                                        System.err.println("ce n'est pas un nombre : " + inputValue.getText());
                                }
                } else if (c.equals("Lever")) ;// courante.leverCrayon();
                else if (c.equals("Baisser")) ;// courante.baisserCrayon();

                // actions des boutons du bas
                else if (c.equals("Proc1")) ; //proc1();
                else if (c.equals("Proc2")) ; //proc2();
                else if (c.equals("Proc3")) ; //proc3();
                else if (c.equals("Effacer")); //effacer();
                else if (c.equals("Quitter")); //quitter();
    }



}

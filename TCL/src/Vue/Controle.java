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
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.*;


public class Controle extends ReseauView {

    private final JFrame frame;
    private JTextField inputValue;
    public static final Dimension VGAP = new Dimension(3,1);
    public static final Dimension HGAP = new Dimension(2,1);
    public JComboBox ListStationDepart;
    public JComboBox ListStationArrivee;



    public Controle (ReseauController c) {

        super(c);
        frame = new JFrame("Controle");

        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("logo.jpg"));

        InitControle();
    }

   public void  InitControle(){
       
       
     
                 
                frame.getContentPane().setLayout(new BorderLayout(10,10));

        //Initialise les menus
               
                JPanel buttonPanel1 = new JPanel();
                JPanel buttonPanel2 = new JPanel();
                JPanel StationPanel = new JPanel();
		

		frame.getContentPane().add(buttonPanel1,BorderLayout.NORTH);
                frame.getContentPane().add(buttonPanel2,BorderLayout.CENTER);
                frame.getContentPane().add(StationPanel,BorderLayout.PAGE_END);

		
                buttonPanel1.add(Box.createRigidArea(VGAP));

               
		String[] Station = {"1", "2", "3","4","5","6", "7", "8", "9","10", "11", "12"};

		// Create the combo box
		
		JLabel StationdepartLabel = new JLabel(" Selectionner Station de depart: ");
		buttonPanel1.add(StationdepartLabel);
              
                ListStationDepart = new JComboBox(Station);              
		buttonPanel1.add(ListStationDepart);
                


                JLabel StationarriveLabel = new JLabel(" Selectionner Station d'arrivée: ");
                buttonPanel1.add(StationarriveLabel);
                ListStationArrivee = new JComboBox(Station);
                buttonPanel1.add(ListStationArrivee);

                JButton AjouterPersonne = new JButton("Ajouter la personne");
		buttonPanel2.add(AjouterPersonne);
		AjouterPersonne.addActionListener(this.getController());
                

                //StationPanel.setLayout(new BorderLayout(10,10));
                StationPanel.add(Box.createRigidArea(VGAP));
                JLabel SupprimerStation = new JLabel(" Supprimer une Station : ");
                StationPanel.add(SupprimerStation);
                JComboBox ListStation = new JComboBox(Station);
                StationPanel.add(ListStation);

                JButton SupprimeStation = new JButton("Supprimer Station");
		StationPanel.add(SupprimeStation);
		SupprimeStation.addActionListener(this.getController());




		// les menus
		JMenuBar menubar=new JMenuBar();
                frame.setJMenuBar(menubar);	// on installe le menu bar
		JMenu menuFile=new JMenu("File"); // on installe le premier menu
                menubar.add(menuFile);


                addMenuItem(menuFile, "Quitter", "Quitter", KeyEvent.VK_Q);


		JMenu menuHelp=new JMenu("Aide"); // on installe le premier menu
                menubar.add(menuHelp);
                addMenuItem(menuHelp, "Aide", "Help", -1);
                addMenuItem(menuHelp, "A propos", "About", -1);


		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
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
	  b.addActionListener(this.getController());
	}

    @Override
    public void display() {
        frame.setVisible(true);
    }

    @Override
    public void close() {
        frame.dispose();
    }


    public JComboBox getListStationDepart(){

        return this.ListStationDepart;
    }

    public JComboBox getListStationArrivee(){

        return this.ListStationArrivee;
    }


}

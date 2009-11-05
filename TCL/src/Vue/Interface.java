/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vue;

import Model.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author p0401116
 */
public class Interface extends JFrame implements ActionListener{
    private Feuille feuille;
    private Graphe graphe;

    public Interface(Graphe g){
        setTitle("TCL");
        setIconImage(Toolkit.getDefaultToolkit().getImage("logo.jpg"));
        this.graphe=g;
        initInterface();
        
       // System.out.println("dans constructeur"+graphe);
    }

    private void initInterface(){
        getContentPane().setLayout(new BorderLayout(10,10));
        feuille=new Feuille(graphe);
        feuille.setPreferredSize(new Dimension(1500,1500));
        System.out.println(feuille);
        System.out.println(feuille.getBackground());
        
        getContentPane().add(feuille,"Center");
       
        //Initialise les menus
        JMenuBar menubar=new JMenuBar();
        setJMenuBar(menubar);	// on installe le menu bar

        JMenu menuFile=new JMenu("File"); // on installe le premier menu
        menubar.add(menuFile);

        addMenuItem(menuFile, "Quitter", "Quitter", KeyEvent.VK_Q);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

   

    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();
        if(c.equals("Quitter")){
            quitter();
        }
    }

    private void quitter(){
        System.exit(0);
    }

    private void addMenuItem(JMenu m, String label, String command, int key) {
        JMenuItem menuItem = new JMenuItem(label);
        m.add(menuItem);

        menuItem.setActionCommand(command);
        menuItem.addActionListener(this);
		if (key > 0) {
			if (key != KeyEvent.VK_DELETE)
				menuItem.setAccelerator(KeyStroke.getKeyStroke(key, Event.CTRL_MASK, false));
			else
				menuItem.setAccelerator(KeyStroke.getKeyStroke(key, 0, false));
		}
	}

    public Feuille getFeuille() {
        return feuille;
    }
    
}

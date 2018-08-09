/**
 * Bar.java
 */
package gui;
import tool.Constant;
 
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Graphics;
import java.awt.Graphics2D;


/**
 * <b>GUI est une classe permettant de modeliser l'interface graphique.</b>
 * <p>
 * L'interface graphique est defini par :
 * <ul>
 * <li> L'element dans lequel est affiche la page HTML du gene.</li>
 * <li> L'element dans lequel est affiche les informations et les reactions associe au gene.</li>
 * <li> La barre associe au genome. Elle contient le bouton de recherche.</li>
 * <p> 
 * </ul>
 * 
 * @author Coralie Rohmer
 */
public class Bar extends JMenuBar{
	//------------------------------------------------------------------
	//				Variables d'instances
	//------------------------------------------------------------------
	private JMenu menuTrie = new JMenu("Trier par ...");
	private JMenu test1_2 = new JMenu("Auteur");
	private JMenu test2 = new JMenu("+ Ajouter un film");

	private JMenuItem item1 = new JMenuItem("Genre");
	private JMenuItem item2 = new JMenuItem("Fermer");
	private JMenuItem item3 = new JMenuItem("Manuellement");
	private JMenuItem item4 = new JMenuItem("Automatique");

	private JCheckBoxMenuItem jcmi1 = new JCheckBoxMenuItem("Choix 1");
	private JCheckBoxMenuItem jcmi2 = new JCheckBoxMenuItem("Choix 2");

	private JRadioButtonMenuItem jrmi1 = new JRadioButtonMenuItem("Radio 1");
	private JRadioButtonMenuItem jrmi2 = new JRadioButtonMenuItem("Radio 2");

	
	//------------------------------------------------------------------
	//				Constructeur
	//------------------------------------------------------------------
	/**
	 * Construction de l'interface
	 */
	public Bar(){
	super();
	this.setPreferredSize(new Dimension(100, 40));

    //On initialise nos menus      
    this.menuTrie.add(item1);
    //On ajoute les éléments dans notre sous-menu
    this.test1_2.add(jcmi1);
    this.test1_2.add(jcmi2);

    //Ajout d'un séparateur
    this.test1_2.addSeparator();

    //On met nos radios dans un ButtonGroup
    ButtonGroup bg = new ButtonGroup();
    bg.add(jrmi1);
    bg.add(jrmi1);
    //On présélectionne la première radio
    jrmi1.setSelected(true);

    this.test1_2.add(jrmi1);
    this.test1_2.add(jrmi2);

    //Ajout du sous-menu dans notre menu
    this.menuTrie.add(this.test1_2);

    //Ajout d'un séparateur
	this.menuTrie.addSeparator();
	item2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
		}        
	});

    this.menuTrie.add(item2);  
    this.test2.add(item3);
    this.test2.add(item4);

    //L'ordre d'ajout va déterminer l'ordre d'apparition dans le menu de gauche à droite
    //Le premier ajouté sera tout à gauche de la barre de menu et inversement pour le dernier

    this.add(menuTrie);
    this.add(test2);
	}
	
	//------------------------------------------------------------------
	//				Methodes
	//------------------------------------------------------------------
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Constant.GUI_COLOR);
        g2d.fillRect(0,0, getWidth() - 1, getHeight() - 1);

    }
}

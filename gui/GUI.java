/**
 * GUI.java
 */
package gui;

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
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;


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
public class GUI extends JFrame implements WindowListener{
	//------------------------------------------------------------------
	//				Variables d'instances
	//------------------------------------------------------------------

    /**
     * Permet la serialisation/deserialisation (sauvegarde de donnees).
     */
	private static final long serialVersionUID = 354054054054L;
	
	private Bar menuBar = new Bar();
	private Tab onglets = new Tab();

	
	//------------------------------------------------------------------
	//				Constructeur
	//------------------------------------------------------------------
	/**
	 * Construction de l'interface
	 */
	public GUI(){
	//Paramètre de la fenêtre
    this.setTitle("Le Chat Noir");
    this.setSize(1500,900);
    this.setLocationRelativeTo(null);
    this.setMinimumSize(new Dimension(1100, 600));
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    this.addWindowListener(this);
    
    //Change l'icône de la fenêtre
    Toolkit kit = Toolkit.getDefaultToolkit();
    Image img = kit.getImage("Resources/Logo.png");
    this.setIconImage(img);

    //~ //--------------------------------------------
	//Ajoute le menu
    this.setJMenuBar(menuBar);
	
	 
	//Add the tabbed pane to this panel.
	JPanel content = new JPanel();
    content.setLayout(new BorderLayout());
	content.add(onglets,BorderLayout.CENTER);
	this.setContentPane(content);
    this.setVisible(true);
	}
	
	//------------------------------------------------------------------
	//				Methodes
	//------------------------------------------------------------------
	/**
	* Redefinition de la methode windowsClosing de l'interface WindowListener
	* pour lancer la demande d'une sauvegarde de la session
	* 
	* @param evt
	* 
	* @see WindowListener#windowsClosing()
	* @see Tool
	*/
	public void windowClosing(WindowEvent evt) {
		ImageIcon img = new ImageIcon("Resources/quiz.png");
		int option = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter?", "Texte", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,img);
		if(option == JOptionPane.OK_OPTION){
			System.exit(0);
		}
		if(option == JOptionPane.NO_OPTION){
			System.exit(0);
		}
	}

	/**
	 * Utilise les fichiers de mise a jour pour lancer la mise a jour
	 */
	public void startBackup(){
	}
	
	//------------------------------------------------------------------
	//				Non Utilisées
	//------------------------------------------------------------------
	/**
	 * Methodes de l'interface WindowListener.
	 * Obligatoires mais non utilisees ici
	 */
	public void windowOpened(WindowEvent evt) {};
	public void windowClosed(WindowEvent evt) {};
	public void windowIconified(WindowEvent evt) {};
	public void windowDeiconified(WindowEvent evt) {};
	public void windowActivated(WindowEvent evt) {};
	public void windowDeactivated(WindowEvent evt) {};
}

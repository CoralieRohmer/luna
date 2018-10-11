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
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.TransferHandler;
import moviepage.MoviePage;


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
    this.setTitle("Luna");
    this.setSize(1500,900);
    this.setLocationRelativeTo(null);
    this.setMinimumSize(new Dimension(1100, 600));
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    this.addWindowListener(this);
    
    
    //Change l'icône de la fenêtre
    Toolkit kit = Toolkit.getDefaultToolkit();
    Image img = kit.getImage("../Ressources/Logo.jpg");
    this.setIconImage(img);

    //~ //--------------------------------------------
	//Ajoute le menu
    this.setJMenuBar(menuBar);
	
	 
	//Add the tabbed pane to this panel.
	JPanel content = new JPanel();
    content.setLayout(new BorderLayout());
	content.add(onglets,BorderLayout.CENTER);
	content.setTransferHandler(new MyFileTransferHandler());
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
		ImageIcon img = new ImageIcon("../Ressources/quiz.png");
		int option = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter?", "Fermeture", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,img);
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

/** Classe non publique pour gérer les gouttes de nom de fichier */

class MyFileTransferHandler extends TransferHandler {

  /**
   * @see javax.swing.TransferHandler#canImport(javax.swing.JComponent,
   *      java.awt.datatransfer.DataFlavor[])
   */
  public boolean canImport(JComponent arg0, DataFlavor[] arg1) {
    for (int i = 0; i < arg1.length; i++) {
      DataFlavor flavor = arg1[i];
      if (flavor.equals(DataFlavor.javaFileListFlavor)) {
        return true;
      }
      if (flavor.equals(DataFlavor.stringFlavor)) {
        return true;
      }
    }
    // Je n'ai pas trouvé de correspondance, donc :
    return false;
  }

  /**
   *  Effectuer l'importation proprement dite.
   * 
   * @see javax.swing.TransferHandler#importData(javax.swing.JComponent,
   *      java.awt.datatransfer.Transferable)
   */
  public boolean importData(JComponent comp, Transferable t) {
    DataFlavor[] flavors = t.getTransferDataFlavors();
    for (int i = 0; i < flavors.length; i++) {
      DataFlavor flavor = flavors[i];
      try {
        if (flavor.equals(DataFlavor.javaFileListFlavor)) {

          List l = (List) t
              .getTransferData(DataFlavor.javaFileListFlavor);
          Iterator iter = l.iterator();
          while (iter.hasNext()) {
            File file = (File) iter.next();
            // Maintenant, faites quelque chose avec le fichier.....
          }
          return true;
        } else if (flavor.equals(DataFlavor.stringFlavor)) {
          String fileOrURL = (String) t.getTransferData(flavor);
          ImageIcon img = new ImageIcon("../Ressources/Logo.gif");
			int option = JOptionPane.showConfirmDialog(null, "Voulez-vous ajouter ce ou ces film(s)?", "Ajout", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,img);
			if(option == JOptionPane.OK_OPTION){
				System.out.println("Ajout validé");
			}
			if(option == JOptionPane.NO_OPTION){
				System.out.println("Ajout refusé");
			}
		  System.out.println("Le chemin est : " + fileOrURL);
          // Maintenant, faites quelque chose avec le String.

        } else {
          // Ne revenez pas ; essayez le Flavor suivante.
        }
      } catch (IOException ex) {
      } catch (UnsupportedFlavorException e) {
      }
    }
    // Si vous arrivez ici, je n'ai pas aimé le Flavor.
    Toolkit.getDefaultToolkit().beep();
    return false;
  }
}

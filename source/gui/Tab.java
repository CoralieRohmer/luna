package gui;

import tool.Constant;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException; 
import javax.imageio.ImageIO;
import javax.swing.JButton;

/*
 * Création et utilisation de TabComponentsDemo
 */ 
public class Tab extends JTabbedPane{    

	//~ private JMenuItem tabComponentsItem;
	//~ private JMenuItem scrollLayoutItem;
	private Image img;
	
	public Tab(){
		super();
		try {

		  img = ImageIO.read(new File("../fondBouton.png"));

		} catch (IOException e) {

		  e.printStackTrace();

		}
		this.removeAll();
		ImageIcon icon = createImageIcon("/home/coralie/luna/Ressources/Images/test1.png");
		 
		JComponent panel1 = makeTextPanel("../Ressources/Images/test1.png");
		panel1.setPreferredSize(new Dimension(getWidth(), getHeight()));
		this.addTab("Manette", icon, panel1,
				"Manette Nes");
		this.setMnemonicAt(0, KeyEvent.VK_1);
		this.setTabComponentAt(0, new TabCloseButton(this));
		 
		JComponent panel2 = makeTextPanel("../Ressources/Images/test2.png");
		this.addTab("Portal", icon, panel2,
				"Portal Toilette");
		this.setMnemonicAt(1, KeyEvent.VK_2);
		this.setTabComponentAt(1, new TabCloseButton(this));
		this.setBackgroundAt(1, Constant.GUI_COLOR);
		 
		JComponent panel3 = makeTextPanel("../Ressources/Images/test3.png");
		this.addTab("Blague", icon, panel3,
				"Blague de developpeur");
		this.setMnemonicAt(2, KeyEvent.VK_3);
		this.setTabComponentAt(2, new TabCloseButton(this));
		
		this.addTab("+", icon, new JPanel(),
				"Ouvrir un nouvelle onglet");
		this.setMnemonicAt(3, KeyEvent.VK_4);
		
		//The following line enables to use scrolling tabs.
		this.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		
	}

	//~ public TabComponentsDemo(String title) {
		//~ super(title);
		//~ setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//~ initMenu();        
		//~ add(pane);        
	//~ }

	//~ public void runTest() {
		//~ for (int i = 0; i < tabNumber; i++) {
			//~ String title = "Tab " + i;
			//~ pane.add(title, new JLabel(title));
			//~ initTabComponent(i);
		//~ }
		//~ tabComponentsItem.setSelected(true);
		//~ pane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		//~ scrollLayoutItem.setSelected(false);
		//~ setSize(new Dimension(400, 200));
		//~ setVisible(true);
	//~ }
		//~ setLocationRelativeTo(null);

		//~ setLocationRelativeTo(null);

	//~ private void initTabComponent(int i) {
		//~ pane.setTabComponentAt(i,
				 //~ new ButtonTabComponent(pane));
	//~ }    

	//~ //Menu de réglage

	//~ private void initMenu() {
		//~ JMenuBar menuBar = new JMenuBar();
		//~ //crée le menu des options
		//~ tabComponentsItem = new JCheckBoxMenuItem("Utilise TabComponents", true);
		//~ tabComponentsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.ALT_MASK));
		//~ tabComponentsItem.addActionListener(new ActionListener() {
			//~ public void actionPerformed(ActionEvent e) {
				//~ for (int i = 0; i < pane.getTabCount(); i++) {
					//~ if (tabComponentsItem.isSelected()) {
						//~ initTabComponent(i);
					//~ } else {
						//~ pane.setTabComponentAt(i, null);
					//~ }
				//~ }
			//~ }
		//~ });
		//~ scrollLayoutItem = new JCheckBoxMenuItem("Set ScrollLayout");
		//~ scrollLayoutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
		//~ scrollLayoutItem.addActionListener(new ActionListener() {
			//~ public void actionPerformed(ActionEvent e) {
				//~ if (pane.getTabLayoutPolicy() == JTabbedPane.WRAP_TAB_LAYOUT) {
					//~ pane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
				//~ } else {
					//~ pane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
				//~ }
			//~ }
		//~ });
		//~ JMenuItem resetItem = new JMenuItem("Reset JTabbedPane");
		//~ resetItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_MASK));
		//~ resetItem.addActionListener(new ActionListener() {
			//~ public void actionPerformed(ActionEvent e) {
				//~ runTest();
			//~ }
		//~ });
	   
		//~ JMenu optionsMenu = new JMenu("Options");
		//~ optionsMenu.add(tabComponentsItem);
		//~ optionsMenu.add(scrollLayoutItem);
		//~ optionsMenu.add(resetItem);
		//~ menuBar.add(optionsMenu);
		//~ setJMenuBar(menuBar);
	//~ }

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//~ Graphics2D g2d = (Graphics2D)g;
		//~ GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, 20, Color.cyan, true);
		//~ g2d.setPaint(gp);
		//~ g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		//~ g2d.setColor(Color.black);     
	}
	/** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = GUI.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

	protected JComponent makeTextPanel(String text) {
		JLabel filler = new JLabel();
		filler.setIcon(new ImageIcon(new ImageIcon(text).getImage().getScaledInstance(1500,1000, Image.SCALE_DEFAULT)));
		JScrollPane scroll = new JScrollPane(filler);
		return scroll;
	}
}

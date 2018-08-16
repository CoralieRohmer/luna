package gui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;

/**
 * Component to be used as tabComponent;
 * Contains a JLabel to show the text and 
 * a JButton to close the tab it belongs to 
 */ 
public class TabCloseButton extends JPanel {
    private final JTabbedPane pane;

    public TabCloseButton(final JTabbedPane pane) {
        //unset default FlowLayout' gaps
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        if (pane == null) {
            throw new NullPointerException("TabbedPane is null");
        }
        this.pane = pane;
        setOpaque(false);
        
        //faire en sorte que JLabel lise les titres de JTabbedPane
        JLabel label = new JLabel() {
            public String getText() {
                int i = pane.indexOfTabComponent(TabCloseButton.this);
                if (i != -1) {
                    return pane.getTitleAt(i);
                }
                return null;
            }
        };
        
        add(label);
        //ajouter plus d'espace entre le label et le bouton
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        //tab button
        JButton button = new TabButton();
        add(button);
        //ajouter plus d'espace en haut du composant.
        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
    }

    private class TabButton extends JButton implements ActionListener {
        public TabButton() {
            int size = 17;
            setPreferredSize(new Dimension(size, size));
            setToolTipText("close this tab");
            //Rendre le bouton identique pour tous les Laf's.
            setUI(new BasicButtonUI());
            //Make it transparent
            setContentAreaFilled(false);
            //Faites en sorte qu'il soit transparent
            setFocusable(false);
            setBorder(BorderFactory.createEtchedBorder());
            setBorderPainted(false);
            //Réalisation d'un bel effet de retournement
            //Nous utilisons le même écouteur pour tous les boutons.
            addMouseListener(buttonMouseListener);
            setRolloverEnabled(true);
            //Fermez l'onglet approprié en cliquant sur le bouton
            addActionListener(this);//shift the image for pressed buttons
        }

        public void actionPerformed(ActionEvent e) {
            int i = pane.indexOfTabComponent(TabCloseButton.this);
            if (i != -1) {
                pane.remove(i);
            }
        }

        //nous ne voulons pas mettre à jour l'interface utilisateur pour ce bouton.
        public void updateUI() {
        }

        //peindre la croix
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            //déplacer l'image pour les boutons pressés
            if (getModel().isPressed()) {
                g2.translate(1, 1);
            }
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.BLACK);
            if (getModel().isRollover()) {
                g2.setColor(Color.MAGENTA);
            }
            int delta = 6;
            g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
            g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
            g2.dispose();
        }
    }

    private final static MouseListener buttonMouseListener = new MouseAdapter() {
        public void mouseEntered(MouseEvent e) {
            Component component = e.getComponent();
            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                button.setBorderPainted(true);
            }
        }

        public void mouseExited(MouseEvent e) {
            Component component = e.getComponent();
            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                button.setBorderPainted(false);
            }
        }
    };
}



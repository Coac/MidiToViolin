package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

import javax.swing.KeyStroke;

/**
* <p>
* La classe <b><code>Menu</code></b> est utilis�e par <code>MainWindow</code> 
* pour afficher le menu de l'application Sudoku.
* </p>
* <p>
* Elle permet � l'utilisateur de l'application d'acc�der � diff�rentes
* fonctions via un menu d�roulant. <b><code>Menu</code></b> g�re ainsi les 
* �v�nements li�s � ce menu comme la s�lection/clic mais aussi les raccourcis 
* clavier.
* </p>
* 
*
* @author Victor LE
* @version 1.0
*
* @see MainWindow
* @see CaseButton
*
* @since 1.0
*/
public class Menu implements ActionListener {
    
    private MainFrame gui;
    
    /**
     * Instancie un Menu avec en param�tre un MainWindow pour permettre
     * d'int�ragir avec le Sudoku
     *
     * @param gui the gui
     */
    public Menu (MainFrame gui)
    {
    	this.gui = gui;
    }

    
    
    /**
     * Cr�er un JMenuBar en enregistrant les �v�nements pour chaque item du
     * menu.
     *
     * @return le JMenuBar
     */
    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;

        menuBar = new JMenuBar();

        menu = new JMenu("File");
        menuBar.add(menu);

        menuItem = new JMenuItem("Load MIDI");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Select Track");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Set Tempo");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Set display Speed");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Change note display");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menu.addSeparator();
        
        menuItem = new JMenuItem("Start");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Pause");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Stop");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);

        
        menu.addSeparator();
        
        menuItem = new JMenuItem("Quit");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menu = new JMenu("Help");
        menuItem.addActionListener(this);
        menuBar.add(menu);
        
        menuItem = new JMenuItem("Help");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menu.addSeparator();
        
        menuItem = new JMenuItem("About");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        return menuBar;
    }
    
	
    /**
     * M�thode du � l'impl�mentation de ActionListener.
     * Permet de g�rer les �v�nements li�s au menu, et appelle les m�thodes
     * ad�quats dans MainWindow
     *
     */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JMenuItem source = (JMenuItem)(e.getSource());
		String cmd = source.getActionCommand().toLowerCase();
		
		switch (cmd) {
		case "load midi":
			gui.loadMidi();
			break;
		case "start":
			gui.start();
			break;
		case "stop":
			gui.stop();
			break;
		case "pause":
			gui.pause();
			break;
		case "about":
			gui.about();
			break;
		case "quit":
			gui.quit();
			break;
		case "help":
			gui.help();
			break;
		case "change note display":
			gui.changeNoteDisplay();
			break;
		case "select track":
			gui.selectTrack();
			break;
		case "set tempo":
			gui.setTempo();
			break;
		case "set display speed":
			gui.setDisplaySpeed();
			break;
		default:
			break;
		}
		
	}
}

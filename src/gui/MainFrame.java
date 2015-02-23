package gui;


import gui.musicPlayer.MusicPlayer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import midireader.MidiFileFormatException;

public class MainFrame extends JFrame{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private ScreenPanel screen;
	private ButtonsPanel buttons;
	private Menu menu;
	private SliderPanel slider;
	
	private static MusicPlayer player = new MusicPlayer();
	
	private String title;
	
	private List<NoteToDraw> NotesToDraw  = new LinkedList<NoteToDraw>(); 
	
	public MainFrame(String title, int width, int height) {
		
		super(title);
		this.title = title;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		setLayout(new BorderLayout());
		//setResizable(false);
		setSize(width, height);
		setLocationByPlatform(true);
		
		
		// Menu
		menu = new Menu(this);
		this.setJMenuBar(menu.createMenuBar());
		
		// Ecran d'affichage des notes
		screen = new ScreenPanel(this);
		getContentPane().add(BorderLayout.CENTER, screen);
		
		
		// Panel du bas
		JPanel panel = new JPanel();
		GridLayout grid = new GridLayout(2,1); 
		grid.setVgap(2);
		grid.setHgap(2);
		panel.setLayout( grid );
		getContentPane().add(BorderLayout.SOUTH, panel);
		
		
		//Slider
		slider = new SliderPanel(this);
		slider.init(300);
		panel.add(slider);
				
		// Boutons du bas
		buttons = new ButtonsPanel(this);
		buttons.init();
		panel.add(buttons);
		
		
		
		setVisible(true);
	}
	
	
	public void loadMidi() {
		String filename = File.separator+"tmp";
		JFileChooser fc = new JFileChooser(new File(filename));

		fc.showOpenDialog(this);
		File selFile = fc.getSelectedFile();
		
		// Rien sélectionné
		if(selFile == null)
			return;
		
		try {
			player.load(selFile.getAbsolutePath());
			
			
			slider.init(player.getTotalTime());
			setVisible(true);
			screen.repaint();
			
			
			
			
		} catch (IOException | MidiFileFormatException | RuntimeException e) {
			JOptionPane.showMessageDialog(this, "Error in the Midi selected", 
					"Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

	private Thread drawThread;
	
	public void stop() {
		player.stop();
		
		pause();
	}


	public void pause() {
		player.pause();
		
		if(drawThread!=null)
			drawThread.interrupt();
	}


	public void help() {
		
		
	}


	public void start() {
		if(!player.isLoaded())
			JOptionPane.showMessageDialog(this, "Load the MIDI before starting", 
					"Error", JOptionPane.ERROR_MESSAGE);
			
		player.start();
		
		drawThread = new Thread(new DrawThread(this));
		drawThread.start();
	}


	public void quit() {
		dispose();
		
	}


	public void about() {
		String str = "This app has been designed for the beginner. "
				+ "\nIt will help them to learn the notes on the instrument"
				+ "\n\nCopyright Victor LE - Visit victor-le.fr";
		String title = "A propos - "  + this.title;
		
		JOptionPane.showMessageDialog(this, str, title, JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	public SliderPanel getSliderPanel() {
		return slider;
	}


	public void changeNoteDisplay() {
		player.changeNoteDisplay();
		
	}


	public void selectTrack() {
		String in = JOptionPane.showInputDialog(null);
		try {
			player.setSelectedTrack(Integer.parseInt(in));
			NotesToDraw.clear();
			repaint();
		}
		catch(NumberFormatException a) {
			JOptionPane.showMessageDialog(this, "Erreur de saisi", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		
	}


	public void setTempo() {
		String in1 = JOptionPane.showInputDialog(null);
		try {
			player.setMicroPerTick(Integer.parseInt(in1));
		}
		catch(NumberFormatException a) {
			JOptionPane.showMessageDialog(this, "Erreur de saisi", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
	}


	public void setDisplaySpeed() {
		String in11 = JOptionPane.showInputDialog(null);
		try {
			NoteToDraw.incrementY = (Integer.parseInt(in11));
		}
		catch(NumberFormatException a) {
			JOptionPane.showMessageDialog(this, "Erreur de saisi", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		
	}


	public MusicPlayer getPlayer() {
		
		return player;
	}
	
	public List<NoteToDraw> getNotesToDraw() {
		return NotesToDraw;
	}
	
	
	
}

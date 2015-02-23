package gui;

import gui.musicPlayer.MusicPlayer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class ScreenPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8255887955649743991L;
	
	MainFrame frame;
	ScreenPanel screenPanel;

	private int oneX = 7;
	private int oneY = 7;

	boolean up = false;
	boolean down = true;
	boolean left = false;
	boolean right = true;
	
	private static final int NUMBER_OF_STRING = 4;
	
	public static int xFirstString;
	
	public ScreenPanel(MainFrame frame ){
		super();
		this.frame = frame;
	}

	@Override
	public void paintComponent(Graphics g) {
		xFirstString = getWidth()/(NUMBER_OF_STRING +1);
		g.setColor(Color.BLUE);
		g.drawLine(xFirstString, 0, xFirstString,getHeight());
		g.drawLine(xFirstString*2, 0, xFirstString*2, getHeight());
		g.drawLine(xFirstString*3, 0, xFirstString*3, getHeight());
		g.drawLine(xFirstString*4, 0, xFirstString*4, getHeight());
		g.fillRect(oneX, oneY, 6, 6);
		
		if(g instanceof Graphics2D)
	      {
	        Graphics2D g2 = (Graphics2D)g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	        RenderingHints.VALUE_ANTIALIAS_ON);

	        g2.setColor(Color.BLACK);
	   
	        if(frame.getPlayer().isLoaded()) {
	        	g2.drawString("Track : " + (frame.getPlayer().getSelectedTrack() +1)
	        		+ "\nNumber of Tracks : " + frame.getPlayer().getNumberOfTrack()  ,100,100); 
	        	g2.drawString("Notes : " + frame.getPlayer().getPlayingNote(), 200, 200); 
	        }
	        
	        g2.setFont(new Font("Arial", Font.PLAIN, 20)); 
	        g2.setColor(Color.DARK_GRAY);
	        synchronized(frame.getNotesToDraw()) {
		        for(NoteToDraw note : frame.getNotesToDraw()) {
		        	g2.drawString(note.getNoteFR(), note.getX(), note.getY());
		        }
	        }
	       }

	}

	
}

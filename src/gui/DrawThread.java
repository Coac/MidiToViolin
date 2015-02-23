package gui;

import java.util.LinkedList;
import java.util.List;


public class DrawThread implements Runnable {
	private MainFrame frame;
	
	private static List<NoteToDraw> NoteToDel = new LinkedList<NoteToDraw>();
	
	public DrawThread(MainFrame frame) {
		this.frame = frame;
	}
	
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				
				System.out.println("interrompu DrawThread");
				return;
			}
			
			
			frame.getSliderPanel().setValue((int)frame.getPlayer().getActualTick());
	
	
			synchronized(frame.getNotesToDraw()) {
				for(NoteToDraw note : (frame.getNotesToDraw()) ){
					note.incrementY();
					if(note.getY() > frame.getHeight())
						NoteToDel.add(note);
				}
				
				for(NoteToDraw note : NoteToDel) {
					frame.getNotesToDraw().remove(note);
				}
				NoteToDel.clear();
				
				synchronized(frame.getPlayer().getPlayingNote()) {
					for(Integer note : frame.getPlayer().getPlayingNote()) {
						NoteToDraw noteDrawToAdd = new NoteToDraw(note);
						
						for(NoteToDraw noteDraw : frame.getNotesToDraw()) {
							if(noteDraw.getNoteNumber() == noteDrawToAdd.noteNumber && noteDraw.getY() == noteDrawToAdd.getY() + NoteToDraw.incrementY) {
								noteDrawToAdd.note = "|";
								noteDrawToAdd.noteFR = "|";
							}
						}
						frame.getNotesToDraw().add(noteDrawToAdd);
					}
				}
				
				frame.repaint();
				
				System.out.println("noteToDraw=" + frame.getNotesToDraw().size() + " noteOnplay="+ frame.getPlayer().getPlayingNote().size());
				
			}
		}	
	}
	
}

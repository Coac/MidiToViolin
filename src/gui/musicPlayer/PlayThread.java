package gui.musicPlayer;

import midireader.midievent.NoteMidiEvent;
import midireader.midievent.NoteMidiEvent.NoteEventType;
import gui.MainFrame;
import gui.musicPlayer.*;

public class PlayThread implements Runnable {

	private MusicPlayer player;

	public PlayThread(MusicPlayer player) {
		this.player = player;
	}
	
	@Override
	public void run() {
		
		for (double i = player.getActualTick(); i < player.getTotalTime(); i+=(player.getSleepMs()*1000/player.getMicroPerTick()))
		{
			try {
				Thread.sleep((long) player.getSleepMs());
			} 
			catch (InterruptedException e)
			{
				System.out.println("interrompu");
				//Play.Notes.clear();
				return;
			}
			
			synchronized(player.getPlayingNote()) {

			for(NoteMidiEvent note : player.getAllTracks().get(player.getSelectedTrack()))
				if(note.getTotalTime() >= player.getActualTick() && note.getTotalTime() < i)  {
					if(note.getNoteEventType() == NoteEventType.NOTE_ON)
						player.getPlayingNote().add(note.getNoteNumber());
					else
						player.getPlayingNote().remove(new Integer(note.getNoteNumber()));
				}

				
			}
			player.setActualTick((int) i);
			
			
			
		}
			
		
		
		
	}
	

}

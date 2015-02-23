package gui.musicPlayer;

import java.awt.Container;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import midireader.MidiFileFormatException;
import midireader.MidiReader;
import midireader.midievent.MidiEvent;
import midireader.midievent.NoteMidiEvent;

public class MusicPlayer {
	
	 private List<List<NoteMidiEvent>> allTracks;
	 
	 private List<Integer> PlayingNote = new LinkedList<Integer>();
	 
	 private int selected = 0;
	 private double actualTick = 0;
	 
	 private int totalTime = 300;
	 private long microPerTick = 500;
	 private int sleepMs = 50;
	 
	 private boolean FR = false;
	 private int octaveVariation = -12;
	 
	 private boolean loaded = false;
	 
	 private Thread playThread;
	
	 public void load(String chemin) throws IOException, MidiFileFormatException {
		allTracks =  new ArrayList<List<NoteMidiEvent>>();
		
		MidiReader reader = new MidiReader(chemin);
		microPerTick =reader.getMidiFileInfo().getMicrosecondsPerTick();
		
	    ArrayList<String> differentTracks =  new ArrayList<String>();
		    for (MidiEvent nextEvent : reader) {
		    	if(nextEvent instanceof NoteMidiEvent) {
		    		NoteMidiEvent note = (NoteMidiEvent) nextEvent;
		    		String channelStr = note.getChannel().toString();
		    		if(differentTracks.contains(channelStr)) {
		    				allTracks.get(differentTracks.indexOf(channelStr)).add(note);
		    		}
		    		else {
		    			differentTracks.add(channelStr);
		    			allTracks.add(new ArrayList<NoteMidiEvent>());
		    			allTracks.get(differentTracks.indexOf(channelStr)).add(note);
		    		}
		    	}
		    }
		

		reader.close();
		
		if(allTracks !=null)
			selected = 0;
		else
			selected = -1;
		
		actualTick=0;
		//Trouve la durée de la musique
		totalTime = 0;
		
		for(List<NoteMidiEvent> listNotes : allTracks) {
			int total = 0;
			for(NoteMidiEvent note : listNotes) {
				
				total=(int) note.getTotalTime();
				if(total > totalTime) {
					totalTime = total;
				}
			}
		}
		
		loaded = true;
		
	}

	public int getTotalTime() {
		return totalTime;
	}
	
	public List<List<NoteMidiEvent>> getAllTracks() {
		return allTracks;
	}
	
	public boolean isLoaded() {
		return loaded;
	}

	public void changeNoteDisplay() {
		FR = !FR;
		
	}

	public void setSelectedTrack(int numTrack) {
		selected = numTrack;
		
	}
	
	public int getSelectedTrack() {
		return selected;
		
	}

	public void setMicroPerTick(int microPerTick) {
		this.microPerTick = microPerTick;
		
	}

	public void setSleepMs(int ms) {
		sleepMs = ms;
		
	}

	public int getNumberOfTrack() {
	
		return allTracks.size();
	}

	public void setActualTick(int value) {
		actualTick = value;
		
	}

	public void pause() {
		if(playThread!=null)
			playThread.interrupt();
	}

	public void stop() {
		actualTick = 0;
		pause();
		
	}

	public void start() {
		pause();
		playThread = new Thread(new PlayThread(this));
		playThread.start();
		
	}

	public double getActualTick() {
		return actualTick;
	}

	public int getSleepMs() {
		return sleepMs;
	}

	public long getMicroPerTick() {
		
		return microPerTick;
	}

	public List<Integer> getPlayingNote() {
		return PlayingNote;
	}
	
}

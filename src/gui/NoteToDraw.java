package gui;



public class NoteToDraw {
	public String note;
	public String noteFR;
	public int noteNumber;
	private int x;
	private int y;
	
	public static int initialY = 5;
	
	public static int incrementY = 10;
	
	

	
	
	public NoteToDraw(int note) {
		noteNumber = note ;
		switch (noteNumber%12) {
		case 0:
			this.note = "C";
			noteFR = "DO";
			break;
		case 1:
			this.note = "C#";
			noteFR = "DO#";
			break;
		case 2:
			this.note = "D";
			noteFR = "RE";
			break;
		case 3:
			this.note = "D#";
			noteFR = "RE#";
			break;
		case 4:
			this.note = "E";
			noteFR = "MI";
			break;
		case 5:
			this.note = "F";
			noteFR = "FA";
			break;
		case 6:
			this.note = "F#";
			noteFR = "FA#";
			break;
		case 7:
			this.note = "G";
			noteFR = "SOL";
			break;
		case 8:
			this.note = "G#";
			noteFR = "SOL#";
			break;
		case 9:
			this.note = "A";
			noteFR = "LA";
			break;
		case 10:
			this.note = "A#";
			noteFR = "LA#";
			break;
		case 11:
			this.note = "B";
			noteFR = "SI";
			break;

		default:
			break;
		}
		
		
		this.note += noteNumber/12;
		
		y = initialY;
		
		if(noteNumber >= 43 && noteNumber <=49)
			x = ScreenPanel.xFirstString;
		else if(noteNumber >= 50 && noteNumber <=56)
			x = ScreenPanel.xFirstString * 2;
		else if(noteNumber >= 57 && noteNumber <=63)
			x = ScreenPanel.xFirstString * 3;
		else if(noteNumber >= 64 && noteNumber <=72)
			x = ScreenPanel.xFirstString * 4;
		
		
	}
		
	
	public int getY() {
		return y;
	}
	
	public int getX() {
		return x;
	}
	
	
	public String getNote() {
		return note;
	}
	
	public String getNoteFR() {
		return noteFR;
	}
	
	public int getNoteNumber() {
		return noteNumber;
	}


	public void incrementY() {
		y+= incrementY;
		
	}
}

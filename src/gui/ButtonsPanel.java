package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class ButtonsPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int NB_BUTTONS = 3;

	private MainFrame gui;
	
	
	public ButtonsPanel(MainFrame gui) {
		super();
		this.gui = gui;

	}
	
	public void init() {
		GridLayout grid = new GridLayout(1,NB_BUTTONS); 
		grid.setVgap(2);
		grid.setHgap(2);
		setLayout( grid );
		
		JButton temp;
		
		temp = new JButton("Stop");
		temp.addActionListener(this);
		add(temp);
		
		temp = new JButton("Pause");
		temp.addActionListener(this);
		add(temp);
		
		temp = new JButton("Start");
		temp.addActionListener(this);
		add(temp);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)(e.getSource());
		String cmd = source.getActionCommand().toLowerCase();
		
		switch (cmd) {
		case "start":
			gui.start();
			break;
		case "stop":
			gui.stop();
			break;
		case "pause":
			gui.pause();
			break;
		default:
			break;
			
		}
		
	}
	
	

}

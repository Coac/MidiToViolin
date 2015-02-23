package gui;


import gui.musicPlayer.MusicPlayer;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderPanel extends JPanel implements ChangeListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainFrame gui;
	private JSlider slider;
	
	private static final int MIN_SLIDER = 0;
	private int maxSlider;

	public SliderPanel(MainFrame gui) {
		super();
		this.gui = gui;
		setLayout(new BorderLayout(100,100));
	}
	
	public void init(int maxSlider) {
		removeAll();
		slider = new JSlider(JSlider.HORIZONTAL,
                MIN_SLIDER, maxSlider, MIN_SLIDER);
		
		this.maxSlider = maxSlider ;
		
		slider.setMajorTickSpacing(maxSlider/5);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		
		add(slider);
		slider.addChangeListener(this);
	}
	
	public void setValue(int value) {
		slider.setValue(value);
	}


	@Override
	public void stateChanged(ChangeEvent arg0) {
		gui.getPlayer().setActualTick(slider.getValue());
		
	}

}

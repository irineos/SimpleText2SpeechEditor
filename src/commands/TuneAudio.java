package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Document;
import view.Text2SpeechEditorView;

public class TuneAudio implements ActionListener {
	private float volume;
	private float pitch;
	private float rate;

	public TuneAudio() {
		
		
	}

	
	public void actionPerformed(ActionEvent actionEvent) {
		TuneAudio a = new TuneAudio();
		Text2SpeechEditorView w = Text2SpeechEditorView.getInstance();
		ReplayManager m = ReplayManager.getInstance();
		if(!m.getPlay()) {
			a.getTuneAudioView();
			if(m.getRec()) {
				m.getCommandsList().add(a);
				m.getEventList().add(actionEvent);
			}
		}
		else {
			a.setVPR(w.getCurrentDocument(),volume,pitch,rate);
		}
		
		System.out.println("tune audio");
	}
	
	public void getTuneAudioView() {
		Text2SpeechEditorView w = Text2SpeechEditorView.getInstance();
		Document doc = w.getCurrentDocument();
		if(doc!=null) {
			volume = doc.getVolume();
			pitch = doc.getPitch();
			rate = doc.getRate();
		}
		
		JFrame frame = new JFrame("Tune Audio");
		frame.setBounds(100, 100, 430, 250);
		frame.setLocationRelativeTo(w.getF());
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		createVolumeSlider(frame);
		createPitchSlider(frame);
		createRateSlider(frame);
		createOkButon(frame,doc);
		
		frame.setVisible(true);
	}
	
	public void createVolumeSlider(JFrame frame) {
		JLabel lblVolume = new JLabel("Volume:");
		lblVolume.setBounds(82, 12, 57, 30);
		frame.getContentPane().add(lblVolume);
		
		JSlider slider = new JSlider(0,10);
		slider.setBounds(12, 47, 200, 16);
		frame.getContentPane().add(slider);
		slider.setValue((int)(volume*10));
		
		JLabel lblGetvolume = new JLabel();
		lblGetvolume.setBounds(142, 20, 70, 15);
		frame.getContentPane().add(lblGetvolume);
		lblGetvolume.setText(String.valueOf(volume));
		
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				volume = (float)slider.getValue()/10.0f;
				lblGetvolume.setText(String.valueOf(volume));				
			}	
		});
	}
	
	public void createPitchSlider(JFrame frame) {
		JLabel lblPitch = new JLabel("Pitch:");
		lblPitch.setBounds(91, 75, 70, 15);
		frame.getContentPane().add(lblPitch);
		
		JSlider slider_1 = new JSlider(0,200);
		slider_1.setBounds(12, 102, 200, 16);
		frame.getContentPane().add(slider_1);
		slider_1.setValue((int)pitch);
		
		JLabel lblGetpitch = new JLabel();
		lblGetpitch.setBounds(142, 75, 70, 15);
		frame.getContentPane().add(lblGetpitch);
		lblGetpitch.setText(String.valueOf(pitch));
		
		slider_1.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				pitch = (float)slider_1.getValue();
				lblGetpitch.setText(String.valueOf(pitch));	
			}
		});
	}
	
	public void createRateSlider(JFrame frame) {
		JLabel lblRate = new JLabel("Rate:");
		lblRate.setBounds(91, 130, 70, 15);
		frame.getContentPane().add(lblRate);
		
		JSlider slider_2 = new JSlider(50,300);
		slider_2.setBounds(12, 157, 200, 16);
		frame.getContentPane().add(slider_2);
		slider_2.setValue((int)rate);
		
		JLabel lblGetrate = new JLabel();
		lblGetrate.setBounds(142, 130, 70, 15);
		frame.getContentPane().add(lblGetrate);
		lblGetrate.setText(String.valueOf(rate));
		
		slider_2.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				rate = (float)slider_2.getValue();
				lblGetrate.setText(String.valueOf(rate));				
			}	
		});	
	}
	
	public void createOkButon(JFrame frame,Document doc) {
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(263, 102, 117, 25);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(doc!=null) {
					setVPR(doc,volume,pitch,rate);
				}
				frame.dispose();
			}	
		});
		frame.getContentPane().add(btnOk);
	}
	
	public void setVPR(Document doc,float volume,float pitch,float rate) {
		doc.setVolume(volume);
		doc.setPitch(pitch);
		doc.setRate(rate);
	}
	

}

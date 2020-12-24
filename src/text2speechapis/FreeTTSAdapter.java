package text2speechapis;

import javax.swing.SwingUtilities;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FreeTTSAdapter implements TextToSpeechAPI {
	
	private VoiceManager vm;
	private Voice voice;

	public FreeTTSAdapter() {
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		vm = VoiceManager.getInstance();
	    voice = vm.getVoice("kevin16");
	    voice.allocate();	
	}

	
	public void play(String s) {
		SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
            	voice.speak(s);
           }
        });
	}

	
	public void setVolume(float vol) {
		voice.setVolume(vol);

	}

	
	public void setPitch(float hertz) {
		voice.setPitch(hertz);

	}

	
	public void setRate(float wpm) {
		voice.setRate(wpm);

	}


	@Override
	public float getVolume() {
		return voice.getVolume();
	}


	@Override
	public float getPitch() {
		return voice.getPitch();
	}


	@Override
	public float getRate() {
		return voice.getRate()/(voice.getDurationStretch()*voice.getDurationStretch());
	}
	
	

}

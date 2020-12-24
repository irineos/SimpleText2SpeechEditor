package text2speechapis;

public class FakeTextToSpeechAPI implements TextToSpeechAPI {
	//public String speech;
	private float volume;
	private float pitch;
	private float rate;
	
	public FakeTextToSpeechAPI() {
		// TODO Auto-generated constructor stub
	}

	
	public void play(String s) {
		System.out.println(s);

	}


	@Override
	public void setVolume(float vol) {
		volume=vol;
		
	}


	@Override
	public void setPitch(float hertz) {
		pitch = hertz;
		
	}


	@Override
	public void setRate(float wpm) {
		rate = wpm;
		
	}


	@Override
	public float getVolume() {
		return volume;
	}


	@Override
	public float getPitch() {
		return pitch;
	}


	@Override
	public float getRate() {
		return rate;
	}

	
	
	
	

}

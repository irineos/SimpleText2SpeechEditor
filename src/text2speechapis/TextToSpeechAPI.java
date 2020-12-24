package text2speechapis;

public interface TextToSpeechAPI {
	
	public void play(String s);
	public void setVolume(float vol);
	public void setPitch(float hertz);
	public void setRate(float wpm);
	
	public float getVolume();
	public float getPitch();
	public float getRate();

}

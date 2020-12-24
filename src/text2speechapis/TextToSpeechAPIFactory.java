package text2speechapis;

public class TextToSpeechAPIFactory {
	
	private TextToSpeechAPI ttsAPI;

	public TextToSpeechAPIFactory() {
		
	}
	
	public TextToSpeechAPI createTTSAPI(String ttsapi) {
		if(ttsapi.equals("ttsapi"))
				return new FreeTTSAdapter();
		else if(ttsapi.equals("fakettsapi"))
			return new FakeTextToSpeechAPI();
		return null;
		
	}

}

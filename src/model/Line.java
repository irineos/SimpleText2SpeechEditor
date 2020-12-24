package model;

import java.util.ArrayList;
import encodingstrategies.EncodingStrategy;
import encodingstrategies.Rot13Encoding;
import text2speechapis.TextToSpeechAPI;
import text2speechapis.TextToSpeechAPIFactory;

public class Line {
	
	private ArrayList<String> words;
	private EncodingStrategy encodingStrategy;
	private TextToSpeechAPI audioManager;
	
	/*public Line() {
		//initialize fields
		words = new ArrayList<String>();
		//use factory to initialize TextToSpeechAPI audioManager
		TextToSpeechAPIFactory factory = new TextToSpeechAPIFactory();
		audioManager = factory.createTTSAPI("ttsapi");
		//encodingStrategy = new Rot13Encoding();
	}*/
	
	
	public Line() {
		//initialize fields
		words = new ArrayList<String>();
		//use factory to initialize TextToSpeechAPI audioManager
		//TextToSpeechAPIFactory factory = new TextToSpeechAPIFactory();
		//audioManager = factory.createTTSAPI(api);
		//encodingStrategy = new Rot13Encoding();
		
	}
	//use TextToSpeechAPI(lib) methods
	public void playLine() {
		String finalSpeech="";
		for(int i=0;i<words.size();i++) {
			finalSpeech+=words.get(i);
			if(i!=words.size()-1)
				finalSpeech+=" ";
		}
		audioManager.play(finalSpeech);
	}
	public void playReverseLine() {
		String finalSpeech="";
		for(int i=words.size()-1;i>=0;i--) {
			finalSpeech+=words.get(i);
			if(i!=0)
				finalSpeech+=" ";
		}
		audioManager.play(finalSpeech);
	}
	public void playEncodedLine() {
		String encodedWord;
		String finalSpeech="";
		for(int i=0;i<words.size();i++) {
			encodedWord = encodingStrategy.encode(words.get(i));
			finalSpeech+=encodedWord;
			if(i!=words.size()-1)
				finalSpeech+=" ";
		}
		audioManager.play(finalSpeech);
	}
	public void tuneEncodingStrategy(EncodingStrategy encoding) {
		encodingStrategy = encoding;
		
	}
	
	public void setAudioManager(TextToSpeechAPI audioManager) {
		this.audioManager = audioManager;
	}
	
	
	public void setWords(String w) {
		words.clear();
		String word[] = w.split("[\\s,.-]");
		for(int i=0;i<word.length;i++) {
			words.add(word[i]);
		}
	}
	public String getWords() {
		String lineText="";
		for(int i=0;i<words.size();i++) {
			lineText+=words.get(i);
			if(i!=words.size()-1)
				lineText+=" ";
		}
		return lineText;
	}

	
	
}

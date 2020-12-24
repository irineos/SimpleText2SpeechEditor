package model;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import text2speechapis.TextToSpeechAPI;
import text2speechapis.TextToSpeechAPIFactory;
import encodingstrategies.EncodingStrategy;
import encodingstrategies.Rot13Encoding;

public class Document {
	private String author;
	private String title;
	private String date;
	private String api;
	private String path;
	private EncodingStrategy encodingStrategy;
	private ArrayList<Line> contents;
	private TextToSpeechAPI audioManager;

	
	
	public Document(String author,String title,String date,String path) {
		//initialize fields
		api = "ttsapi";
		contents = new ArrayList<Line>();
		this.author = author;
		this.title = title;
		this.date = date;
		this.path = path;
		encodingStrategy = new Rot13Encoding();
		TextToSpeechAPIFactory factory = new TextToSpeechAPIFactory();
		audioManager = factory.createTTSAPI(api);
		//System.out.println("author:" + author + "\ntitle:"+title + "\ndate:" + date );
	}
	
	//use model.Line methods
	public void playContents() {
		for(int i=0;i<contents.size();i++) {
			Line l = contents.get(i);
			l.setAudioManager(audioManager);
			l.playLine();
		}
	}
	public void playReverseContents() {
		for(int i=contents.size()-1;i>=0;i--) {
			Line l = contents.get(i);
			l.setAudioManager(audioManager);
			l.playReverseLine();
		}
	}
	public void playEncodedContents() {
		for(int i=0;i<contents.size();i++) {
			Line l;
			l = contents.get(i);
			l.setAudioManager(audioManager);
			l.tuneEncodingStrategy(encodingStrategy);
			l.playEncodedLine();
		}
	}
	public void playLine(int line) {
		Line l = contents.get(line);
		l.setAudioManager(audioManager);
		l.playLine();
	}
	public void playReverseLine(int line) {
		Line l = contents.get(line);
		l.setAudioManager(audioManager);
		l.playReverseLine();
	}
	public void playEncodedLine(int line) {
		Line l;
		l = contents.get(line);
		l.setAudioManager(audioManager);
		l.tuneEncodingStrategy(encodingStrategy);
		l.playEncodedLine();
	}
	
	//simple getters 
	public String getAuthor() {
		return author;
	}
	public String getTitle() {
		return title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String d) {
		date = d;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String p) {
		path = p;
	}
	public void setAPI(String api) {
		TextToSpeechAPIFactory factory = new TextToSpeechAPIFactory();
		audioManager = factory.createTTSAPI(api);
		this.api = api;
	}
	public void tuneEncodingStrategy(EncodingStrategy encoding) {
		encodingStrategy = encoding;
		
	}
	public EncodingStrategy getEncodingStrategy() {
		return encodingStrategy;
	}
	
	public void setVolume(float vol) {
		audioManager.setVolume(vol);

	}

	
	public void setPitch(float hertz) {
		audioManager.setPitch(hertz);

	}

	
	public void setRate(float wpm) {
		audioManager.setRate(wpm);

	}
	
	public float getVolume() {
		return audioManager.getVolume();
	}
	public float getPitch() {
		return audioManager.getPitch();
	}
	public float getRate() {
		return audioManager.getRate();
	}
	
	public ArrayList<Line> getContentsList() {
		return contents;
	}
	
	
	//split the JTextArea text to lines,then store these lines to a new Line class object and add this object to Document.contents
	public void setContents(String text) {
		contents.clear();
		String lines[] = text.split("\n");
		for(int i=0;i<lines.length;i++) {
			Line line = new Line();
			line.setWords(lines[i]);
			contents.add(line);
		}
	}
	
	public String getContents() {
		String contentsText="";
		for(int i=0;i<contents.size();i++) {
			contentsText+=contents.get(i).getWords();
			if(i!=contents.size()-1)
				contentsText+="\n";
		}
		return contentsText;
	}

}

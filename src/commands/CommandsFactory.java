package commands;

import java.awt.event.ActionListener;

public class CommandsFactory {
	private DocumentToSpeech documentToSpeech;
	private EditDocument editDocument;
	private LineToSpeech lineToSpeech;
	private NewDocument newDocument;
	private OpenDocument openDocument;
	private ReplayCommand replayCommand;
	private SaveDocument saveDocument;
	private TuneAudio tuneAudio;
	private TuneEncoding tuneEncoding;
	
	private ReplayManager replayManager;

	
	
	

	public CommandsFactory() {
		
	}
	
	public void setRecording(boolean rec) {
		replayManager = ReplayManager.getInstance();
		if(rec)
			replayManager.setRec(true);
		else
			replayManager.setRec(false);

			
	}
	
	
	
	public ActionListener createCommand(String command) {
		//accordingly to the command string a class that implements ActionListener is initialized
		if(command.equals("New")) {
			newDocument = new NewDocument();
			return newDocument;
		}
		else if(command.equals("Open")) {
			openDocument = new OpenDocument();
			return openDocument;
		}
		else if(command.equals("Save As")) {
			saveDocument = new SaveDocument();
			return saveDocument;
		}
		else if(command.equals("Save")) {
			saveDocument = new SaveDocument("Save");
			return saveDocument;
		}
		else if(command.equals("Contents")) {
			documentToSpeech = new DocumentToSpeech();
			return documentToSpeech;
		}
		else if(command.equals("Line")) {
			lineToSpeech = new LineToSpeech();
			return lineToSpeech;
		}
		else if(command.equals("Reversed Contents")) {
			documentToSpeech = new DocumentToSpeech("Reversed Contents");
			return documentToSpeech;
		}
		else if(command.equals("Reversed Line")) {
			lineToSpeech = new LineToSpeech("Reversed Line");
			return lineToSpeech;
		}
		else if(command.equals("Encoded Contents")) {
			documentToSpeech = new DocumentToSpeech("Encoded Contents");
			return documentToSpeech;
		}
		else if(command.equals("Encoded Line")) {
			lineToSpeech = new LineToSpeech("Encoded Line");
			return lineToSpeech;
		}
		else if(command.equals("Rot13")) {
			tuneEncoding = new TuneEncoding("Rot13");
			return tuneEncoding;
		}
		else if(command.equals("AtBash")) {
			tuneEncoding = new TuneEncoding("AtBash");
			return tuneEncoding;
		}
		else if(command.equals("Edit")) {
			editDocument =  EditDocument.getInstance();
			return editDocument;
		}
		else if(command.equals("Tune Audio")) {
			tuneAudio = new TuneAudio();
			return tuneAudio;
		}
		else if(command.equals("Play")) {
			replayCommand = new ReplayCommand();
			return replayCommand;
		}
	
		return null;
		
	}

}

package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Document;
import view.Text2SpeechEditorView;

public class DocumentToSpeech implements ActionListener {
	private ActionEvent event;
	private String type;
	private int index;

	public DocumentToSpeech() {
		type = "normal";
	}
	public DocumentToSpeech(String t) {
		if(t.equals("Reversed Contents"))
				type = "Reversed Contents";
		else if(t.equals("Encoded Contents"))
			type = "Encoded Contents";
		else
			type = "normal";
	}
	
	
	public boolean saveBeforePlay(Text2SpeechEditorView w){
		SaveDocument save = new SaveDocument();
		if(w.getCurrentDocument().getPath()==null) {
			 boolean b = save.saveAsDocumentView();
			 return b;
		}
		else {
			save.save(w);
		}
		return true;
		
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int i) {
		index = i;
	}

	public void actionPerformed(ActionEvent actionEvent) {
		Text2SpeechEditorView w = Text2SpeechEditorView.getInstance();
		DocumentToSpeech ds = new DocumentToSpeech(type);
		ReplayManager m = ReplayManager.getInstance();
		if(!m.getPlay()) {
			ds.setIndex(w.getIndex());
			ds.documentToSpeech() ;
			if(m.getRec()) {
				m.getCommandsList().add(ds);
				m.getEventList().add(actionEvent);
			}
		}
		else {
			documentToSpeech();
		}
	}
	
	
	public void documentToSpeech() {
		//get the current Text2SpeechEditorView instance  
		Text2SpeechEditorView w = Text2SpeechEditorView.getInstance();

		//get current document and call playContents that implements in model.Document class
		if(w.getCurrentDocument()!=null) {
			index =w.getTabbedPane().getSelectedIndex();
			//save document before play
			if(saveBeforePlay(w)) {
				if(type.equals("Reversed Contents")) {
					w.getDocList().get(index).playReverseContents();
					System.out.println("reversed");
				}
				else if(type.equals("Encoded Contents")) {
					w.getDocList().get(index).playEncodedContents();
					System.out.println("encoded");
				}
				else if(type.equals("normal")) {
					w.getDocList().get(index).playContents();
					System.out.println("normal");
				}
			}
		}
	}

}

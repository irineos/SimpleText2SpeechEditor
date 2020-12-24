package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

import model.Document;
import view.Text2SpeechEditorView;

public class LineToSpeech implements ActionListener {
	private String type;
	private int line;
	private int index;
	
	public LineToSpeech() {
		type="normal";
	}
	public LineToSpeech(String t) {
		if(t.equals("Reversed Line"))
			type = "Reversed Line";
		else if(t.equals("Encoded Line"))
			type = "Encoded Line";
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
		LineToSpeech ls = new LineToSpeech(type);
		ReplayManager m = ReplayManager.getInstance();
		if(!m.getPlay()) {
			ls.setIndex(w.getIndex());
			ls.lineToSpeech() ;
			if(m.getRec()) {
				m.getCommandsList().add(ls);
				m.getEventList().add(actionEvent);
			}
		}
		else {
			lineToSpeech();
		}
	}
	
	public void lineToSpeech() {
		ReplayManager m = ReplayManager.getInstance();
		Text2SpeechEditorView w = Text2SpeechEditorView.getInstance();
		
		saveBeforePlay(w);
		
		if(!m.getPlay() && w.getCurrentDocument()!=null) {
			JTextArea textArea = w.getT();
			try {
				//get the number of the line where is the cursor
				line = textArea.getLineOfOffset(textArea.getCaretPosition());
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
		if(w.getCurrentDocument()!=null) {
			
			if(line>=0 && line<=w.getCurrentDocument().getContentsList().size()-1) {
			//get current document and call playLine(int) that implements in model.Document class
				index =w.getTabbedPane().getSelectedIndex();
				//save document before play
			
				if(type.equals("Reversed Line"))
					w.getDocList().get(index).playReverseLine(line);
				else if(type.equals("Encoded Line"))
					w.getDocList().get(index).playEncodedLine(line);
				else if(type.equals("normal"))
					w.getDocList().get(index).playLine(line);
				
			}	
		}
	}

}

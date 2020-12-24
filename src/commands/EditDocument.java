package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.ButtonTabComponent;
import view.Text2SpeechEditorView;

public class EditDocument implements ActionListener {
	int index;
	private  boolean[] pressed;
	private static EditDocument editDocument;

	public EditDocument() {
		pressed = new boolean[1000];
		Arrays.fill(pressed, false);
	}
	
	public void setPressed(boolean pressed) {
		Text2SpeechEditorView w = Text2SpeechEditorView.getInstance();
		index = w.getIndex();
		this.pressed[index]=pressed;
	}
	public static EditDocument getInstance() {
		if(editDocument==null)
			editDocument=new EditDocument();
		return editDocument;
	}
	
	
	public void edit(Text2SpeechEditorView w) {	
		String text = w.getT().getText();
		w.getCurrentDocument().setContents(text);	
	}
	
	public void checkChanges(Text2SpeechEditorView w) {
		ButtonTabComponent btc = (ButtonTabComponent) w.getTabbedPane().getTabComponentAt(w.getIndex());
		w.getT().getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void insertUpdate(DocumentEvent e) {	
				btc.setIcon("resources/unsaved.png");	
			}
	
			@Override
			public void removeUpdate(DocumentEvent e) {
				btc.setIcon("resources/unsaved.png");
			}
	
			@Override
			public void changedUpdate(DocumentEvent e) {
				btc.setIcon("resources/unsaved.png");	
			}
			
		});         
	}
	
	

	
	public void actionPerformed(ActionEvent actionEvent) {
		Text2SpeechEditorView w = Text2SpeechEditorView.getInstance();
		checkChanges(w);
		index = w.getIndex();
		
		w.getTabbedPane().addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	            index = w.getTabbedPane().getSelectedIndex();
	        }
	    });
		
		if(w.getCurrentDocument()!=null) {
			pressed[index]=!pressed[index];
			if(pressed[index]) {
				w.getT().setEditable(pressed[index]);
				
			}
			else {
				edit(w);
				w.getT().setEditable(pressed[index]);
			}
		}
		
	}
	
	
	

}

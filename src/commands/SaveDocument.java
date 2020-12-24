package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Document;
import view.ButtonTabComponent;
import view.Text2SpeechEditorView;

public class SaveDocument implements ActionListener {
	//true if you click Save ,false if you click Save As
	private boolean saved;

	
	public SaveDocument() {
		saved = false;
	}
	
	public SaveDocument(String s) {
		if(s.equals("Save")) {
			saved = true;	
		}
	}

	
	public void actionPerformed(ActionEvent actionEvent) {
		
		Text2SpeechEditorView w = Text2SpeechEditorView.getInstance();
		ReplayManager m = ReplayManager.getInstance();
		
		if(w.getCurrentDocument()!=null) {
			if(saved && w.getCurrentDocument().getPath()!=null)
				save(w);
			else
				saveAsDocumentView();
		}
		if(m.getRec()) {
			m.getCommandsList().add(this);
			m.getEventList().add(actionEvent);
		}
	}
	
	
	public void save(Text2SpeechEditorView w) {
		EditDocument edit = EditDocument.getInstance();
		BufferedWriter outFile = null;
		File file = new File(w.getCurrentDocument().getPath());
	      try {
	    	 
	         outFile = new BufferedWriter(new FileWriter(file));

	         outFile.write(w.getT().getText());

	      } catch (IOException ex) {
	         ex.printStackTrace();
	      } finally {
	         if (outFile != null) {
	            try {
	               outFile.close();
	            } catch (IOException e) {}
	         }
	      }
		  SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		  Date d = new Date();
		 
		  w.getF().setTitle(w.getCurrentDocument().getAuthor() + "-"+w.getCurrentDocument().getTitle() + " " + date.format(d));
		  w.getCurrentDocument().setDate(date.format(d));
		  
	      edit.edit(w);
		  
		  //change tab icon
		  int index = w.getTabbedPane().getSelectedIndex();
		  ButtonTabComponent btc = (ButtonTabComponent) w.getTabbedPane().getTabComponentAt(index);
		  btc.setIcon("resources/save.png");
			
	}
	
	
	
	public boolean saveAsDocumentView() {
		  Text2SpeechEditorView w = Text2SpeechEditorView.getInstance();
		
	      FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Text File", "txt");
	      final JFileChooser saveAsFileChooser = new JFileChooser("./");
	      saveAsFileChooser.setApproveButtonText("Save");
	      saveAsFileChooser.setFileFilter(extensionFilter);
	      int actionDialog = saveAsFileChooser.showSaveDialog(w.getF());
	      
	      
	      
	      if (actionDialog != JFileChooser.APPROVE_OPTION) {
	         return false;
	      }

	      // !! File fileName = new File(SaveAs.getSelectedFile() + ".txt");
	      File file = saveAsFileChooser.getSelectedFile();
	      if (!file.getName().endsWith(".txt")) {
	         file = new File(file.getAbsolutePath() + ".txt");
	      }      
	      w.getCurrentDocument().setPath(file.getAbsolutePath());
	      
	      save(w);
	      
		  
		  return true;
	  }
	
	

}

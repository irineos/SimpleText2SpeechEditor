package commands;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Document;
import view.ButtonTabComponent;
import view.CaretAction;
import view.Text2SpeechEditorView;

public class OpenDocument implements ActionListener {
	private ActionEvent event;
	private File file;

	public OpenDocument() {
		
	}

	
	public void actionPerformed(ActionEvent actionEvent) {
		OpenDocument o = new OpenDocument();
		
		Text2SpeechEditorView w = Text2SpeechEditorView.getInstance();
		ReplayManager m = ReplayManager.getInstance();
		if(!m.getPlay()) {
			o.openDocumentView() ;
			if(m.getRec()) {
				m.getCommandsList().add(o);
				m.getEventList().add(actionEvent);
			}
		}
		else {
			o.open(w,file);
		}
	}
	
	public void openDocumentView() {
		//open file browser window on working directory
		JFileChooser fc = new JFileChooser("./");
		//option for showing only txt files
		fc.addChoosableFileFilter(new FileNameExtensionFilter("*.txt","txt","min"));
		
		
	    Text2SpeechEditorView w = Text2SpeechEditorView.getInstance();

		
	    int returnVal = fc.showOpenDialog(w.getF());
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
	        file = fc.getSelectedFile();
	        
	        open(w,file);
	    }
	    else {
	        System.out.println("Operation is CANCELLED :(");
	    }
		
	
	}
	
	public void open(Text2SpeechEditorView w,File file) {
		boolean b = false;
		int temp = 0;
        for(int i=0;i<w.getTabbedPane().getTabCount();i++) {
	        if(w.getTabbedPane().getTitleAt(i).equals(file.getName())){
	        	b=true;
	        	temp=i;
	        }
	        
		}
        if(b)
        	w.getTabbedPane().setSelectedIndex(temp);
        
        else{
		
		
			JTextArea t = new JTextArea();
			t.addCaretListener(new CaretAction());
			w.getTList().add(t);
			
			if(w.getTheme()) {
				t.setBackground(new Color(10, 10, 20));
		        t.setForeground(new Color(250, 250, 250));
			}
			
			JScrollPane sp = new JScrollPane(t);
			sp.setVisible(true);
			w.getTabbedPane().add(sp);
			int index = w.getTList().size()-1;
			w.getTabbedPane().setSelectedIndex(index);
			w.getTabbedPane().setTabComponentAt(index, new ButtonTabComponent(w.getTabbedPane()));
			
			
			
			
	       
			
			BufferedReader input = null;
			try {
		          //write file contents in JTextArea
		        input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		        t.read(input, "READING FILE :-)");
		         
		    } 
			catch (Exception e) {
		          e.printStackTrace();
		    }
		        
			  //create date format (how will the date appears in string)
			SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Document doc = new Document(file.getName(),"",date.format(file.lastModified()),file.getAbsolutePath());
			w.getDocList().add(doc);
			  //create a new Document and set the current document to it
			w.setCurrentDocument(doc);
			  //set path
			w.getCurrentDocument().setPath(file.getAbsolutePath());
			    
			  //refresh main frame and its title
			w.getF().setTitle(w.getCurrentDocument().getAuthor() + "-"+w.getCurrentDocument().getTitle() + " " + w.getCurrentDocument().getDate());
			w.getTabbedPane().setTitleAt(index, file.getName());
			  
			  //w.getT().setVisible(true);
			t.setEditable(false);
			EditDocument edit = EditDocument.getInstance();
			edit.setPressed(false);
			  //set the current document contents to JTextArea text
		    String text = w.getT().getText();
		      //System.out.println(text);
			w.getCurrentDocument().setContents(text);
			  
			w.getTabbedPane().setVisible(true);
        }
	}
	public void getFile() {
		System.out.println(file.getName());
	}

}

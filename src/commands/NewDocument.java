package commands;
import model.Document;
import view.ButtonTabComponent;
import view.CaretAction;
import view.Text2SpeechEditorView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

public class NewDocument implements ActionListener {
	private String author;
	private String title;
	private String date;
	private String path;
	
	Document doc;

	public NewDocument() {
		
	}

	
	public void actionPerformed(ActionEvent actionEvent) {
		ReplayManager m = ReplayManager.getInstance();
		NewDocument n = new NewDocument();
		if(!m.getPlay()) {
			n.newDocumentView();
			if(m.getRec()) {
				m.getCommandsList().add(n);
				m.getEventList().add(actionEvent);
			}
		}
		else {
			n.createNewDocument(author, title, date, path);
		}
	}
	
	public void createNewDocument(String author,String title,String date,String path) {
		
		
		Text2SpeechEditorView w = Text2SpeechEditorView.getInstance();
		doc = new Document(author,title,date,path);
		w.getDocList().add(doc);
		w.setCurrentDocument(doc);
		
		
		JTextArea t = new JTextArea();
		t.addCaretListener(new CaretAction());
		w.getTList().add(t);
		JScrollPane sp = new JScrollPane(t);
		
		t.setBackground(Color.WHITE);
		t.setVisible(true);
		sp.setVisible(true);
		w.getTabbedPane().add(sp);
		int index = w.getTList().size()-1;
		w.getTabbedPane().setSelectedIndex(index);
		w.getTabbedPane().setTabComponentAt(index, new ButtonTabComponent(w.getTabbedPane()));
		
		
		//set the title of main frame
		w.getF().setTitle(w.getCurrentDocument().getAuthor() + "-"+w.getCurrentDocument().getTitle() + " " + w.getCurrentDocument().getDate());
		 w.getTabbedPane().setTitleAt(index, w.getCurrentDocument().getTitle());
		
		//refresh main frame
		w.getT().setVisible(true);
		w.getT().setText("");
		w.getT().setEditable(false);
		
		EditDocument edit = EditDocument.getInstance();
		edit.setPressed(false);
		w.getF().setVisible(true);
		w.getTabbedPane().setVisible(true);
		
	}
	
	public void newDocumentView() {
		Text2SpeechEditorView w = Text2SpeechEditorView.getInstance();
		//creates the frame that appears when you click New in File menu option
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 430, 250);
		frame.setLocationRelativeTo(w.getF());
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//creates the Author label
		JLabel lblNewLabel = new JLabel("Author");
		lblNewLabel.setBounds(12, 77, 70, 15);
		frame.getContentPane().add(lblNewLabel);
		//creates the Title label
		JLabel lblNewLabel_1 = new JLabel("Title");
		lblNewLabel_1.setBounds(12, 133, 70, 15);
		frame.getContentPane().add(lblNewLabel_1);
		//creates the Author text field
		JTextField authorText = new JTextField();
		authorText.setBounds(100, 75, 114, 19);
		frame.getContentPane().add(authorText);
		authorText.setColumns(10);
		//creates the Title text field
		JTextField titleText = new JTextField();
		titleText.setBounds(100, 131, 114, 19);
		frame.getContentPane().add(titleText);
		titleText.setColumns(10);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.setBounds(177, 179, 117, 25);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			//called when OK button is pressed
			public void actionPerformed(ActionEvent e) {
				//get text from text fields
				author = authorText.getText();
				title = titleText.getText();
				
				//get current date
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date d = new Date();
				date = dateFormat.format(d);
				createNewDocument(author,title,date,null);
				//close the "create New Document" window		
				frame.dispose();
			}
		});
		
		frame.getContentPane().add(btnNewButton);
		//set title for the "create new document pop up window"
		JLabel lblCreateNewDocument = new JLabel("Create new document");
		lblCreateNewDocument.setBounds(150, 12, 156, 33);
		frame.getContentPane().add(lblCreateNewDocument);
		frame.setVisible(true);
	}
	
	
	
	

}

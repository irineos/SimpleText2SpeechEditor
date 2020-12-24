package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ServiceLoader;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.WindowConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;

import commands.CommandsFactory;
import commands.OpenDocument;
import commands.ReplayManager;
import model.Document;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JRadioButtonMenuItem;

public class Text2SpeechEditorView {
	
	private static Text2SpeechEditorView singletonView;
	
	private CommandsFactory commandsFactory;
	private ArrayList<JTextArea> t;
	private JFrame f;
	private ArrayList<Document> currentDocument;
	private JTabbedPane tabbedPane;
	private int index;
	private boolean isDarkTheme = false;
	
	private JLabel rowLabel=new JLabel("Row : ");
	private JLabel colLabel=new JLabel("Col : ");
	
	private JRadioButtonMenuItem rdbtnmntmRot ;
	private JRadioButtonMenuItem rdbtnmntmAtbash;

	public Text2SpeechEditorView() {
		createEditorView();
		
	}
	
	public static Text2SpeechEditorView getInstance() {
		if(singletonView == null)
			singletonView = new Text2SpeechEditorView();
		
		return singletonView;
	}
	
	
	
	
	//get the current document that contains the text of JTextArea t
	public Document getCurrentDocument() {
		if(currentDocument.size()>0) {
			
			if (index != -1) {
				return currentDocument.get(index);
			}
		}
		return null;
	}
	public JRadioButtonMenuItem getRot() {
		return rdbtnmntmRot;
	}
	public JRadioButtonMenuItem getAtBash() {
		return rdbtnmntmAtbash;
	}
	
	public void setCurrentDocument(Document d) {
		
		if (index != -1) {
			currentDocument.set(index, d);
		}
	}
	
	public JTextArea getT() {
		if(t.size()>0) {
			int index=tabbedPane.getSelectedIndex();
			if (index != -1) {
					return t.get(index);
			}
		}
		return null;
	}
	
	public void setT(JTextArea tArea) {
		int index=tabbedPane.getSelectedIndex();
		if (index != -1) {
			t.set(index, tArea);
		}
	}
	public JFrame getF() {
		return f;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setF(JFrame frame) {
		f = frame;
	}
	
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
	public ArrayList<JTextArea> getTList() {
		return t;
	}
	public ArrayList<Document> getDocList() {
		return currentDocument;
	}
	
	public boolean getTheme() {
		return isDarkTheme;
	}
	public void setTheme(boolean b) {
		isDarkTheme = b;
	}
	public JLabel getRowLabel() {
		return rowLabel;
	}
	public JLabel getColLabel() {
		return colLabel;
	}
	
	
	
	public void createEditorView() {
		
		

		//create JFrame
		f = new JFrame();
		f.setTitle("No document");
		f.setBounds(100, 100, 600, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		 JToolBar statusBar=new JToolBar();
	     statusBar.setFloatable(false);
	     
	     
	     
	     JLabel readylabel=new JLabel(" Text To Speech Editor"); 
	     statusBar.add(readylabel);
	    
	     statusBar.add(Box.createHorizontalGlue());
	     
	     statusBar.add(rowLabel);
	     statusBar.add(new JLabel("     "));
	     statusBar.add(colLabel);
	     statusBar.add(new JLabel("     "));
		
		tabbedPane = new JTabbedPane(); 
		
		f.getContentPane().add(statusBar, BorderLayout.SOUTH);
		f.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.setVisible(true);
		
		//create JTextArea that will contain all the text
		t = new ArrayList<JTextArea>();
		
		
		//initialize CommandsFactory()
		commandsFactory = new CommandsFactory();
		currentDocument = new ArrayList<Document>();
		
		//create menu bar and insert it to frame
		JMenuBar menuBar = new JMenuBar();
		
		
		f.setJMenuBar(menuBar);
		
		
		createFileMenu(menuBar);
		createEditMenu(menuBar);
		
		createPlayMenu(menuBar);
		createTuneMenu(menuBar);
		createReplayMenu(menuBar);
		createViewMenu(menuBar);
		
	}
	
	
	public void createFileMenu(JMenuBar menuBar) {
		ActionListener a;
		//create menu "File" button and insert it to menu bar 
		JMenu mnFile = new JMenu("File ");
		mnFile.setMnemonic(KeyEvent.VK_F);
		menuBar.add(mnFile);
		//create menu items and insert them to "File" menu bar option
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.setIcon(new ImageIcon(this.getClass().getResource("resources/new.png")));
		mntmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		
		mnFile.add(mntmNew);
		//get the text of the menu item and call commandsFactory.createCommand(**with this text**)
		a = commandsFactory.createCommand(mntmNew.getText());
		mntmNew.addActionListener(a);
				
				
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.setIcon(new ImageIcon(this.getClass().getResource("resources/open.png")));
		mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		mnFile.add(mntmOpen);
		a = commandsFactory.createCommand(mntmOpen.getText());
		mntmOpen.addActionListener(a);
		
		mnFile.add(new JSeparator());
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mnFile.add(mntmClose);
		mntmClose.addActionListener(event -> {
			if(tabbedPane.getTabCount() > 0) {
				int i = tabbedPane.getSelectedIndex();
	            if (index != -1) {
	                tabbedPane.remove(i);
	                currentDocument.remove(i);
	                t.remove(i);
	            }
			}
		});
		
		JMenuItem mntmCloseAll = new JMenuItem("CloseAll");
		mnFile.add(mntmCloseAll);
		mntmCloseAll.addActionListener(e -> {
			tabbedPane.removeAll();
			currentDocument.clear();
			t.clear();
			
		});
		
		mnFile.add(new JSeparator());
		
				
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setIcon(new ImageIcon(this.getClass().getResource("resources/save.png")));
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		mnFile.add(mntmSave);
		a = commandsFactory.createCommand(mntmSave.getText());
		mntmSave.addActionListener(a);
		
		
		JMenuItem mntmSaveAs = new JMenuItem("Save As");
		mntmSaveAs.setIcon(new ImageIcon(this.getClass().getResource("resources/saveas.png")));
		mnFile.add(mntmSaveAs);
		a = commandsFactory.createCommand(mntmSaveAs.getText());
		mntmSaveAs.addActionListener(a);
		
		
		JMenuItem mntmSaveAll = new JMenuItem("Save All");
		mntmSaveAll.setIcon(new ImageIcon(this.getClass().getResource("resources/saveAll.png")));
		mnFile.add(mntmSaveAll);
		mntmSaveAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tabbedPane.getTabCount()>0) {
					int temp = tabbedPane.getSelectedIndex();
					ActionListener a = commandsFactory.createCommand(mntmSave.getText());
					for(int i=0;i<tabbedPane.getTabCount();i++) {
						tabbedPane.setSelectedIndex(i);
						a.actionPerformed(e);
					}
					tabbedPane.setSelectedIndex(temp);
				}
				
			}
		});
		
		
		mnFile.add(new JSeparator());
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setIcon(new ImageIcon(this.getClass().getResource("resources/exit.png")));
		mnFile.add(mntmExit);
		mntmExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (JOptionPane.showConfirmDialog(new JFrame(),"Do you Want to Exit","Exit",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
                        System.exit(0);
            }
        });
	}
	
	public void createPlayMenu(JMenuBar menuBar) {
		ActionListener a;
		//create menu "Play" option and insert it to menu bar 
		JMenu mnPlay = new JMenu("Play ");
		menuBar.add(mnPlay);
		//create menu items and insert them to "Play" menu bar option
		JMenuItem mntmPlayCon = new JMenuItem("Contents");
		mnPlay.add(mntmPlayCon);
		a = commandsFactory.createCommand(mntmPlayCon.getText());
		mntmPlayCon.addActionListener(a);
				
		JMenuItem mntmPlayLine = new JMenuItem("Line");
		mnPlay.add(mntmPlayLine);
		a = commandsFactory.createCommand(mntmPlayLine.getText());
		mntmPlayLine.addActionListener(a);
				
		JMenuItem mntmPlayRevCon = new JMenuItem("Reversed Contents");
		mnPlay.add(mntmPlayRevCon);
		a = commandsFactory.createCommand(mntmPlayRevCon.getText());
		mntmPlayRevCon.addActionListener(a);
				
		JMenuItem mntmPlayRevLine = new JMenuItem("Reversed Line");
		mnPlay.add(mntmPlayRevLine);
		a = commandsFactory.createCommand(mntmPlayRevLine.getText());
		mntmPlayRevLine.addActionListener(a);
				
		JMenuItem mntmPlayEncCon = new JMenuItem("Encoded Contents");
		mnPlay.add(mntmPlayEncCon);
		a = commandsFactory.createCommand(mntmPlayEncCon.getText());
		mntmPlayEncCon.addActionListener(a);
				
		JMenuItem mntmPlayEncLine = new JMenuItem("Encoded Line");
		mnPlay.add(mntmPlayEncLine);
		a = commandsFactory.createCommand(mntmPlayEncLine.getText());
		mntmPlayEncLine.addActionListener(a);
	}
	
	public void createEditMenu(JMenuBar menuBar) {
		ActionListener a;
		JMenu mnEdit = new JMenu("Edit ");
		mnEdit.setMnemonic(KeyEvent.VK_E);
		menuBar.add(mnEdit);
		JMenuItem mntmEdit = new JMenuItem("Edit");
		mntmEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		mnEdit.add(mntmEdit);
		a = commandsFactory.createCommand(mntmEdit.getText());
		mntmEdit.addActionListener(a);
	}
	
	public void createTuneMenu(JMenuBar menuBar) {
		ActionListener a;
		JMenu mnTune = new JMenu("Tune ");
		menuBar.add(mnTune);
		
		JMenuItem mntmTuneAudio = new JMenuItem("Tune Audio");
		mnTune.add(mntmTuneAudio);
		a = commandsFactory.createCommand(mntmTuneAudio.getText());
		mntmTuneAudio.addActionListener(a);
		
		JMenu mnTuneEncoding = new JMenu("Tune Encoding");
		mnTune.add(mnTuneEncoding);
		
		rdbtnmntmRot = new JRadioButtonMenuItem("Rot13");
		mnTuneEncoding.add(rdbtnmntmRot);
		a = commandsFactory.createCommand(rdbtnmntmRot.getText());
		rdbtnmntmRot.addActionListener(a);
		rdbtnmntmRot.setSelected(true);
		
		rdbtnmntmAtbash = new JRadioButtonMenuItem("AtBash");
		mnTuneEncoding.add(rdbtnmntmAtbash);
		a = commandsFactory.createCommand(rdbtnmntmAtbash.getText());
		rdbtnmntmAtbash.addActionListener(a);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnmntmRot);
		group.add(rdbtnmntmAtbash);
	}
	
	public void createReplayMenu(JMenuBar menuBar) {
		ActionListener a;
		JMenu mnReplay = new JMenu("Replay ");
		menuBar.add(mnReplay);
		
		JMenuItem mntmStartRec = new JMenuItem("Start Recording");
		mntmStartRec.setIcon(new ImageIcon(this.getClass().getResource("resources/recording.png")));
		mnReplay.add(mntmStartRec);
		mntmStartRec.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				commandsFactory.setRecording(true);
				
			}	
		});
		
		JMenuItem mntmStopRec = new JMenuItem("Stop Recording");
		mntmStopRec.setIcon(new ImageIcon(this.getClass().getResource("resources/stop_recording.png")));
		mnReplay.add(mntmStopRec);
		mntmStopRec.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				commandsFactory.setRecording(false);
				
			}	
		});
		
		JMenuItem mntmPlay = new JMenuItem("Play");
		mntmPlay.setIcon(new ImageIcon(this.getClass().getResource("resources/play.png")));
		mnReplay.add(mntmPlay);
		a = commandsFactory.createCommand(mntmPlay.getText());
		mntmPlay.addActionListener(a);
		
	}
	
	public void createViewMenu(JMenuBar menuBar){
		JMenu menu = new JMenu("Appearance");
		
		
		 UIManager.put( "control", new Color( 128, 128, 128) );
		 UIManager.put( "info", new Color(128,128,128) );
		 UIManager.put( "nimbusBase", new Color( 18, 30, 49) );
		 UIManager.put( "nimbusAlertYellow", new Color( 248, 187, 0) );
		 UIManager.put( "nimbusDisabledText", new Color( 128, 128, 128) );
		 UIManager.put( "nimbusFocus", new Color(115,164,209) );
		 UIManager.put( "nimbusGreen", new Color(176,179,50) );
		 UIManager.put( "nimbusInfoBlue", new Color( 66, 139, 221) );
		 UIManager.put( "nimbusLightBackground", new Color( 18, 30, 49) );
		 UIManager.put( "nimbusOrange", new Color(191,98,4) );
		 UIManager.put( "nimbusRed", new Color(169,46,34) );
		 UIManager.put( "nimbusSelectedText", new Color( 255, 255, 255) );
		 UIManager.put( "nimbusSelectionBackground", new Color( 104, 93, 156) );
		 UIManager.put( "text", new Color( 230, 230, 230) );
		
  
        UIManager.LookAndFeelInfo[] lookAndFeels = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo lookAndFeelInfo : lookAndFeels) {
        	JMenuItem item;
        	if(!lookAndFeelInfo.getName().equals("Nimbus") && !lookAndFeelInfo.getName().equals("Metal")){
        		item = new JMenuItem(lookAndFeelInfo.getName());
        	}
        	else if(lookAndFeelInfo.getName().equals("Metal")) {
        		item = new JMenuItem("Default");
        	}
        	else {
        		item = new JMenuItem("Dark Theme");
        	}
            item.addActionListener(event -> {
                try {
                    // Set the look and feel for the frame and update the UI
                    // to use a new selected look and feel.
                    UIManager.setLookAndFeel(lookAndFeelInfo.getClassName());
                    SwingUtilities.updateComponentTreeUI(f);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            menu.add(item);
            if(item.getText().equals("Dark Theme"))
            	menu.add(new JSeparator());
        }
        JMenu viewMenu = new JMenu("Window ");
        viewMenu.add(menu);
        
        menuBar.add(viewMenu);
	}
	
	
	 
	public void getTabIndex() {
		tabbedPane.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	            index = tabbedPane.getSelectedIndex();
	            if(index>-1)
	            	f.setTitle(tabbedPane.getTitleAt(index));
	            else
	            	f.setTitle("No Document");
	        }
	        
	    });
		
	}

	
	

	
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Text2SpeechEditorView window = Text2SpeechEditorView.getInstance();
					window.f.setVisible(true);
					window.getTabIndex();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

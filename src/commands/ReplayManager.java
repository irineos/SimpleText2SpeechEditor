package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import view.Text2SpeechEditorView;

public class ReplayManager {
	
	private ArrayList<ActionListener> commandsList;
	private ArrayList<ActionEvent> eventList;
	private ArrayList<Integer> indexList;
	
	private static ReplayManager replay;
	private boolean rec;
	private boolean play;

	public ReplayManager() {
		play = false;
		rec = false;
		commandsList = new ArrayList<ActionListener>();
		eventList = new ArrayList<ActionEvent>();
		
	}
	
	public void replay() {
		Text2SpeechEditorView w = Text2SpeechEditorView.getInstance();
		play = true;
		for(int i=0;i<commandsList.size();i++) {
			System.out.println(commandsList.size());
			ActionListener action = commandsList.get(i);
			if(action instanceof DocumentToSpeech) {
				w.getTabbedPane().setSelectedIndex(((DocumentToSpeech) action).getIndex());
			}
			if(action instanceof LineToSpeech) {
				w.getTabbedPane().setSelectedIndex(((LineToSpeech) action).getIndex());
			}
			ActionEvent event = eventList.get(i);
			action.actionPerformed(event);
			System.out.println(commandsList.get(i).toString());
		}
		play = false;
	}
	
	public ArrayList<ActionListener> getCommandsList() {
		return commandsList;
	}
	public ArrayList<ActionEvent> getEventList() {
		return eventList;
	}
	public ArrayList<Integer> getIndexList() {
		return indexList;
	}

	
	public static ReplayManager getInstance() {
		if(replay==null)
			replay = new ReplayManager();
		return replay;
	}
	public void setRec(boolean rec) {
		if(rec) {
			commandsList.clear();
			eventList.clear();
		}
			
		this.rec = rec;
	}
	public boolean getRec() {
		return rec;
	}
	public boolean getPlay() {
		return play;
	}
}

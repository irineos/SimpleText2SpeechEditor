package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplayCommand implements ActionListener {

	private ReplayManager replayManager;
	
	public ReplayCommand() {
		replayManager = ReplayManager.getInstance();
	}

	
	public void actionPerformed(ActionEvent actionEvent) {
		if(replayManager.getCommandsList().size()>0) {
			replayManager.replay();
			System.out.println("play button pressed");
		}
	}

}

package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import encodingstrategies.EncodingStrategy;
import encodingstrategies.StrategiesFactory;
import model.Line;
import view.Text2SpeechEditorView;

public class TuneEncoding implements ActionListener {
	private String type;
	private StrategiesFactory strategy;
	
	public TuneEncoding(String enc) {
		if(enc.equals("Rot13"))
			type="Rot13";
		else if(enc.equals("AtBash"))
			type="AtBash";
	}

	
	public void actionPerformed(ActionEvent actionEvent) {
		Text2SpeechEditorView w = Text2SpeechEditorView.getInstance();
		TuneEncoding t = new TuneEncoding(type);
		ReplayManager m = ReplayManager.getInstance();

		if(type.equals("Rot13")) {
			t.strategy=new StrategiesFactory("Rot13");
			w.getRot().setSelected(true);
		}
		if(type.equals("AtBash")) {
			t.strategy=new StrategiesFactory("AtBash");
			w.getAtBash().setSelected(true);
		}
		if(m.getRec()) {
			m.getCommandsList().add(t);
			m.getEventList().add(actionEvent);
		}
			
	}

}

package encodingstrategies;

import model.Document;
import model.Line;
import view.Text2SpeechEditorView;

public class StrategiesFactory {
	private EncodingStrategy ret;

	public StrategiesFactory(String encoding) {
		Text2SpeechEditorView w = Text2SpeechEditorView.getInstance();
		Document doc = w.getCurrentDocument();
		if(encoding.equals("Rot13")) {
				ret = new Rot13Encoding();
				if(doc!=null)
					doc.tuneEncodingStrategy(ret);
				System.out.println("rot13");
		}
		else if(encoding.equals("AtBash")) {
			ret = new AtBashEncoding();
			if(doc!=null)
				doc.tuneEncodingStrategy(ret);
			System.out.println("atbash");
		}
		
	}

	

}

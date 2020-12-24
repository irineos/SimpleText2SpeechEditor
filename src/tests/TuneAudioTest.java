package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import commands.NewDocument;
import commands.TuneAudio;
import encodingstrategies.AtBashEncoding;
import encodingstrategies.EncodingStrategy;
import model.Document;
import view.Text2SpeechEditorView;

class TuneAudioTest {

	
	@Test
	void testVolume() {
		//create a new document
		NewDocument newDocument = new NewDocument();
		newDocument.createNewDocument("", "", "", "");
		//test if the new document contents equals the current document contents
		Text2SpeechEditorView w = Text2SpeechEditorView.getInstance();
		Document doc = w.getCurrentDocument();	
		
		doc.setAPI("fakettsapi");
		
		doc.setVolume(1.0f);
		
				
		Assert.assertEquals(doc.getVolume(),1.0f,0.0f);
	}
	@Test
	void testPitch() {
		//create a new document
		NewDocument newDocument = new NewDocument();
		newDocument.createNewDocument("", "", "", "");
		//test if the new document contents equals the current document contents
		Text2SpeechEditorView w = Text2SpeechEditorView.getInstance();
		Document doc = w.getCurrentDocument();	
		
		doc.setAPI("fakettsapi");
		
		
		doc.setPitch(0.0f);
		
				
		Assert.assertEquals(doc.getPitch(),0.0f,0.0f);
	}
	@Test
	void testRate() {
		//create a new document
		NewDocument newDocument = new NewDocument();
		newDocument.createNewDocument("", "", "", "");
		//test if the new document contents equals the current document contents
		Text2SpeechEditorView w = Text2SpeechEditorView.getInstance();
		Document doc = w.getCurrentDocument();	
		
		doc.setAPI("fakettsapi");
		
		
		doc.setRate(10.0f);
		
				
		Assert.assertEquals(doc.getRate(),10.0f,0.0f);
	}
	

}

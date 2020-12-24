package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import commands.NewDocument;
import view.Text2SpeechEditorView;

class NewDocumentTest {

	@Test
	void test() {
		//create a new document
		NewDocument newDocument = new NewDocument();
		newDocument.createNewDocument("", "", "", "");
		//test if the new document contents equals the current document contents
		Text2SpeechEditorView w = Text2SpeechEditorView.getInstance();
		assertEquals(w.getCurrentDocument().getContents(),"");
	}

}

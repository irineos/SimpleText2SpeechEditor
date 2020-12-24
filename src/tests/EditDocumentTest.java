package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import commands.EditDocument;
import commands.NewDocument;
import view.Text2SpeechEditorView;

class EditDocumentTest {

	@Test
	void test() {
		Text2SpeechEditorView w = Text2SpeechEditorView.getInstance();
		
		NewDocument newDocument = new NewDocument();
		newDocument.createNewDocument("", "", "", "");
		
		EditDocument editDocument = new EditDocument();
		editDocument.setPressed(true);
		
		w.getT().setText("Hello World" + "\n" + "hi");
		
		editDocument.edit(w);
		
		editDocument.setPressed(false);
		
		
		String text = w.getCurrentDocument().getContents();
		
		assertEquals(text,"Hello World" + "\n" + "hi");
		
		
	}

}

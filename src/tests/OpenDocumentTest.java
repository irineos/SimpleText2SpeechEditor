package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import commands.OpenDocument;
import view.Text2SpeechEditorView;

class OpenDocumentTest {

	@Test
	void test() {
		String text;
		OpenDocument openDocument = new OpenDocument();
		Text2SpeechEditorView w = Text2SpeechEditorView.getInstance();
		File file = new File("./hello.txt");
		openDocument.open(w, file);
		
		String currentDocumentText=w.getCurrentDocument().getContents();
		
		StringBuilder contentBuilder = new StringBuilder();
	    try (BufferedReader br = new BufferedReader(new FileReader(file))) 
	    {
	 
	        String sCurrentLine;
	        while ((sCurrentLine = br.readLine()) != null) 
	        {
	            contentBuilder.append(sCurrentLine.trim()+"\n");
	        }
	    } 
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	    text = contentBuilder.toString();
	    assertEquals(text,currentDocumentText+"\n");
	}

}

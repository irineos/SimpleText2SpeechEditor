package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;

import commands.NewDocument;
import commands.SaveDocument;
import view.Text2SpeechEditorView;

class SaveDocumentTest {

	@Test
	void test(){
		String text = null;
		//create a new document
		NewDocument newDocument = new NewDocument();
		newDocument.createNewDocument("", "", "", "");
		//set the contents and the path of the current document and save it
		SaveDocument saveDocument = new SaveDocument();
		Text2SpeechEditorView w = Text2SpeechEditorView.getInstance();
		w.getCurrentDocument().setContents("Hello World");
		w.getT().setText(w.getCurrentDocument().getContents());
		w.getCurrentDocument().setPath("./hello.txt");
		saveDocument.save(w);
		//open the file that has been saved to disk and read its contents
		File file = new File("./hello.txt");
		StringBuilder contentBuilder = new StringBuilder();
	    try (BufferedReader br = new BufferedReader(new FileReader(file))) 
	    {
	 
	        String sCurrentLine;
	        while ((sCurrentLine = br.readLine()) != null) 
	        {
	            contentBuilder.append(sCurrentLine).append("\n");
	        }
	    } 
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	    text = contentBuilder.toString();
	    System.out.println(w.getCurrentDocument().getContents());
		assertEquals(w.getCurrentDocument().getContents()+"\n",text);
	}

}

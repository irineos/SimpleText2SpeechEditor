package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

import commands.NewDocument;
import encodingstrategies.AtBashEncoding;
import text2speechapis.TextToSpeechAPI;
import text2speechapis.TextToSpeechAPIFactory;
import view.Text2SpeechEditorView;

class DocumentToSpeechTest {
	
	@Test
	void contents() {
		NewDocument newDocument = new NewDocument();
		newDocument.createNewDocument("", "", "", "");
		
		Text2SpeechEditorView w = Text2SpeechEditorView.getInstance();
		
		
		w.getCurrentDocument().setAPI("fakettsapi");
		
		w.getCurrentDocument().setContents("Hello World\n"
											+ "hello");
		
		ByteArrayOutputStream test = new ByteArrayOutputStream();
		PrintStream PS = new PrintStream(test);
		PrintStream old = System.out;
		System.setOut(PS);
	
		w.getCurrentDocument().playContents();
	
		assertEquals(test.toString(),w.getCurrentDocument().getContents()+"\n");//because of System.out.println() that adds a "\n" 


	}
	@Test
	void reversedContents() {
		NewDocument newDocument = new NewDocument();
		newDocument.createNewDocument("", "", "", "");
		
		Text2SpeechEditorView w = Text2SpeechEditorView.getInstance();
		
		
		w.getCurrentDocument().setAPI("fakettsapi");
		
		w.getCurrentDocument().setContents("Hello World \n"
											+ "hello");
		
		ByteArrayOutputStream test = new ByteArrayOutputStream();
		PrintStream PS = new PrintStream(test);
		PrintStream old = System.out;
		System.setOut(PS);
	
		w.getCurrentDocument().playReverseContents();
		//System.out.println inserts "\n" in the end
		assertEquals(test.toString(),"hello\n" + "World Hello\n");

	}
	
	@Test
	void rot13Contents() {
		NewDocument newDocument = new NewDocument();
		newDocument.createNewDocument("", "", "", "");
		
		Text2SpeechEditorView w = Text2SpeechEditorView.getInstance();
		
		
		w.getCurrentDocument().setAPI("fakettsapi");
		
		w.getCurrentDocument().setContents("Hello World \n"
											+ "hello");
		
		ByteArrayOutputStream test = new ByteArrayOutputStream();
		PrintStream PS = new PrintStream(test);
		PrintStream old = System.out;
		System.setOut(PS);
	
		w.getCurrentDocument().playEncodedContents();
		//System.out.println inserts "\n" in the end
		assertEquals(test.toString(),"Uryyb Jbeyq\n" + "uryyb\n");

	}
	
	@Test
	void atBashContents() {
		NewDocument newDocument = new NewDocument();
		newDocument.createNewDocument("", "", "", "");
		
		Text2SpeechEditorView w = Text2SpeechEditorView.getInstance();
		
		
		w.getCurrentDocument().setAPI("fakettsapi");
		
		w.getCurrentDocument().setContents("Hello World \n"
											+ "hello");
		
		ByteArrayOutputStream test = new ByteArrayOutputStream();
		PrintStream PS = new PrintStream(test);
		PrintStream old = System.out;
		System.setOut(PS);
		
		w.getCurrentDocument().tuneEncodingStrategy(new AtBashEncoding());
		w.getCurrentDocument().playEncodedContents();
		//System.out.println inserts "\n" in the end
		assertEquals(test.toString(),"Svool Dliow\n" + "svool\n");

	}

}

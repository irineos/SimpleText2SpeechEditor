package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import commands.NewDocument;
import encodingstrategies.AtBashEncoding;
import view.Text2SpeechEditorView;

class LineToSpeechTest {

	@Test
	void line() {
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
	
		w.getCurrentDocument().playLine(0);
	
		assertEquals(test.toString(),"Hello World\n");


	}
	@Test
	void reversedLine() {
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
	
		w.getCurrentDocument().playReverseLine(0);
		//System.out.println inserts "\n" in the end
		assertEquals(test.toString(),"World Hello\n");

	}
	
	@Test
	void rot13Line() {
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
	
		w.getCurrentDocument().playEncodedLine(0);
	
		assertEquals(test.toString(),"Uryyb Jbeyq\n");


	}
	
	@Test
	void atBashLine() {
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
		w.getCurrentDocument().playEncodedLine(0);
	
		assertEquals(test.toString(),"Svool Dliow\n");


	}

}

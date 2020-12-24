package view;

import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;

public class CaretAction implements CaretListener
{
	Text2SpeechEditorView w = Text2SpeechEditorView.getInstance();
    public int getRow(int pos,JTextArea textArea)
    {
        int rn=(pos==0) ? 1:0;
        try
        {
            int offs=pos;
            while(offs>0)
            {
                offs=Utilities.getRowStart(textArea, offs)-1;
                rn++;
            }
        }
        catch(BadLocationException e){ e.printStackTrace();}

        return rn;
    }

    public int getColumn(int pos,JTextArea textArea)
    {
        try
        {
            return pos-Utilities.getRowStart(textArea, pos)+1;
        }
        catch (BadLocationException e) {e.printStackTrace();  }

        return -1;
    }

    @Override
    public void caretUpdate(CaretEvent evt)
    {
    	JTextArea textArea=(JTextArea)evt.getSource();
        int row = getRow(evt.getDot(), textArea);
        int col = getColumn(evt.getDot(), textArea);
        w.getRowLabel().setText("Row : "+row);
        w.getColLabel().setText("Col : "+col);
    }
}




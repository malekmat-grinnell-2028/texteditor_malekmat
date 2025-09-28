package edu.grinnell.csc207.texteditor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GapBufferTests {
    @Test
    public void testInsert(){
        GapBuffer str = new GapBuffer();
        str.insert('h');
        str.insert('i');
        // str.insert('!');
        assertEquals('h', str.getChar(0));
    }

    @Test
    public void testToString(){
        GapBuffer str = new GapBuffer();
        str.insert('h');
        str.insert('i');
        str.insert('!');
        assertEquals("hi!", str.toString());
    }
}

package edu.grinnell.csc207.texteditor;

import static org.junit.jupiter.api.Assertions.assertEquals;




import org.junit.jupiter.api.Test;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;

public class SimpleStringBufferTests {

    @Test
    public void testInsertChar(){
        SimpleStringBuffer str = new SimpleStringBuffer();
        str.insert('h');
        str.insert('i');
        str.insert('!');
        assertEquals('i', str.getChar(1));
    }

    @Test
    public void testDelete(){
        SimpleStringBuffer str = new SimpleStringBuffer();
        str.insert('h');
        str.insert('i');
        str.insert('!');
        str.moveRight();
        str.moveRight();
        str.delete();
        assertEquals('h', str.getChar(0));
    }

    @Property
    public void testStringBufferChars(@ForAll @IntRange(min = 32, max = 126) int ch0,@ForAll @IntRange(min = 32, max = 126) int ch1, @ForAll @IntRange(min = 32, max = 126) int ch2) {
        SimpleStringBuffer str = new SimpleStringBuffer();
        str.insert((char) ch0);
        str.insert((char) ch1);
        str.insert((char) ch2);
        assertEquals((char) ch0, str.getChar(0));
    }

}

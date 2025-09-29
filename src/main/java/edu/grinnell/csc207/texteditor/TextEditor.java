package edu.grinnell.csc207.texteditor;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

/**
 * The driver for the TextEditor Application.
 */
public class TextEditor {
    public static void main(String[] args) throws IOException {
        DefaultTerminalFactory factory = new DefaultTerminalFactory();
        Screen screen = factory.createScreen();
        screen.newTextGraphics();
        GapBuffer text = new GapBuffer();
        
        screen.startScreen();
        if (args.length != 1) {
            System.err.println("Usage: java TextEditor <filename>");
            System.exit(1);
        }

        
        // TODO: fill me in with a text editor TUI!
        String path = args[0];
        System.out.format("Loading %s...\n", path);
        if(Files.exists(Paths.get(path))) {
            String fileContents = Files.readString(Paths.get(path));

            
            for(int i =0; i<fileContents.length(); i++) {
                text.insert(fileContents.charAt(i));
            }
        
            
            boolean isRunning = true;
            while (isRunning) {
                KeyStroke stroke = screen.readInput();
                // TODO: Process the key stroke!
                if(stroke.getKeyType().equals(KeyType.Escape)) {
                    isRunning = false;
                } else if(stroke.getKeyType().equals(KeyType.Character)){
                    text.insert(stroke.getCharacter());
                } else if(stroke.getKeyType().equals(KeyType.ArrowLeft)){
                    text.moveLeft();
                }else if(stroke.getKeyType().equals(KeyType.ArrowRight)){
                    text.moveRight();
                }else if(stroke.getKeyType().equals(KeyType.Backspace)){
                    text.delete();
                }
                drawBuffer(text, screen);
            }
        }
        screen.stopScreen();

    }

    public static void drawBuffer(GapBuffer buf, Screen screen) throws IOException {
        int gap = buf.index2-buf.index1;
        TextCharacter[] txtCharacter = new TextCharacter[buf.getSize()-gap];
        for(int i = 0; i < txtCharacter.length; i++){
            txtCharacter[i] = TextCharacter.fromCharacter(buf.getChar(i))[i];
        }

        for(int i = 0; i < buf.index1; i++){
            
            screen.setCharacter(0, i, txtCharacter[i]);
        }
        for(int i = buf.index2; i < buf.getSize(); i++){
            screen.setCharacter(0, i, txtCharacter[i]);
        }
        screen.refresh();
    }

}

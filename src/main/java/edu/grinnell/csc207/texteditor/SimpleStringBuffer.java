package edu.grinnell.csc207.texteditor;

/**
 * A naive implementation of a text buffer using a <code>String</code>.
 */
public class SimpleStringBuffer {
    public String str;
    public int index;

    /**
     * constructs a new empty string buffer by setting str to "" and index to 0
     */
    public SimpleStringBuffer() {
        str = "";
        index = 0;
    }

    public void insert(char ch) {
        str += ch;
        index++;
    }

    public void delete() {
        if(str.equals("")){
            return;
        }else {
            if(index == str.length()-1){
                str = str.substring(0, index - 2);
            }else{
            str = str.substring(0, index - 2) + str.substring(index-1);
            index--;
            }
        }
    }

    public int getCursorPosition() {
        return index;
    }

    public void moveLeft() {
        if(index > 0) {
            index--;
        }
    }

    public void moveRight() {
        if(index < str.length()) {
            index++;
        }
    }

    public int getSize() {
        return str.length();
    }

    public char getChar(int i) {
        return str.charAt(i);
    }

    @Override
    public String toString() {
        return str;
    }
}

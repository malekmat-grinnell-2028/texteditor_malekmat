package edu.grinnell.csc207.texteditor;

/**
 * A gap buffer-based implementation of a text buffer.
 */
public class GapBuffer {
    public int index1 = 0;
    public int index2 = 3;
    public char[] strArr = new char[4];
    int arrSize = 4;


    public void insert(char ch) {
        if(index1 != index2) {
            strArr[index1] = ch;
            index1++;
        } else {
            char[] tempStrArr = strArr;
            arrSize *= 2;
            strArr = new char[arrSize];
            index2 = index1 + arrSize/2;
            for(int i = 0; i<tempStrArr.length; i++) {
                if(i < tempStrArr.length/2) {
                    strArr[i] = tempStrArr[i];
                } else {
                    strArr[strArr.length - i] = tempStrArr[i];
                }
            }
            index1++;

        }
    }

    public void delete() {
        if(index1 == 0){
            return;
        } else {
            index1--;
        }
    }

    public int getCursorPosition() {
        return index1;
    }

    public void moveLeft() {
        if(index1 != 0) {
            strArr[index2-1] = strArr[index1];
            index1--;
            index2--;
        }
    }

    public void moveRight() {
       if(index2 != strArr.length-1){
        strArr[index1 + 1] = strArr[index2];
        index1++;
        index2++;
       }
    }

    public int getSize() {
       return strArr.length;
    }

    public char getChar(int i) {
        int gap = index2 - index1;
        if(i > index1){
            return strArr[i+gap];
        }
        return strArr[i];
    }

    public String toString() {
        String str = "";
        for(int i = 0; i < index1; i++){
            str += strArr[i];
        }
        for(int i = index2; i < strArr.length-(index1 + (index2-index1)); i--){
            str += strArr[i];
        }
        return str;
    }
}

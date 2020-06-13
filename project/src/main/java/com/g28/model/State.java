package com.g28.model;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

public abstract class State {
    public int width;
    public int height;
    public boolean end;

    public State(int width, int height) {
        this.width = width;
        this.height = height;
        this.end = false;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public void draw(TextGraphics graphics) {}
    public abstract void close();
    public abstract boolean checkEnd();
    public abstract void processKey(KeyStroke key);
}

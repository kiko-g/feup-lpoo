package com.g28.model;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

public class GameOverMenu extends State {
    int score;

    public GameOverMenu(int width, int height) {
        super(width, height);
        this.score = 0;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        graphics.enableModifiers(SGR.BOLD);

        String title = "Game Over!";
        String scoreString = "Score: " + score;

        graphics.putString(width/2 - title.length() / 2, height/2 - 2, title);
        graphics.putString(width/2 - scoreString.length() / 2, height/2, scoreString);
    }

    @Override
    public void close() {
        this.end = true;
    }

    @Override
    public boolean checkEnd() {
        return end;
    }

    @Override
    public void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case Escape:
                this.close();
                break;

            default: break;
        }
    }
}

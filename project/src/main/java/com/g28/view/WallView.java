package com.g28.view;

import com.g28.model.Wall;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import static com.googlecode.lanterna.Symbols.BLOCK_DENSE;

public class WallView {
    public void draw(TextGraphics graphics, Wall wall) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#2121DE"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#2121DE"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(wall.getPosition().getX(), wall.getPosition().getY()), String.valueOf(BLOCK_DENSE));
    }
}

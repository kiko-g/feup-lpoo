package com.g28.view;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.g28.model.Ghost;

public class GhostView {
    public void draw(TextGraphics graphics, Ghost ghost) {
        graphics.enableModifiers(SGR.BOLD);
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(TextColor.Factory.fromString(ghost.getGhostColor()));
        graphics.putString(new TerminalPosition(ghost.getPosition().getX(), ghost.getPosition().getY()), "0");
    }
}

package com.g28.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.g28.model.Player;

import static com.googlecode.lanterna.Symbols.FACE_BLACK;

public class PlayerView {
    public void draw(TextGraphics graphics, Player player) {
        graphics.enableModifiers(SGR.BOLD);
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFF00"));
        if(player.getPoweredUp()) {
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
            graphics.setBackgroundColor(TextColor.Factory.fromString("#F00000"));
        } else {
            graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        }

        graphics.putString(new TerminalPosition(player.getPosition().getX(), player.getPosition().getY()), String.valueOf(FACE_BLACK));

        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        String heartsString = "Hearts: " + player.getHearts();
        graphics.putString(new TerminalPosition(30 - heartsString.length(), 21), heartsString); //draw hearts

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(0, 21), "Score: " + player.getScore()); //draw score
    }
}

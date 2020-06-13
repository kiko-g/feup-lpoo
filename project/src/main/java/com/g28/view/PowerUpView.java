package com.g28.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.g28.model.PowerUp;

public class PowerUpView {
    public void draw(TextGraphics graphics, PowerUp powerUp) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffc0cb"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(powerUp.getPosition().getX(), powerUp.getPosition().getY()), "o");
    }
}

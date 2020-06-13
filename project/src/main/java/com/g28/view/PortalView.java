package com.g28.view;

import com.g28.model.Portal;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import static com.googlecode.lanterna.Symbols.BLOCK_SOLID;

public class PortalView {
    public void draw(TextGraphics graphics, Portal portal) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(portal.getPosition().getX(), portal.getPosition().getY()), String.valueOf(BLOCK_SOLID));
    }
}

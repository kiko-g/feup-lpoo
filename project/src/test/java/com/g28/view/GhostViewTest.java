package com.g28.view;

import com.g28.model.Ghost;
import com.g28.model.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.Test;
import org.mockito.Mockito;

public class GhostViewTest {
    @Test
    public void testDraw() {
        Ghost ghost = Mockito.mock(Ghost.class);
        Mockito.when(ghost.getPosition()).thenReturn(new Position(10, 20));

        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);

        GhostView ghostView = new GhostView();
        ghostView.draw(textGraphics, ghost);

        Mockito.verify(textGraphics, Mockito.times(1))
                .putString(new TerminalPosition(10, 20), "0");
    }
}

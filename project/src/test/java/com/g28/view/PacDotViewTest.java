package com.g28.view;

import com.g28.model.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.g28.model.PacDot;
import org.junit.Test;
import org.mockito.Mockito;

public class PacDotViewTest {
    @Test
    public void testDraw() {
        PacDot pacdot = Mockito.mock(PacDot.class);
        Mockito.when(pacdot.getPosition()).thenReturn(new Position(5, 5));

        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);

        PacDotView pacDotView = new PacDotView();
        pacDotView.draw(textGraphics, pacdot);

        Mockito.verify(textGraphics, Mockito.times(1))
                .putString(new TerminalPosition(5, 5), ".");
    }
}

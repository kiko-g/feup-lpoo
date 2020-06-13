package com.g28.view;

import com.g28.model.Position;
import com.g28.model.Wall;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.Test;
import org.mockito.Mockito;

import static com.googlecode.lanterna.Symbols.BLOCK_DENSE;

public class WallViewTest {
    @Test
    public void testDraw() {
        Wall wall = Mockito.mock(Wall.class);
        Mockito.when(wall.getPosition()).thenReturn(new Position(0, 0));

        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);

        WallView wallView = new WallView();
        wallView.draw(textGraphics, wall);

        Mockito.verify(textGraphics, Mockito.times(1))
                .putString(new TerminalPosition(0, 0), String.valueOf(BLOCK_DENSE));
    }
}

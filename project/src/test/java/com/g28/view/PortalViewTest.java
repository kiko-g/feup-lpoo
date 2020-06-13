package com.g28.view;

import com.g28.model.Portal;
import com.g28.model.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.Test;
import org.mockito.Mockito;

import static com.googlecode.lanterna.Symbols.BLOCK_SOLID;

public class PortalViewTest {
    @Test
    public void testDraw() {
        Portal portal = Mockito.mock(Portal.class);
        Mockito.when(portal.getPosition()).thenReturn(new Position(0, 10));

        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);

        PortalView portalView = new PortalView();
        portalView.draw(textGraphics, portal);

        Mockito.verify(textGraphics, Mockito.times(1))
                .putString(new TerminalPosition(0, 10), String.valueOf(BLOCK_SOLID));
    }
}

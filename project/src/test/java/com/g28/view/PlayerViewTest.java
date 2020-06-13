package com.g28.view;

import com.g28.model.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.g28.model.Player;
import org.junit.Test;
import org.mockito.Mockito;

import static com.googlecode.lanterna.Symbols.FACE_BLACK;

public class PlayerViewTest {
    @Test
    public void testDraw() {
        Player player = Mockito.mock(Player.class);
        Mockito.when(player.getPosition()).thenReturn(new Position(10, 10));

        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);

        PlayerView playerView = new PlayerView();
        playerView.draw(textGraphics, player);

        Mockito.verify(textGraphics, Mockito.times(1))
                .putString(new TerminalPosition(10, 10), String.valueOf(FACE_BLACK));
    }
}

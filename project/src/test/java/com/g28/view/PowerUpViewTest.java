package com.g28.view;

import com.g28.model.Position;
import com.g28.model.PowerUp;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.Test;
import org.mockito.Mockito;

public class PowerUpViewTest {
    @Test
    public void testDraw() {
        PowerUp powerup = Mockito.mock(PowerUp.class);
        Mockito.when(powerup.getPosition()).thenReturn(new Position(20, 20));

        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);

        PowerUpView powerUpView = new PowerUpView();
        powerUpView.draw(textGraphics, powerup);

        Mockito.verify(textGraphics, Mockito.times(1))
                .putString(new TerminalPosition(20, 20), "o");
    }
}

package com.g28.view;
import com.g28.model.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.util.List;

public class ArenaView {

    private PlayerView playerView = new PlayerView();
    private GhostView ghostView = new GhostView();
    private PacDotView pacDotView = new PacDotView();
    private PowerUpView powerUpView = new PowerUpView();
    private PortalView portalView = new PortalView();
    private WallView wallView = new WallView();

    public void drawArena(TextGraphics graphics, Arena arena) {
        List<Element> elements = arena.getAllElements();

        for(Element element : elements) {

            if(element.getClass().equals(Player.class)) {
                playerView.draw(graphics, (Player) element);
            }

            if(element.getClass().equals(Ghost.class)) {
                ghostView.draw(graphics, (Ghost) element);
            }

            if(element.getClass().equals(PacDot.class)) {
                pacDotView.draw(graphics, (PacDot) element);
            }

            if(element.getClass().equals(PowerUp.class)) {
                powerUpView.draw(graphics, (PowerUp) element);
            }

            if(element.getClass().equals(Portal.class)) {
                portalView.draw(graphics, (Portal) element);
            }

            if(element.getClass().equals(Wall.class)) {
                wallView.draw(graphics, (Wall) element);
            }
        }
    }
}

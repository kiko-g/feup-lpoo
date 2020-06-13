package com.g28.model;
import java.util.List;

public class Ghost extends Element 
{
    private String ghostColor, initialGhostColor;
    private ChaseBehaviour chaseBehaviour;
    private FrightenedBehaviour frightenedBehaviour;
    private Position initialPosition;

    public Ghost(int x, int y, String ghostColor, ChaseBehaviour chaseBehaviour, FrightenedBehaviour frightenedBehaviour) {
        super(x, y);
        this.initialPosition = new Position(x, y);
        this.initialGhostColor = ghostColor;
        this.ghostColor = ghostColor;
        this.chaseBehaviour = chaseBehaviour;
        this.frightenedBehaviour = frightenedBehaviour;
    }

    public void setGhostColor(String ghostColor) {
        this.ghostColor = ghostColor;
    }

    public String getGhostColor() {
        return ghostColor;
    }

    public void enterChaseMode(Player player, List<Element> elements) {
        setGhostColor(initialGhostColor);
        this.setPosition(chaseBehaviour.chase(this.getPosition(), player, elements));
    }

    public void enterFrightenedMode(Player player, List<Element> elements) {
        setGhostColor("#1c2e4a");
        this.setPosition(frightenedBehaviour.frightened(this.getPosition(), player, elements));
    }

    public void returnToInitialPosition() {
        this.setPosition(initialPosition);
    }
}

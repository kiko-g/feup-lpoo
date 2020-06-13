package com.g28.model;

public class Player extends Element {

    public Player() {
         this(12, 25);
    }
    public Player(int x, int y) {
        super(x, y);
        this.poweredUp = false;
    }
    private int velocityX = 1, velocityY = 1, hearts = 4, score = 0;
    private boolean poweredUp;

    public int getScore() { return score; }
    public int getVelocityX() { return velocityX; }
    public int getVelocityY() { return velocityY; }
    public boolean getPoweredUp() {
        return poweredUp;
    }
    public int getHearts() {
        return hearts;
    }

    public void setScore(int score) { this.score = score; }
    public void setVelocityX(int velocityX) { this.velocityX = velocityX; }
    public void setVelocityY(int velocityY) { this.velocityY = velocityY; }
    public void setPoweredUp(boolean state) {
        this.poweredUp = state;
    }

    public void incrementScore(int increment) {
        score += increment;
    }

    public boolean decreaseHearts() {
        if (!getPoweredUp())
            hearts--;
        return hearts != 0;
    }


    public Position moveUp() {
        return new Position(getPosition().getX(), getPosition().getY() - velocityY);
    }
    public Position moveDown() {
        return new Position(getPosition().getX(), getPosition().getY() + velocityY);
    }
    public Position moveLeft() {
        return new Position(getPosition().getX() - velocityX, getPosition().getY());
    }
    public Position moveRight() {
        return new Position(getPosition().getX() + velocityX, getPosition().getY());
    }
}
package com.g28.model;

public class Portal extends Element {

    public Portal(int x, int y) {
        super(x, y);
    }

    public void warp(Player player, Arena.LevelType level) {
        switch(level) {
            case TINY:
                break;
            case MAZE:
                if(player.getPosition().getX() == 0) player.setPosition(new Position(28, 9));
                else if(player.getPosition().getX() == 28) player.setPosition(new Position(0, 9));
                break;
            case OPEN:
                if(player.getPosition().getX() == 0) player.setPosition(new Position(46, 9));
                else if(player.getPosition().getX() == 46) player.setPosition(new Position(0, 9));
                else if(player.getPosition().getY() == 0) player.setPosition(new Position(23, 18));
                else if(player.getPosition().getY() == 18) player.setPosition(new Position(23, 0));
                break;
        }

    }
}


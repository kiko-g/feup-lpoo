package com.g28.model;
import java.util.ArrayList;
import java.util.List;

public abstract class MovingStrategy {
    Integer lastPosition;
    Position newPosUp;
    Position newPosDown;
    Position newPosLeft;
    Position newPosRight;
    List<Integer> bestMoves;
    List<Integer> availableMoves;

    public MovingStrategy() {
        lastPosition = -1;
        availableMoves = new ArrayList<>();
        bestMoves = new ArrayList<>();
    }

    public void calculateAvailableMoves(Position position, List<Element> elements) {
        newPosUp = new Position(position.getX(), position.getY() - 1);
        newPosDown = new Position(position.getX(), position.getY() + 1);
        newPosLeft = new Position(position.getX() - 1, position.getY());
        newPosRight = new Position(position.getX() + 1, position.getY());

        availableMoves.clear();
        availableMoves.add(0);
        availableMoves.add(1);
        availableMoves.add(2);
        availableMoves.add(3);

        for (Element element : elements)
        {
            if (newPosUp.equals(element.getPosition())) {
                for (Integer move : availableMoves)
                    if(move == 0) {
                        availableMoves.remove(move);
                        break;
                    }
            }
            else if (newPosDown.equals(element.getPosition())) {
                for (Integer move : availableMoves)
                    if(move == 1) {
                        availableMoves.remove(move);
                        break;
                    }
            }
            else if (newPosLeft.equals(element.getPosition())) {
                for (Integer move : availableMoves)
                    if(move == 2) {
                        availableMoves.remove(move);
                        break;
                    }
            }
            else if (newPosRight.equals(element.getPosition())) {
                for (Integer move : availableMoves)
                    if(move == 3) {
                        availableMoves.remove(move);
                        break;
                    }
            }
        }
    }

    public Position calculateMove(Position position, Player player, List<Element> elements) {
        return null;
    }
}

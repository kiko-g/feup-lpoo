package com.g28.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChasePredict extends MovingStrategy implements ChaseBehaviour {

    public ChasePredict() {
        super();
    }

    @Override
    public Position chase(Position position, Player player, List<Element> elements) {
        calculateAvailableMoves(position, elements);
        return calculateMove(position, player, elements);
    }

    @Override
    public Position calculateMove(Position position, Player player, List<Element> elements) {
        List<Integer> moves;
        int yDifference = player.getPosition().getY() - position.getY() + 2;
        int xDifference = player.getPosition().getX() - position.getX() + 2;

        if (yDifference < 0) bestMoves.add(0);
        else if (yDifference > 0) bestMoves.add(1);
        if (xDifference < 0) bestMoves.add(2);
        else if (xDifference > 0) bestMoves.add(3);

        availableMoves.removeIf(number -> number.equals(lastPosition));
        bestMoves.removeIf(number -> !availableMoves.contains(number));

        Random rand = new Random();
        int randomAvailableMove;

        if (bestMoves.size() != 0) {
            randomAvailableMove = rand.nextInt(bestMoves.size());
            moves = bestMoves;
        } else if (availableMoves.size() != 0) {
            randomAvailableMove = rand.nextInt(availableMoves.size());
            moves = availableMoves;
        } else {
            moves = new ArrayList<>();
            moves.add(lastPosition);
            randomAvailableMove = 0;
        }

        switch (moves.get(randomAvailableMove)) {
            case 0: lastPosition = 1; return newPosUp;
            case 1: lastPosition = 0; return newPosDown;
            case 2: lastPosition = 3; return newPosLeft;
            case 3: lastPosition = 4; return newPosRight;
            default: return position;
        }
    }
}
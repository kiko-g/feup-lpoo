package com.g28.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChaseRandom extends MovingStrategy implements ChaseBehaviour {

    public ChaseRandom() {
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
        Random rand = new Random();
        int randomAvailableMove;

        if (availableMoves.size() != 0) {
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
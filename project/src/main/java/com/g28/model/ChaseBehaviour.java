package com.g28.model;
import java.util.List;

public interface ChaseBehaviour {
    Position chase(Position position, Player player, List<Element> elements);
}

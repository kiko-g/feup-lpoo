package com.g28.model;

import java.util.List;

public interface FrightenedBehaviour {
    Position frightened(Position position, Player player, List<Element> elements);
}

package com.esiea.tp4A.domain;

import java.util.HashSet;
import java.util.Set;

public class PlanetMapImpl implements PlanetMap {

    final int BORDER_NORTH = 50;
    final int BORDER_SOUTH = -49;
    final int BORDER_EAST = 50;
    final int BORDER_WEST = -49;

    final int BORDER_NORTH_EAST = 50;
    final int BORDER_SOUTH_WEST = -49;

    public int north_east_border(int p) {
        if (p < BORDER_NORTH_EAST)
            p++;
        else if (p == BORDER_NORTH_EAST)
            p = BORDER_SOUTH_WEST;
        return p;
    }

    public int south_west_border(int p) {
        if (p > BORDER_SOUTH_WEST)
            p--;
        else if (p == BORDER_SOUTH_WEST)
            p = BORDER_NORTH_EAST;
        return p;
    }

    public static Set<Position> obstaclePositions() {
        Position pos1 = Position.of(0, 1, Direction.NORTH);
        Position pos2 = Position.of(2, 4, Direction.NORTH);
        Position pos3 = Position.of(30, 16, Direction.NORTH);
        Set<Position> obstacles = new HashSet<Position>();
        obstacles.add(pos1);
        obstacles.add(pos2);
        obstacles.add(pos3);
        return obstacles;
    }
}

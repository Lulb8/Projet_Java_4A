package com.esiea.tp4A.domain;

import java.util.HashSet;
import java.util.Set;

public class PlanetMapImpl implements PlanetMap {

    private Set<Position> obstacles = new HashSet<Position>();

    private final int BORDER_NORTH_EAST = 50;
    private final int BORDER_SOUTH_WEST = -49;

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

    public boolean checkIfObstacle(int x, int y) {
        return obstacles.stream().anyMatch(position -> position.getX() == x && position.getY() == y);
    }

    public void addObstacle(int obsX, int obsY) {
        Obstacle newObstacle = new Obstacle(obsX, obsY);
        obstacles.add(newObstacle);
    }

    public void destroyObstacle(int x, int y) {
        obstacles.removeIf(obstacle -> obstacle.getX() == x && obstacle.getY() == y);
    }

    @Override
    public Set<Position> obstaclePositions() {
        return obstacles;
    }
}

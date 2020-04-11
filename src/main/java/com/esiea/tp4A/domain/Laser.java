package com.esiea.tp4A.domain;

public class Laser {

    private final int range;
    private final PlanetMapImpl map;

    public Laser(int range, PlanetMapImpl map) {
        this.range = range;
        this.map = map;
    }

    public void shootOnObstacle(int x, int y, Direction direction) {
        switch (direction) {
            case NORTH:
                shootNorth(x, y, direction);
                break;
            case SOUTH:
                shootSouth(x, y, direction);
                break;
            case WEST:
                shootWest(x, y, direction);
                break;
            case EAST:
                shootEast(x, y, direction);
                break;
        }
    }

    public void shootNorth(int x, int y, Direction direction) {
        for (int i = 1; i <= range; i++) {
            if (map.checkIfObstacle(x, y + i, direction)) {
                map.destroyObstacle(x, y + i);
                break;
            }
        }
    }

    public void shootSouth(int x, int y, Direction direction) {
        for (int i = 1; i <= range; i++) {
            if (map.checkIfObstacle(x, y - i, direction)) {
                map.destroyObstacle(x, y - i);
                break;
            }
        }
    }

    public void shootWest(int x, int y, Direction direction) {
        for (int i = 1; i <= range; i++) {
            if (map.checkIfObstacle(x - i, y, direction)) {
                map.destroyObstacle(x - i, y);
                break;
            }
        }
    }

    public void shootEast(int x, int y, Direction direction) {
        for (int i = 1; i <= range; i++) {
            if (map.checkIfObstacle(x + i, y, direction)) {
                map.destroyObstacle(x + i, y);
                break;
            }
        }
    }
}

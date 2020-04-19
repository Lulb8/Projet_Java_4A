package com.esiea.tp4A.domain;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class PlanetMapImpl implements PlanetMap {

    private final Set<Position> obstacles = new HashSet<Position>();
    private final Set<MarsRoverImpl> rovers = new HashSet<MarsRoverImpl>();
    private final int BORDER_NORTH_EAST;
    private final int BORDER_SOUTH_WEST;

    public PlanetMapImpl(int mapSize, int numberOfObstacles) {
        this.BORDER_NORTH_EAST = mapSize / 2;   //for 100x100 -> 50
        this.BORDER_SOUTH_WEST = -((mapSize / 2) - 1); //for 100x100 -> -49
        this.addRandomObstacles(numberOfObstacles);
    }

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

    public void addRandomObstacles(int numberOfObstacles) {
        int obsX, obsY;
        while (obstacles.size() < numberOfObstacles){
            do {
                obsX = randomInt(BORDER_SOUTH_WEST, BORDER_NORTH_EAST);
                obsY = randomInt(BORDER_SOUTH_WEST, BORDER_NORTH_EAST);
            } while (checkIfObstacle(obsX, obsY));
            addOneObstacle(obsX, obsY);
        }
    }

    public void addOneObstacle(int obsX, int obsY) {
        Obstacle newObstacle = new Obstacle(obsX, obsY);
        obstacles.add(newObstacle);
    }

    public void destroyObstacle(int x, int y) {
        obstacles.removeIf(obstacle -> obstacle.getX() == x && obstacle.getY() == y);
    }

    public void addRandomRovers(int numberOfRovers) {
        int roverX, roverY;
        while (rovers.size() < numberOfRovers){
            do {
                roverX = randomInt(BORDER_SOUTH_WEST, BORDER_NORTH_EAST);
                roverY = randomInt(BORDER_SOUTH_WEST, BORDER_NORTH_EAST);
            } while (checkIfObstacle(roverX, roverY));
            addOneRover(roverX, roverY);
        }
    }

    public void addOneRover(int roverX, int roverY) {
        MarsRoverImpl newRover = new MarsRoverImpl(roverX, roverY, Direction.NORTH, this);
        rovers.add(newRover);
    }

    public int randomInt(int min, int max){
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    @Override
    public Set<Position> obstaclePositions() {
        return obstacles;
    }

    public Set<MarsRoverImpl> getRovers() {
        return rovers;
    }
}

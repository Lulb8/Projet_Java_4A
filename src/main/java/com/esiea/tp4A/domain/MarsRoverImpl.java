package com.esiea.tp4A.domain;

public class MarsRoverImpl implements MarsRover {
    private Position position;
    private final PlanetMapImpl map;
    private boolean knownCommand = true;

    public MarsRoverImpl(int x, int y, Direction direction, PlanetMapImpl map) {
        position = Position.of(x, y, direction);
        this.map = map;
    }

    @Override
    public Position move(String command) {
        int originalX = position.getX(), originalY = position.getY(); Direction originalDirection = position.getDirection();
        if (command.isEmpty()) return position;
        for (int count = 0; knownCommand && count < command.length(); count++) {
            char singleCommand = command.charAt(count);
            int x = position.getX(), y = position.getY(); Direction direction = position.getDirection();
            execute_command(x, y, direction, originalX, originalY, originalDirection, singleCommand);
        } return position;
    }

    public void execute_command(int x, int y, Direction direction, int originalX, int originalY, Direction originalDirection, char singleCommand) {
        switch (singleCommand) {
            case 'f': move_rover_forward(x, y, direction); break;
            case 'b': move_rover_backward(x, y, direction); break;
            case 'l': give_new_position(x, y, direction = direction.rotateLeft()); break;
            case 'r': give_new_position(x, y, direction = direction.rotateRight()); break;
            default: give_new_position(originalX, originalY, originalDirection); knownCommand = false; break;
        }
    }

    public void move_rover_forward(int x, int y, Direction direction) {
        switch (direction) {
            case NORTH: y = map.north_east_border(y); break;
            case EAST: x = map.north_east_border(x); break;
            case SOUTH: y = map.south_west_border(y); break;
            case WEST: x = map.south_west_border(x); break;
        } if (!map.checkIfObstacle(x, y, direction)){
            give_new_position(x, y, direction);
        }
    }

    public void move_rover_backward(int x, int y, Direction direction) {
        switch (direction) {
            case NORTH: y = map.south_west_border(y); break;
            case EAST: x = map.south_west_border(x); break;
            case SOUTH: y = map.north_east_border(y); break;
            case WEST: x = map.north_east_border(x); break;
        } if (!map.checkIfObstacle(x, y, direction)){
            give_new_position(x, y, direction);
        }
    }

    public void give_new_position(int x, int y, Direction direction) {
            position = Position.of(x, y, direction);
    }

/*
    public void laser(int rangelaser, Direction direction, Position marsroverposition, char commandshoot) {
        if (commandshoot == 's')
            for (Position positionObstacles : PlanetMap.obstaclePositions())
                for (int laserlength = 1; laserlength <= rangelaser; laserlength++)
                    switch (direction) {
                        case NORTH:
                            if (marsroverposition.getY() + laserlength == positionObstacles.getY()
                                    && marsroverposition.getX() == positionObstacles.getX())
                                PlanetMapImpl.obstaclePositions().remove(positionObstacles);
                            break;
                        case EAST:
                            if (marsroverposition.getX() + laserlength == positionObstacles.getX()
                                    && marsroverposition.getY() == positionObstacles.getY())
                                PlanetMapImpl.obstaclePositions().remove(positionObstacles);
                            break;
                        case SOUTH:
                            if (marsroverposition.getY() - laserlength == positionObstacles.getY()
                                    && marsroverposition.getX() == positionObstacles.getX())
                                PlanetMapImpl.obstaclePositions().remove(positionObstacles);
                            break;
                        case WEST:
                            if (marsroverposition.getX() - laserlength == positionObstacles.getX()
                                    && marsroverposition.getY() == positionObstacles.getY())
                                PlanetMapImpl.obstaclePositions().remove(positionObstacles);
                            break;
                    }
    }
 */
}

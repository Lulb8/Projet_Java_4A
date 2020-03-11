package com.esiea.tp4A.domain;

public class MarsRoverImpl implements MarsRover {

    Position position;

    public MarsRoverImpl(int x, int y, Direction direction) {
        position = Position.of(x, y, direction);
    }

    @Override
    public Position move(String command) {
        int x= position.getX();
        int y = position.getY();
        Direction direction = position.getDirection();

        int originalX= position.getX();
        int originalY = position.getY();
        Direction originalDirection = position.getDirection();

        if (command.isEmpty()) {
            return position;
        }

        for (int count = 0; count < command.length(); count++) {
            char singleCommand = command.charAt(count);
            switch (singleCommand) {
                case 'f':
                    Object[] newPositionF = move_rover_forward(x, y, direction);
                    x = (int) newPositionF[0];
                    y = (int) newPositionF[1];
                    position = Position.of(x, y, direction); break;
                case 'b':
                    Object[] newPositionB = move_rover_backward(x, y, direction);
                    x = (int) newPositionB[0];
                    y = (int) newPositionB[1];
                    position = Position.of(x, y, direction); break;
                case 'l':
                    direction = move_rover_left(direction);
                    position = Position.of(x, y, direction); break;
                case 'r':
                    direction = move_rover_right(direction);
                    position = Position.of(x, y, direction); break;
                default:
                    return position = Position.of(originalX, originalY, originalDirection);
            }
        }
        return position;
    }

    public Object[] move_rover_forward(int x, int y, Direction direction){
        int newX = x;
        int newY = y;
        switch (direction){
            case NORTH:
                if (y < 50) {
                    newY++;
                } else if (y == 50) {
                    newY = -49;
                } break;
            case EAST:
                if (x < 50) {
                    newX++;
                } else if (x == 50) {
                    newX = -49;
                } break;
            case SOUTH:
                if (y > -49) {
                    newY--;
                } else if (y == -49) {
                    newY = 50;
                } break;
            case WEST:
                if (x > -49) {
                    newX--;
                } else if (x == -49) {
                    newX = 50;
                } break;
        }
        return new Object[]{newX, newY};
    }

    public Object[] move_rover_backward(int x, int y, Direction direction){
        int newX = x;
        int newY = y;
        switch (direction){
            case NORTH:
                if (y > -49) {
                    newY--;
                } else if (y == -49) {
                    newY = 50;
                } break;
            case EAST:
                if (x > -49) {
                    newX--;
                } else if (x == -49) {
                    newX = 50;
                } break;
            case SOUTH:
                if (y < 50) {
                    newY++;
                } else if (y == 50) {
                    newY = -49;
                } break;
            case WEST:
                if (x < 50) {
                    newX++;
                } else if (x == 50) {
                    newX = -49;
                } break;
        }
        return new Object[]{newX, newY};
    }

    public Direction move_rover_right(Direction direction){
        Direction newDirection = direction;
        switch (direction) {
            case NORTH: newDirection = Direction.EAST; break;
            case WEST: newDirection = Direction.NORTH; break;
            case SOUTH: newDirection = Direction.WEST; break;
            case EAST: newDirection = Direction.SOUTH; break;
        }
        return newDirection;
    }

    public Direction move_rover_left(Direction direction){
        Direction newDirection = direction;
        switch (direction) {
            case NORTH: newDirection = Direction.WEST; break;
            case WEST: newDirection = Direction.SOUTH; break;
            case SOUTH: newDirection = Direction.EAST; break;
            case EAST: newDirection = Direction.NORTH; break;
        }
        return newDirection;
    }
}

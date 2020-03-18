package com.esiea.tp4A.domain;

public class MarsRoverImpl implements MarsRover {
    Position position;

    public MarsRoverImpl(int x, int y, Direction direction) {
        position = Position.of(x, y, direction);
    }

    @Override
    public Position move(String command) {
        int x = position.getX(), originalX = position.getX(), y = position.getY(), originalY = position.getY();
        Direction direction = position.getDirection(), originalDirection = position.getDirection();
        if (command.isEmpty())
            return position;
        for (int count = 0; count < command.length(); count++) {
            char singleCommand = command.charAt(count);
            switch (singleCommand) {
                case 'f':
                    Object[] newPositionF = move_rover_forward(x, y, direction);
                    x = (int) newPositionF[0];
                    y = (int) newPositionF[1];
                    position = Position.of(x, y, direction);
                    break;
                case 'b':
                    Object[] newPositionB = move_rover_backward(x, y, direction);
                    x = (int) newPositionB[0];
                    y = (int) newPositionB[1];
                    position = Position.of(x, y, direction);
                    break;
                case 'l':
                    direction = move_rover_left(direction);
                    position = Position.of(x, y, direction);
                    break;
                case 'r':
                    direction = move_rover_right(direction);
                    position = Position.of(x, y, direction);
                    break;
                default:
                    return position = Position.of(originalX, originalY, originalDirection);
            }
        }
        return position;
    }

    public Object[] move_rover_forward(int x, int y, Direction direction) {
        switch (direction) {
            case NORTH: y = north_east_border(y); break;
            case EAST: x = north_east_border(x); break;
            case SOUTH: y = south_west_border(y); break;
            case WEST: x = south_west_border(x); break;
        } return new Object[]{x, y};
    }

    public Object[] move_rover_backward(int x, int y, Direction direction) {
        switch (direction) {
            case NORTH: y = south_west_border(y); break;
            case EAST: x = south_west_border(x); break;
            case SOUTH: y = north_east_border(y); break;
            case WEST: x = north_east_border(x); break;
        } return new Object[]{x, y};
    }

    public Direction move_rover_right(Direction direction) {
        Direction newDirection = direction;
        switch (direction) {
            case NORTH: newDirection = Direction.EAST; break;
            case WEST: newDirection = Direction.NORTH; break;
            case SOUTH: newDirection = Direction.WEST; break;
            case EAST: newDirection = Direction.SOUTH; break;
        } return newDirection;
    }

    public Direction move_rover_left(Direction direction) {
        Direction newDirection = direction;
        switch (direction) {
            case NORTH: newDirection = Direction.WEST; break;
            case WEST: newDirection = Direction.SOUTH; break;
            case SOUTH: newDirection = Direction.EAST; break;
            case EAST: newDirection = Direction.NORTH; break;
        } return newDirection;
    }

    public int north_east_border(int p) {
        if (p < 50)
            p++;
        else if (p == 50)
            p = -49;
        return p;
    }

    public int south_west_border(int p) {
        if (p > -49)
            p--;
        else if (p == -49)
            p = 50;
        return p;
    }
}

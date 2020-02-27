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

        if (command.isEmpty()) {
            return position;
        }
        for (int count = 0; count < command.length(); count++) {
            char singleCommand = command.charAt(count);
            switch (singleCommand) {
                case 'f':
                    if (position.getY() == 50 && position.getDirection() == Direction.NORTH) {
                        y = -49;
                    } else if (position.getX() == 50 && position.getDirection() == Direction.EAST) {
                        x = -49;
                    } else if (position.getY() == -49 && position.getDirection() == Direction.SOUTH) {
                        y = 50;
                    } else if (position.getX() == -49 && position.getDirection() == Direction.WEST) {
                        x = 50;
                    } else {
                        switch (position.getDirection()) {
                            case NORTH:
                                y++; break;
                            case WEST:
                                x--; break;
                            case SOUTH:
                                y--; break;
                            case EAST:
                                x++; break;
                        }
                    }
                    position = Position.of(x, y, direction); break;
                case 'b':
                    if (position.getY() == -49 && position.getDirection() == Direction.NORTH) {
                        y = 50;
                    } else if (position.getX() == -49 && position.getDirection() == Direction.EAST) {
                        x = 50;
                    } else if (position.getY() == 50 && position.getDirection() == Direction.SOUTH) {
                        y = -49;
                    } else if (position.getX() == 50 && position.getDirection() == Direction.WEST) {
                        x = -49;
                    } else {
                        switch (position.getDirection()) {
                            case NORTH:
                                y--; break;
                            case WEST:
                                x++; break;
                            case SOUTH:
                                y++; break;
                            case EAST:
                                x--; break;
                        }
                    }
                    position = Position.of(x, y, direction); break;
                case 'l':
                    switch (position.getDirection()) {
                        case NORTH:
                            direction = Direction.WEST; break;
                        case WEST:
                            direction = Direction.SOUTH; break;
                        case SOUTH:
                            direction = Direction.EAST; break;
                        case EAST:
                            direction = Direction.NORTH; break;
                    }
                    position = Position.of(x, y, direction);
                    break;
                case 'r':
                    switch (position.getDirection()) {
                        case NORTH:
                            direction = Direction.EAST; break;
                        case WEST:
                            direction = Direction.NORTH; break;
                        case SOUTH:
                            direction = Direction.WEST; break;
                        case EAST:
                            direction = Direction.SOUTH; break;
                    }
                    position = Position.of(x, y, direction); break;
            }
        }
        return position;
    }
}

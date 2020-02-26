package com.esiea.tp4A.domain;

public class MarsRoverImpl implements MarsRover {

    Position position;

    public MarsRoverImpl(int x, int y, Direction direction) {
        position = Position.of(x, y, direction);
    }

    @Override
    public Position move(String command) {
        if (command.isEmpty()) {
            return position;
        }
        for (int count = 0; count < command.length(); count++) {
            char singleCommand = command.charAt(count);
            if ('f' == singleCommand) {
                if (position.getDirection() == Direction.NORTH) {
                    position = Position.of(position.getX(), position.getY() + 1, Direction.NORTH);
                } else if (position.getDirection() == Direction.WEST) {
                    position = Position.of(position.getX() - 1, position.getY(), Direction.WEST);
                } else if (position.getDirection() == Direction.SOUTH) {
                    position = Position.of(position.getX(), position.getY() - 1, Direction.SOUTH);
                } else if (position.getDirection() == Direction.EAST) {
                    position = Position.of(position.getX() + 1, position.getY(), Direction.EAST);
                }
            } else if ('b' == singleCommand) {
                if (position.getDirection() == Direction.NORTH) {
                    position = Position.of(position.getX(), position.getY() - 1, Direction.NORTH);
                } else if (position.getDirection() == Direction.WEST) {
                    position = Position.of(position.getX() + 1, position.getY(), Direction.WEST);
                } else if (position.getDirection() == Direction.SOUTH) {
                    position = Position.of(position.getX(), position.getY() + 1, Direction.SOUTH);
                } else if (position.getDirection() == Direction.EAST) {
                    position = Position.of(position.getX() - 1, position.getY(), Direction.EAST);
                }
            } else if ('l' == singleCommand) {
                if (position.getDirection() == Direction.NORTH) {
                    position = Position.of(position.getX(), position.getY(), Direction.WEST);
                } else if (position.getDirection() == Direction.WEST) {
                    position = Position.of(position.getX(), position.getY(), Direction.SOUTH);
                } else if (position.getDirection() == Direction.SOUTH) {
                    position = Position.of(position.getX(), position.getY(), Direction.EAST);
                } else if (position.getDirection() == Direction.EAST) {
                    position = Position.of(position.getX(), position.getY(), Direction.NORTH);
                }
            } else if ('r' == singleCommand) {
                if (position.getDirection() == Direction.NORTH) {
                    position = Position.of(position.getX(), position.getY(), Direction.EAST);
                } else if (position.getDirection() == Direction.EAST) {
                    position = Position.of(position.getX(), position.getY(), Direction.SOUTH);
                } else if (position.getDirection() == Direction.SOUTH) {
                    position = Position.of(position.getX(), position.getY(), Direction.WEST);
                } else if (position.getDirection() == Direction.WEST) {
                    position = Position.of(position.getX(), position.getY(), Direction.NORTH);
                }
            }
        }
        return position;
    }
}

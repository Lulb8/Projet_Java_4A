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
        char singleCommand = command.charAt(0);
        if ('f' == singleCommand) {
            position = Position.of(position.getX(), position.getY() + 1, position.getDirection());
        } else if ('b' == singleCommand) {
            position = Position.of(position.getX(), position.getY() - 1, position.getDirection());
        }
        return position;
    }
}

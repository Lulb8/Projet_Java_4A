package com.esiea.tp4A.domain;

public class MarsRoverImpl implements MarsRover {
    private Position position;
    PlanetMapImpl map = new PlanetMapImpl();
    boolean knownCommand = true;

    public MarsRoverImpl(int x, int y, Direction direction) {
        position = Position.of(x, y, direction);
    }

    @Override
    public Position move(String command) {
        int originalX = position.getX(), originalY = position.getY();
        Direction originalDirection = position.getDirection();
        if (command.isEmpty())
            return position;
        for (int count = 0; knownCommand && count < command.length(); count++) {
            char singleCommand = command.charAt(count);
            int x = position.getX(), y = position.getY();
            Direction direction = position.getDirection();
            execute_command(x, y, direction, originalX, originalY, originalDirection, singleCommand);
        }
        return position;
    }

    public Position previous_position(String command) {
        int originalX = position.getX(), originalY = position.getY();
        Direction originalDirection = position.getDirection();
        if (command.isEmpty())
            return position;
        for (int count = 0; knownCommand && count < command.length(); count++) {
            char singleCommand = command.charAt(count);
            no_execute_command(originalX, originalY, originalDirection, singleCommand);
        }
        return position;
    }

    public void execute_command(int x, int y, Direction direction, int originalX, int originalY,
            Direction originalDirection, char singleCommand) {
        switch (singleCommand) {
            case 'f':
                move_rover_forward(x, y, direction);
                break;
            case 'b':
                move_rover_backward(x, y, direction);
                break;
            case 'l':
                give_new_position(x, y, direction = direction.rotateLeft());
                break;
            case 'r':
                give_new_position(x, y, direction = direction.rotateRight());
                break;
            default:
                give_new_position(originalX, originalY, originalDirection);
                knownCommand = false;
                break;
        }
    }

    public void no_execute_command(int originalX, int originalY, Direction originalDirection, char singleCommand) {
        switch (singleCommand) {
            case 'f':
                move_rover_forward(originalX, originalY, originalDirection);
                break;
            case 'b':
                move_rover_backward(originalX, originalY, originalDirection);
                break;
            case 'l':
                give_new_position(originalX, originalY, originalDirection);
                break;
            case 'r':
                give_new_position(originalX, originalY, originalDirection);
                break;
        }
    }

    public void move_rover_forward(int x, int y, Direction direction) {
        switch (direction) {
            case NORTH:
                y = map.north_east_border(y);
                break;
            case EAST:
                x = map.north_east_border(x);
                break;
            case SOUTH:
                y = map.south_west_border(y);
                break;
            case WEST:
                x = map.south_west_border(x);
                break;
        }
        give_new_position(x, y, direction);
    }

    public void move_rover_backward(int x, int y, Direction direction) {
        switch (direction) {
            case NORTH:
                y = map.south_west_border(y);
                break;
            case EAST:
                x = map.south_west_border(x);
                break;
            case SOUTH:
                y = map.north_east_border(y);
                break;
            case WEST:
                x = map.north_east_border(x);
                break;
        }
        give_new_position(x, y, direction);
    }

    public void give_new_position(int x, int y, Direction direction) {
        position = Position.of(x, y, direction);
    }

    public void detect_obstacles(char command, int initX, int initY, Direction initDirection) {
        int originalX = position.getX(), originalY = position.getY();
        Direction originalDirection = position.getDirection();
        String stringcommand = String.valueOf(command);
        Position newPosition = move(stringcommand);

        for (Position positionObstacles : PlanetMap.obstaclePositions())
            switch (command) {
                case 'f':
                    if (positionObstacles.getY() == newPosition.getY()
                            && positionObstacles.getX() == newPosition.getX())
                        newPosition = previous_position("f");
                    else
                        newPosition = move(stringcommand);
                    break;
                case 'b':
                    if (positionObstacles.getY() == newPosition.getY()
                            && positionObstacles.getX() == newPosition.getX())
                        newPosition = previous_position("b");
                    else
                        newPosition = move(stringcommand);
                    break;
            }
        no_execute_command(originalX, originalY, originalDirection, command);
    }

}

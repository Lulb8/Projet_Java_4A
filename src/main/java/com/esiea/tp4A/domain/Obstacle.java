package com.esiea.tp4A.domain;

public class Obstacle implements Position {

    private final int x;
    private final int y;
    private Position position;

    public Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
        position = Position.of(x, y, Direction.NORTH);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public Direction getDirection() {
        return null;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}

package com.esiea.tp4A;

import com.esiea.tp4A.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MarsRoverTest {

    PlanetMapImpl map = new PlanetMapImpl();

    @Test
    void empty_move() {
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH, map);
        Position newPosition = marsRover.move("");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 0, Direction.NORTH));
    }

    @Test
    void move_forward() {
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH, map);
        Position newPosition = marsRover.move("f");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 1, Direction.NORTH));
    }

    @Test
    void move_backward() {
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH, map);
        Position newPosition = marsRover.move("b");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, -1, Direction.NORTH));
    }

    @Test
    void move_left() {
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH, map);
        Position newPosition = marsRover.move("l");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 0, Direction.WEST));
    }

    @Test
    void move_right() {
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH, map);
        Position newPosition = marsRover.move("r");
        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 0, Direction.EAST));
    }

    @ParameterizedTest
    @CsvSource({ "'f', 0, 1, NORTH", "'b', 0, -1, NORTH", "'l', 0, 0, WEST", "'r', 0, 0, EAST", "'ffl', 0, 2, WEST",
            "'frfrfrfr', 0, 0, NORTH", "'rr', 0, 0, SOUTH", "'lll', 0, 0, EAST", "'fblfl', -1, 0, SOUTH",
            "'bbrfrblff', 3, -1, EAST", "'fflb', 1, 2, WEST", "'lbblffr',2, -2, WEST" })
    void move_from_center(String command, int expectedX, int expectedY, Direction expectedDirection) {
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH, map);
        Position newPosition = marsRover.move(command);
        Assertions.assertThat(newPosition).isEqualTo(Position.of(expectedX, expectedY, expectedDirection));
    }

    @ParameterizedTest
    @CsvSource({ "'f', 0, -49, NORTH, NORTH", "'b', 0, -49, SOUTH, SOUTH" })
    void move_from_border_north(String command, int expectedX, int expectedY, Direction initDirection,
            Direction expectedDirection) {
        MarsRover marsRover = new MarsRoverImpl(0, 50, initDirection, map);
        Position newPosition = marsRover.move(command);
        Assertions.assertThat(newPosition).isEqualTo(Position.of(expectedX, expectedY, expectedDirection));
    }

    @ParameterizedTest
    @CsvSource({ "'f', 0, 50, SOUTH, SOUTH", "'b', 0, 50, NORTH, NORTH" })
    void move_from_border_south(String command, int expectedX, int expectedY, Direction initDirection,
            Direction expectedDirection) {
        MarsRover marsRover = new MarsRoverImpl(0, -49, initDirection, map);
        Position newPosition = marsRover.move(command);
        Assertions.assertThat(newPosition).isEqualTo(Position.of(expectedX, expectedY, expectedDirection));
    }

    @ParameterizedTest
    @CsvSource({ "'f', -49, 0, EAST, EAST", "'b', -49, 0, WEST, WEST" })
    void move_from_border_east(String command, int expectedX, int expectedY, Direction initDirection,
            Direction expectedDirection) {
        MarsRover marsRover = new MarsRoverImpl(50, 0, initDirection, map);
        Position newPosition = marsRover.move(command);
        Assertions.assertThat(newPosition).isEqualTo(Position.of(expectedX, expectedY, expectedDirection));
    }

    @ParameterizedTest
    @CsvSource({ "'f', 50, 0, WEST, WEST", "'b', 50, 0, EAST, EAST" })
    void move_from_border_west(String command, int expectedX, int expectedY, Direction initDirection,
            Direction expectedDirection) {
        MarsRover marsRover = new MarsRoverImpl(-49, 0, initDirection, map);
        Position newPosition = marsRover.move(command);
        Assertions.assertThat(newPosition).isEqualTo(Position.of(expectedX, expectedY, expectedDirection));
    }

    @ParameterizedTest
    @CsvSource({ "'ff', 0, 2, NORTH, 0, 0, NORTH", "'lbblffr', 2, -2, WEST, 0, 0, NORTH" })
    void move_from_another_origin(String command, int expectedX, int expectedY, Direction expectedDirection, int initX,
            int initY, Direction initDirection) {
        MarsRover marsRover = new MarsRoverImpl(initX, initY, initDirection, map);
        Position newPosition = marsRover.move(command);
        Assertions.assertThat(newPosition).isEqualTo(Position.of(expectedX, expectedY, expectedDirection));
    }

    @ParameterizedTest
    @CsvSource({ "'aff', 0, 0, NORTH", "'f f', 0, 0, NORTH", "'f,f', 0, 0, NORTH", "'ff;', 0, 0, NORTH" })
    void unknown_command(String command, int expectedX, int expectedY, Direction expectedDirection) {
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH, map);
        Position newPosition = marsRover.move(command);
        Assertions.assertThat(newPosition).isEqualTo(Position.of(expectedX, expectedY, expectedDirection));
    }
}

package com.esiea.tp4A;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.MarsRover;
import com.esiea.tp4A.domain.MarsRoverImpl;
import com.esiea.tp4A.domain.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class MarsRoverTest {

    @Test
    void empty_move() {
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH);
        Position newPosition = marsRover.move("");

        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 0, Direction.NORTH));
    }

    @Test
    void move_forward() {
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH);
        Position newPosition = marsRover.move("f");

        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 1, Direction.NORTH));
    }

    @Test
    void move_backward() {
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH);
        Position newPosition = marsRover.move("b");

        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, -1, Direction.NORTH));
    }

    @Test
    void move_left() {
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH);
        Position newPosition = marsRover.move("l");

        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 0, Direction.WEST));
    }

    @Test
    void move_right() {
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH);
        Position newPosition = marsRover.move("r");

        Assertions.assertThat(newPosition).isEqualTo(Position.of(0, 0, Direction.EAST));
    }

    @ParameterizedTest
    @CsvSource({
        "'f', 0, 1, NORTH",
        "'l', 0, 0, WEST",
        "'fflb', 1, 2, WEST"
    })
    void move_from_center(String command, int expectedX, int expectedY, Direction expectedDirection) {
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH);
        Position newPosition = marsRover.move(command);

        Assertions.assertThat(newPosition).isEqualTo(Position.of(expectedX, expectedY, expectedDirection));

    }


    @ParameterizedTest
    @CsvSource({
        "'f', 0, -49, NORTH",
    })
    void move_from_border_north(String command, int expectedX, int expectedY, Direction expectedDirection) {
        MarsRover marsRover = new MarsRoverImpl(0, 50, Direction.NORTH);
        Position newPosition = marsRover.move(command);

        Assertions.assertThat(newPosition).isEqualTo(Position.of(expectedX, expectedY, expectedDirection));

    }

    @ParameterizedTest
    @CsvSource({
        "'f', 0, 50, SOUTH",
    })
    void move_from_border_South(String command, int expectedX, int expectedY, Direction expectedDirection) {
        MarsRover marsRover = new MarsRoverImpl(0, -49, Direction.SOUTH);
        Position newPosition = marsRover.move(command);

        Assertions.assertThat(newPosition).isEqualTo(Position.of(expectedX, expectedY, expectedDirection));

    }

    @ParameterizedTest
    @CsvSource({
        "'b', -49, 0, EAST",
    })
    void move_from_border_East(String command, int expectedX, int expectedY, Direction expectedDirection) {
        MarsRover marsRover = new MarsRoverImpl(50, 0, Direction.EAST);
        Position newPosition = marsRover.move(command);

        Assertions.assertThat(newPosition).isEqualTo(Position.of(expectedX, expectedY, expectedDirection));

    }

    @ParameterizedTest
    @CsvSource({
        "'b', 50, 0, WEST",
    })
    void move_from_border_West(String command, int expectedX, int expectedY, Direction expectedDirection) {
        MarsRover marsRover = new MarsRoverImpl(-49, 0, Direction.WEST);
        Position newPosition = marsRover.move(command);

        Assertions.assertThat(newPosition).isEqualTo(Position.of(expectedX, expectedY, expectedDirection));

    }
}

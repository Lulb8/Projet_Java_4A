package com.esiea.tp4A;

import com.esiea.tp4A.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LaserTest {

    PlanetMapImpl map = new PlanetMapImpl();

    @ParameterizedTest
    @CsvSource({
        "'sff', 0, 2, NORTH, 0, 2, NORTH",
        "'rflsf', 1, 1, NORTH, 1, 1, NORTH",
        "'rrfsf', 0, -2, NORTH, 0, -2, SOUTH",
        "'rrsff', 0, -2, NORTH, 0, -2, SOUTH",
        "'rfsff', 3, 0, NORTH, 3, 0, EAST",
        "'lbfsff', -2, 0, NORTH, -2, 0, WEST",
    })
    void rover_destroy_obstacle(String command, int obsX, int obsY, Direction obsDirection, int expX, int expY, Direction expDirection) {
        map.addObstacle(obsX, obsY, obsDirection);
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH, map);
        Position newPosition = marsRover.move(command);
        Assertions.assertThat(newPosition).isEqualTo(Position.of(expX, expY, expDirection));
    }
}


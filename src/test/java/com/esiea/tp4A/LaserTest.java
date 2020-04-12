package com.esiea.tp4A;

import com.esiea.tp4A.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LaserTest {

    PlanetMapImpl map = new PlanetMapImpl();
    int laserRange = 2;

    @ParameterizedTest
    @CsvSource({
        "'sff', 0, 2, 0, 2, NORTH",
        "'rflsf', 1, 1, 1, 1, NORTH",
        "'rrfsf', 0, -2, 0, -2, SOUTH",
        "'rrsff', 0, -2, 0, -2, SOUTH",
        "'rfsff', 3, 0, 3, 0, EAST",
        "'lbfsff', -2, 0, -2, 0, WEST",
        "'fsffb', 0, 2, 0, 2, NORTH",
        "'sfff', 0, 3, 0, 2, NORTH"
    })
    void rover_destroy_obstacle(String command, int obsX, int obsY, int expX, int expY, Direction expDirection) {
        map.addObstacle(obsX, obsY);
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH, map, laserRange);
        Position newPosition = marsRover.move(command);
        Assertions.assertThat(newPosition).isEqualTo(Position.of(expX, expY, expDirection));
    }

    @ParameterizedTest
    @CsvSource({
        "'sff', 0, 2, 0, 2, NORTH, 2",
        "'rrs', 0, -2, 0, 0, SOUTH, 2",
        "'rflsf', 1, 1, 1, 1, NORTH, 2",
        "'rrfsf', 0, -2, 0, -2, SOUTH, 2",
        "'rrsff', 0, -2, 0, -2, SOUTH, 2",
        "'rfsff', 3, 0, 3, 0, EAST, 2",
        "'lbfsff', -2, 0, -2, 0, WEST, 2",
        "'fffrffsffffrfl', 6, 3, 6, 2, EAST, 4",
        "'fffrffsffffrflflflf', 6, 3, 6, 3, WEST, 4",
        "'fflffrbfflsffrbb', -4, 3, -4, 1, NORTH, 2",
        "'llfsff', 0, -3, 0, -3, SOUTH, 2",
        "'flfsff', -2, 1, -3, 1, WEST, 3",
        "'bbrfsff', 3, -2, 3, -2, EAST, 3"
    })
    void rover_destroy_obstacle_with_different_laser_range(String command, int obsX, int obsY, int expX, int expY, Direction expDirection, int laserRange) {
        map.addObstacle(obsX, obsY);
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH, map, laserRange);
        Position newPosition = marsRover.move(command);
        Assertions.assertThat(newPosition).isEqualTo(Position.of(expX, expY, expDirection));
    }

    @ParameterizedTest
    @CsvSource({
        "'s', 0, 1, 1",
        "'s', 0, 2, 2",
        "'s', 0, 10, 10",
        "'s', 0, 50, 50",
        "'rrs', 0, -1, 1",
        "'rrs', 0, -49, 50",
        "'rs', 1, 0, 1",
        "'rs', 50, 0, 50",
        "'ls', -1, 0, 1",
        "'ls', -49, 0, 50"
    })
    void check_laser_range(String command, int obsX, int obsY, int laserRange) {
        map.addObstacle(obsX, obsY);
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH, map, laserRange);
        marsRover.move(command);
        Assertions.assertThat(map.obstaclePositions()).isEmpty();
    }
}


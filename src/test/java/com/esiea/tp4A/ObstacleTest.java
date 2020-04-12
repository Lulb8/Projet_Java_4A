package com.esiea.tp4A;

import com.esiea.tp4A.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ObstacleTest {

    PlanetMapImpl map = new PlanetMapImpl();
    int laserRange = 2;

    @ParameterizedTest
    @CsvSource({
        "0,0,0,0, true",
        "10,3,9,4,false",
        "0, 1, 0, 1, true"
    })
    void creating_and_new_detect_obstacles(int obsX, int obsY, int resX, int resY, boolean expectedRes) {
        map.obstaclePositions().add(new Position.FixedPosition(obsX, obsY, Direction.NORTH));
        Assertions.assertThat(map.checkIfObstacle(resX, resY)).isEqualTo(expectedRes);
    }

    @ParameterizedTest
    @CsvSource({
        "'ff', 0, 2, 0, 1, NORTH",
        "'fflb', 0, 1, 1, 0, WEST",
        "'bb', 0, 2, 0, -2, NORTH",
    })
    void rover_detect_obstacle(String command, int obsX, int obsY, int expX, int expY, Direction expDirection) {
        map.addObstacle(obsX, obsY);
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH, map, laserRange);
        Position newPosition = marsRover.move(command);
        Assertions.assertThat(newPosition).isEqualTo(Position.of(expX, expY, expDirection));
    }
}

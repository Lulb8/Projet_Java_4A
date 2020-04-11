package com.esiea.tp4A;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.MarsRover;
import com.esiea.tp4A.domain.MarsRoverImpl;
import com.esiea.tp4A.domain.PlanetMapImpl;
import com.esiea.tp4A.domain.Position;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ObstacleTest {

    PlanetMapImpl map = new PlanetMapImpl();

    @ParameterizedTest
    @CsvSource({
        "0,0,0,0, NORTH, true",
        "10,3,9,4, NORTH,false",
        "0, 1, 0, 1, NORTH, true"
    })
    void creating_and_new_detect_obstacles(int obsX, int obsY, int resX, int resY, Direction direction, boolean expectedRes) {
        //map.generateObstacles();
        map.obstaclePositions().add(new Position.FixedPosition(obsX, obsY, Direction.NORTH));
        Assertions.assertThat(map.checkIfObstacle(resX, resY, direction)).isEqualTo(expectedRes);
    }

    @ParameterizedTest
    @CsvSource({
        "'ff', 0, 2, NORTH, 0, 1, NORTH",
        "'fflb', 0, 1, NORTH, 1, 0, WEST",
        "'bb', 0, 2, NORTH, 0, -2, NORTH",
    })
    void rover_detect_obstacle(String command, int obsX, int obsY, Direction obsDirection, int expX, int expY, Direction expDirection) {
        map.addObstacle(obsX, obsY, obsDirection);
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH, map);
        Position newPosition = marsRover.move(command);
        Assertions.assertThat(newPosition).isEqualTo(Position.of(expX, expY, expDirection));
    }
}

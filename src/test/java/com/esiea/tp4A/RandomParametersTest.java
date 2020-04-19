package com.esiea.tp4A;

import com.esiea.tp4A.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RandomParametersTest {

    @Test
    void random_map() {
        PlanetMapImpl map = new PlanetMapImpl(100, 15);
        Assertions.assertThat(map.obstaclePositions().size()).isEqualTo(15);
    }

    @ParameterizedTest
    @CsvSource({
        "100",
        "300",
        "600"
    })
    void random_map_15pct_obstacles(int mapSize) {
        int numberOfObstacles = (int) (mapSize * 0.15);
        PlanetMapImpl map = new PlanetMapImpl(mapSize, numberOfObstacles);
        Assertions.assertThat(map.obstaclePositions().size()).isEqualTo(numberOfObstacles);
    }

    @Test
    void random_map_with_rover() {
        PlanetMapImpl map = new PlanetMapImpl(100, 15);
        map.addRandomRovers(50);
        Assertions.assertThat(map.obstaclePositions().size()).isEqualTo(15);
        Assertions.assertThat(map.getRovers().size()).isEqualTo(50);
    }

    @ParameterizedTest
    @CsvSource({
        "'s', 5",
        "'s', 30"
    })
    void random_laser_range(String command, int laserRange) {
        PlanetMapImpl map = new PlanetMapImpl(100, 0);
        MarsRover marsRover = new MarsRoverImpl(0, 0, Direction.NORTH, map);
        marsRover.configureLaserRange(laserRange);
        map.addOneObstacle(0, laserRange);
        Position newPosition = marsRover.move(command);
        Assertions.assertThat(map.obstaclePositions()).isEmpty();
    }
}

package com.esiea.tp4A;

import java.util.HashSet;
import java.util.Set;
import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.MarsRover;
import com.esiea.tp4A.domain.MarsRoverImpl;
import com.esiea.tp4A.domain.PlanetMapImpl;
import com.esiea.tp4A.domain.Position;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ObstacleTest {

    @Test
    void detect_obstacles() {
        MarsRover marsrover = new MarsRoverImpl(0, 0, Direction.NORTH);
        Position newposition = marsrover.move("fflb");

        if (newposition == PlanetMapImpl.obstaclePositions())
            Assertions.assertThat(newposition).isEqualTo(Position.of(1, 0, Direction.WEST));
    }
}

package com.esiea.tp4A;

import java.util.HashSet;
import java.util.Set;
import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.MarsRover;
import com.esiea.tp4A.domain.MarsRoverImpl;
import com.esiea.tp4A.domain.PlanetMapImpl;
import com.esiea.tp4A.domain.Position;
import org.junit.jupiter.api.Test;

public class ObstacleTest {

    @Test
    void detect_obstacles() {
        MarsRover marsrover = new MarsRoverImpl(0, 0, Direction.NORTH);
        Position pos1 = Position.of(0, 1, Direction.NORTH);
        Set<Position> hash_Set = new HashSet<Position>();
        hash_Set.add(pos1);
        PlanetMapImpl.obstaclePositions();
    }
}

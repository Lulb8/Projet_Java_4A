package com.esiea.tp4A;

import java.util.HashSet;
import java.util.Set;
import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.MarsRover;
import com.esiea.tp4A.domain.MarsRoverImpl;
import com.esiea.tp4A.domain.PlanetMap;
import com.esiea.tp4A.domain.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ObstacleTest {

    @Test
    void detect_obstacles() {
        MarsRover marsrover = new MarsRoverImpl(0, 0, Direction.NORTH);
        Position pos = marsrover.move("f,f,l,b");
        Position pos1 = Position.of(0, 1, Direction.NORTH);
        Set<Position> hash_Set = new HashSet<Position>();
        hash_Set.add(pos1);
        // PlanetMap.obstaclePositions(hash_Set);
        PlanetMap pl = new PlanetMap() {

            @Override
            public Set<Position> obstaclePositions() {
                // TODO Auto-generated method stub

                return hash_Set;
            }
        };

        if (marsrover == pl.obstaclePositions())
            Assertions.assertThat(pos).isEqualTo(Position.of(1, 0, Direction.WEST));

    }
}

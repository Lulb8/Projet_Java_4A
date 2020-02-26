package com.esiea.tp4A;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.MarsRover;
import com.esiea.tp4A.domain.MarsRoverImpl;
import com.esiea.tp4A.domain.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

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
}

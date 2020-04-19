package com.esiea.tp4A.api;

import com.esiea.tp4A.domain.MarsRoverImpl;
import com.esiea.tp4A.domain.Obstacle;
import com.esiea.tp4A.domain.Position;

import java.util.HashSet;
import java.util.Set;

public interface GameAPI {
    Position getPlayerPosition(int playerID);
    void getRoverPosition();
    Set<Position> getCloseObstacles(int playerID);
    Set<MarsRoverImpl> getCloseRovers(int playerID);
    int getLaserRange();
    void moveRoverGame(int playerID, String command);
    void shootRoverGame(int playerID);
    boolean getRoverCondition(int playerID);
    void addNewPlayer(String playerName);
    int getPlayer(String playerName);
}

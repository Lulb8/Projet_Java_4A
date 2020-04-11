package com.esiea.tp4A.domain;

public class Laser {

    public void laser(char lasershoot, int rangelaser, Position marsroverposition, int maxpositionObstacles, Direction direction) {

        /*Initialisation du tableau de positions d'obstacles*/
        Position N;// portée maximale d'un obstacle à définir;
        int i = 0, max = maxpositionObstacles;//nbre d'obstacles dans le tableau;
        Position[] tableobstaclesPositions = new Position[max];


        /* Parcourir le tableau de différentes positions d'obstacles*/

        for (i = 0; i < max; i++) {

            //on parcours chaque valeur de la longueur du laser de façon à ne pas dépasser sa portée maximale du laser
            for (int laserlength = 1; laserlength <= rangelaser; laserlength++) {

                // si la position du robot est celle d'un obstacle en X
                if (marsroverposition.getX() == tableobstaclesPositions[i].getX()) {
                    lasershoot = 's'; // on active le laser;
                    switch (direction) {// il y'a deux cas
                        case NORTH:
                            if (marsroverposition.getY() + laserlength == tableobstaclesPositions[i].getY())
                                PlanetMapImpl.obstaclePositions().remove(tableobstaclesPositions[i]);
                            break;
                        case SOUTH:
                            if (marsroverposition.getY() - laserlength == tableobstaclesPositions[i].getY())
                                PlanetMapImpl.obstaclePositions().remove(tableobstaclesPositions[i]);
                            break;
                    }

                    // si la position du robot est celle de l'obstacle en Y
                    if (marsroverposition.getY() == tableobstaclesPositions[i].getY()) {
                        switch (direction) {// il y'a deux cas
                            case EAST:
                                if (marsroverposition.getX() + laserlength == tableobstaclesPositions[i].getX())
                                    PlanetMapImpl.obstaclePositions().remove(tableobstaclesPositions[i]);
                                break;

                            case WEST:
                                if (marsroverposition.getX() - laserlength == tableobstaclesPositions[i].getX())
                                    PlanetMapImpl.obstaclePositions().remove(tableobstaclesPositions[i]);
                                break;
                        }
                    }

                    // si la position du robot est différente de celle d'un obstacle
                    else {
                        System.out.println("il n'y a pas d'obstacles");
                    }
                }

            }
        }

    }

}

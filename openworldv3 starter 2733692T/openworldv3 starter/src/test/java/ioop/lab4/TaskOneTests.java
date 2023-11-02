package ioop.lab4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import openworld.Coordinates;
import openworld.World;

public class TaskOneTests {

    @Test
    public void testSameCoordinates() {
        Coordinates coordinates = new Coordinates(2, 3);
        Coordinates sameCoordinates = new Coordinates(2, 3);
        Coordinates differentCoordinates = new Coordinates(4, 5);
        assertTrue(coordinates.equals(sameCoordinates));
        assertFalse(coordinates.equals(differentCoordinates));
    }

    @Test
    public void testAddCoordinates() {
        Coordinates coordinates = new Coordinates(2, 3);
        Coordinates vectorToAdd = new Coordinates(1, -1);
        Coordinates expectedCoordinates = new Coordinates(3, 2);
        coordinates.addCoordinates(vectorToAdd);
        assertTrue(coordinates.equals(expectedCoordinates));
    }

    @Test
    public void testGetNextStepTo() {
        Coordinates coordinates = new Coordinates(2, 3);
        Coordinates northDestination = new Coordinates(5, 4);
        Coordinates southDestination = new Coordinates(0, 2);
        Coordinates eastDestination = new Coordinates(3, 7);
        Coordinates westDestination = new Coordinates(1, -3);
        Coordinates stationaryDestination = new Coordinates(2, 3);

        Coordinates northStep = coordinates.getNextStepTo(northDestination);
        Coordinates southStep = coordinates.getNextStepTo(southDestination);
        Coordinates eastStep = coordinates.getNextStepTo(eastDestination);
        Coordinates westStep = coordinates.getNextStepTo(westDestination);
        Coordinates stationaryStep = coordinates.getNextStepTo(stationaryDestination);

        assertEquals(Coordinates.NORTH_VECTOR, northStep);
        assertEquals(Coordinates.SOUTH_VECTOR, southStep);
        assertEquals(Coordinates.EAST_VECTOR, eastStep);
        assertEquals(Coordinates.WEST_VECTOR, westStep);
        assertEquals(Coordinates.STATIONARY, stationaryStep);
    }

    public void testFindSafeMoveNorth() {
        World world = new World(3, 3);
        Coordinates coordinates = new Coordinates(1, 1); // Safe move should be North
        Coordinates safeMove = coordinates.findSafeMove(world);
        assertEquals(Coordinates.NORTH_VECTOR, safeMove);
    }

    @Test
    public void testFindSafeMoveEast() {
        World world = new World(3, 3);
        Coordinates coordinates = new Coordinates(3, 1); // Safe move should be East
        Coordinates safeMove = coordinates.findSafeMove(world);
        assertEquals(Coordinates.EAST_VECTOR, safeMove);
    }

    @Test
    public void testFindSafeMoveSouth() {
        World world = new World(3, 3);
        Coordinates coordinates = new Coordinates(3, 3); // Safe move should be South
        Coordinates safeMove = coordinates.findSafeMove(world);
        assertEquals(Coordinates.SOUTH_VECTOR, safeMove);
    }

    @Test
    public void testFindSafeMoveWest() {
        World world = new World(0, 1);
        Coordinates coordinates = new Coordinates(0, 1); // Safe move should be West
        Coordinates safeMove = coordinates.findSafeMove(world);
        System.out.println("SAFE WEST: "+ safeMove.getX()+ " "+ safeMove.getY());
        assertEquals(Coordinates.WEST_VECTOR, safeMove);
    }

    @Test
    public void testFindSafeMoveStationary() {
        World world = new World(0, 0);
        Coordinates coordinates = new Coordinates(0, 0); // Safe move should be Stationary
        Coordinates safeMove = coordinates.findSafeMove(world);
        assertEquals(Coordinates.STATIONARY, safeMove);
    }

}

package openworld;

import java.util.ArrayList;
import openworld.characters.Healer;
import openworld.characters.Wizard;
import openworld.collectibles.Items;
import openworld.collectibles.Potion;
import openworld.collectibles.Wand;

public class Coordinates {
    public final static Coordinates NORTH_VECTOR = new Coordinates(1, 0);
    public final static Coordinates SOUTH_VECTOR = new Coordinates(-1, 0);
    public final static Coordinates EAST_VECTOR = new Coordinates(0, 1);
    public final static Coordinates WEST_VECTOR = new Coordinates(0, -1);
    public final static Coordinates STATIONARY = new Coordinates(0, 0);

    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
        System.out.println("boobs");
    }

    public void addCoordinates(Coordinates vector) {
        x += vector.getX();
        y += vector.getY();
    }

    public Coordinates findSafeMove(World world) {
        if (x + 1 <= world.getxDimension()) {
            return NORTH_VECTOR;
        } else if (y + 1 <= world.getyDimension()) {
            return EAST_VECTOR;
        } else if (x - 1 >= 0) {
            return SOUTH_VECTOR;
        } else if (y - 1 >= 0) {
            return WEST_VECTOR;
        }
        return STATIONARY;
    }

    public Coordinates getNextStepTo(Coordinates destination) {
        int xDiff = destination.getX() - x;
        int yDiff = destination.getY() - y;
        if (xDiff == 0 && yDiff == 0) {
            return STATIONARY;
        }
        if (Math.abs(xDiff) >= Math.abs(yDiff)) {
            if (xDiff > 0) {
                return NORTH_VECTOR;
            } else {
                return SOUTH_VECTOR;
            }
        } else {
            if (yDiff > 0) {
                return EAST_VECTOR;
            } else {
                return WEST_VECTOR;
            }
        }
    }

    // Task 1.3
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        Coordinates check = (Coordinates) obj;
        if (x == check.getX() && y == check.getY()) {
            return true;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coord(" + x + "," + y + ")";
    }

    public ArrayList<String> printSafeMove(World world) {
        ArrayList<String> safeMoves = new ArrayList<String>();
        if (x + 1 <= world.getxDimension()) {
            safeMoves.add("NORTH");
        }
        if (y + 1 <= world.getyDimension()) {
            safeMoves.add("EAST");
        }
        if (x - 1 >= 0) {
            safeMoves.add("SOUTH");
        }
        if (y - 1 >= 0) {
            safeMoves.add("WEST");
        }

        for (int i = 0; i < world.getNonPlayerCharacters().size(); i++) {
            if (world.getAdventurer().getLocation().equals(world.getNonPlayerCharacters().get(i).getLocation())) {
                if (world.getNonPlayerCharacters().get(i) instanceof Healer) {
                    safeMoves.add("HEAL");
                }
                if (world.getNonPlayerCharacters().get(i) instanceof Wizard) {
                    safeMoves.add("GAIN SPELL");
                }
            }
        }

        for (int i = 0; i < world.getAdventurer().getItem().length; i++) {
            if (world.getAdventurer().getSingleItem(i) instanceof Potion) {
                safeMoves.add("USE POTION");
            }
        }
        if (safeMoves.size() == 0) {
            safeMoves.add("NONE");
        }
        return safeMoves;
    }
}

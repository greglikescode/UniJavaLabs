package openworld.terrain;

import openworld.Coordinates;
import openworld.Damage;
import openworld.DamageType;
import openworld.World;
import openworld.adventurer.Adventurer;
import openworld.entityTypes.WorldEntity;

public class Terrain extends WorldEntity{

    public Terrain(String name, Coordinates location, int maxHealth, World world, Damage attack) {
        super(name, location, maxHealth, world, attack);
    }

    public static void main(String[] args) {
        World zorpWorld = new World(15, 15);
        Damage punch = new Damage(10, DamageType.PHYSICAL);
        Adventurer player1 = new Adventurer("Bob", new Coordinates(5, 5), 100, zorpWorld, punch);
        Mountain mountain = new Mountain("Mt Everest", new Coordinates(5, 5), 1000, zorpWorld, null);
        Volcano volcano = new Volcano("Mt Vesuvius", new Coordinates(5, 5), 1000, zorpWorld, null);

        System.out.println(player1.getCurrentHealth());
        System.out.println(player1.getLocation().getX()+","+player1.getLocation().getY());
        mountain.encounter(player1);
        System.out.println(player1.getCurrentHealth());
        System.out.println(player1.getLocation().getX()+","+player1.getLocation().getY());
        volcano.encounter(player1);
        System.out.println(player1.getCurrentHealth());
        System.out.println(player1.getLocation().getX()+","+player1.getLocation().getY());
        
    }

}

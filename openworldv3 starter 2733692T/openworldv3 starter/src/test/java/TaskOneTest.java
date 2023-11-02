import static org.junit.Assert.assertEquals;

import org.junit.Test;

import openworld.Coordinates;
import openworld.Damage;
import openworld.World;
import openworld.Adventurer.Adventurer;
import openworld.characters.Healer;
import openworld.characters.NPC;
import openworld.characters.Wizard;
import openworld.monsters.Monster;
import openworld.terrain.Mountain;
import openworld.terrain.Volcano;
import openworld.monsters.*;

public class TaskOneTest {
    @Test
    public void mountainTest()
    {
        Coordinates location = new Coordinates(0, 0);
        World world = new World(1, 1); // Assuming these dimensions, adjust as necessary
        Damage attack = new Damage(0, null); // Assuming the Mountain does not attack by default

        Mountain mountain = new Mountain("Everest", location, 100, world, attack);
        Adventurer adventurer = new Adventurer("Bob", location, 100, world, attack);
        
        mountain.encounter(adventurer);
        assertEquals(adventurer.getCurrentHealth(),90);
    }

    @Test
    public void volcanoTest()
    {
        Coordinates location = new Coordinates(0, 0);
        Coordinates expected = new Coordinates(1, 0);
        World world = new World(1, 1); // Assuming these dimensions, adjust as necessary
        Damage attack = new Damage(0, null); // Assuming the Mountain does not attack by default

        Volcano v = new Volcano("v", location, 100, world, attack);
        Adventurer adventurer = new Adventurer("Bob", location, 100, world, attack);
        v.encounter(adventurer);
        System.out.println(adventurer.getLocation().getX()+" "+ adventurer.getLocation().getY());
        assertEquals(adventurer.getLocation(), expected);   
    }

    @Test
    public void generateMonstersTest()
    {
        World world = new World(7, 7);
        
        assertEquals(7,world.getxDimension());
        assertEquals(7, world.getyDimension());
        world.generateMonsters();
        assertEquals(9,world.getMonsters().size());
        int skeletonCount =0;
        int blobCount=0;
        for(Monster m:world.getMonsters())
        {
            if (m instanceof Skeleton)
            {
                skeletonCount++;
            }
            else if(m instanceof Blob)
            {
                blobCount++;
            }
        }
        assertEquals(5, skeletonCount);
        assertEquals(4,blobCount);
    }

    @Test
    public void generateCharactersTest()
    {
        World world = new World(7, 7);
        Coordinates location = new Coordinates(0, 0);
        Damage attack = new Damage(0, null);
        assertEquals(7,world.getxDimension());
        assertEquals(7, world.getyDimension());
        Adventurer adventurer = new Adventurer("Bob", location, 100, world, attack);
        world.setAdventurer(adventurer);
        world.initaliseWorld();
        assertEquals(4,world.getNonPlayerCharacters().size());
        int healerCount=0;
        int wizardCount=0;
        for(NPC npc:world.getNonPlayerCharacters())
        {
            if (npc instanceof Healer)
            {
                healerCount++;
            }
            else if(npc instanceof Wizard)
            {
                wizardCount++;
            }
        }
        world.printWorld();
        assertEquals(2,healerCount);
        assertEquals(2,wizardCount);
    }
}

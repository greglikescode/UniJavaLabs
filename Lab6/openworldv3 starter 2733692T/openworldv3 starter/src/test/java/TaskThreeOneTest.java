import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import openworld.AlphabeticalSort;
import openworld.Coordinates;
import openworld.Damage;
import openworld.DamageType;
import openworld.LevelSort;
import openworld.World;
import openworld.characters.NPC;
import openworld.entityTypes.WorldEntity;
import openworld.monsters.Monster;

public class TaskThreeOneTest {
    
    @Test 
    public void alphabeticalSortTest()
    {
        // Create some mock Coordinates, World, and Damage objects
        Coordinates coord = new Coordinates(0, 0);
        World world = new World(10, 10);
        Damage damage = new Damage(5, DamageType.PHYSICAL);

        // Create a list of NPCs with different names
        NPC npc1 = new NPC("Alice", coord, 100, world, damage, coord);
        NPC npc2 = new NPC("Bob", coord, 100, world, damage, coord);
        NPC npc3 = new NPC("Charlie", coord, 100, world, damage, coord);

        List<NPC> npcList = Arrays.asList(npc3, npc1, npc2);

        // Sort the list using NPCNameComparator
        Collections.sort(npcList, new AlphabeticalSort());

        // Verify that the list is sorted by name
        assertEquals("Alice", npcList.get(0).getName());
        assertEquals("Bob", npcList.get(1).getName());
        assertEquals("Charlie", npcList.get(2).getName());
    }

        


    @Test 
    public void levelSortTest()
    {
        // Create some mock Coordinates, World, and Damage objects
        Coordinates coord = new Coordinates(0, 0);
        World world = new World(10, 10);
        Damage damage = new Damage(5, DamageType.PHYSICAL);

        // Create a list of Monsters with different levels and names
        Monster monster1 = new Monster("Zombie", coord, 100, world, damage, 1);
        monster1.gainXp(3); // This should level up the monster to level 2
        Monster monster2 = new Monster("Skeleton", coord, 100, world, damage, 1);
        Monster monster3 = new Monster("Dragon", coord, 100, world, damage, 1);
        monster3.gainXp(6); // This should level up the monster to level 3

        List<Monster> monsterList = Arrays.asList(monster1, monster2, monster3);

        // Sort the list using LevelSort comparator
        Collections.sort(monsterList, new LevelSort());

        // Verify that the list is sorted by level, and then by name
        assertEquals("Skeleton", monsterList.get(0).getName());
        assertEquals("Zombie", monsterList.get(1).getName());  // Same level as Skeleton, but comes after in alphabetical order
        assertEquals("Dragon", monsterList.get(2).getName());   // Highest level
    }
    

    @Test 
    public void turnAdventurerMoveTest()
    {
        World world = new World(7, 7);   
        world.initaliseWorld();
        world.run();
        assertEquals(new Coordinates(1,0),world.getAdventurer().getLocation());

    }




    @Test 
    public void resolveMoveTest()
    {
        World world = new World(7, 7);   
    }

    @Test 
    public void vulnerabilityTest()
    {
        WorldEntity w = new WorldEntity("B", null, 100, null, null);
        w.takeDamage(new Damage(10, DamageType.FIRE));
        assertEquals(90,w.getCurrentHealth());
        w.setDamageVulnerability(DamageType.FIRE, 110);
        w.takeDamage(new Damage(10, DamageType.FIRE));
        assertEquals(79, w.getCurrentHealth());
    
    }




}

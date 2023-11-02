package ioop.lab4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import openworld.Coordinates;
import openworld.Damage;
import openworld.DamageType;
import openworld.World;
import openworld.Adventurer.Adventurer;
import openworld.entityTypes.WorldEntity;
import openworld.monsters.Monster;

public class TaskTwoTwoTests {
     

    @Test
    public void testTakeTurn() {
        Coordinates location = new Coordinates(1,1);
        Damage attack = new Damage(10, DamageType.FIRE);
        World world = new World(5, 5);
        world.setAdventurer(new Adventurer("Adventurer1", new Coordinates(3, 1), 100, world, new Damage(10, DamageType.ELECTRIC)));
        Monster monster = new Monster("Monster1", location, 100, world, attack, 2);

        // Test that takeTurn behaves as expected
        monster.takeTurn(); // The monster should not move since it's not awake yet
        assertEquals(0, monster.getStepTimer());
        // Set the monster to awake and call takeTurn, it should move
        monster.setAwake(true);
        monster.takeTurn();
        assertTrue(monster.getStepTimer() > 0);
        assertEquals(monster.getLocation(), new Coordinates(1, 1));
        monster.takeTurn();
        assertEquals(monster.getLocation(), new Coordinates(2, 1));
    }

    @Test
    public void testGainXP() {
        Coordinates location = new Coordinates(2, 3);
        Damage attack = new Damage(10, DamageType.FIRE);
        World world = new World(5, 5);
        Monster monster = new Monster("Monster1", location, 100, world, attack, 2);

        // Test gaining XP and leveling up
        assertEquals(1, monster.getLevel());
        assertEquals(0, monster.getXp());

        monster.gainXp(5); // Gains 5 XP
        assertEquals(2, monster.getLevel());
        assertEquals(3, monster.getXp());

        monster.gainXp(5); // Gains 5 XP
        assertEquals(3, monster.getLevel());
        assertEquals(4, monster.getXp());
        monster.gainXp(10); // Gains 10 XP, which is enough for another level up
        assertEquals(4, monster.getLevel());
        assertEquals(6, monster.getXp());
    }

    @Test
    public void testEncounter() {
        Coordinates location = new Coordinates(2, 3);
        Damage attack = new Damage(10, DamageType.FIRE);
        World world = new World(5, 5);
        Monster monster = new Monster("Monster1", location, 100, world, attack, 2);

        WorldEntity adventurer = new WorldEntity("Adventurer", new Coordinates(3, 3), 100, world, new Damage(15, DamageType.PHYSICAL));

        // Monster is initially not awake, so encountering should make it awake
        assertFalse(monster.isAwake());
        monster.encounter(adventurer);
        assertTrue(monster.isAwake()); 
    }
    
}

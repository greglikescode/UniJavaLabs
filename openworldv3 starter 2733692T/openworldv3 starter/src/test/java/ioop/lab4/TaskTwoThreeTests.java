package ioop.lab4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;



import org.junit.Test;


import openworld.Coordinates;
import openworld.Damage;
import openworld.DamageType;
import openworld.World;
import openworld.Adventurer.Adventurer;
import openworld.entityTypes.WorldEntity;
import openworld.monsters.Skeleton;
import openworld.monsters.Blob;


public class TaskTwoThreeTests {

    @Test
    public void testMerge() {
        Coordinates location = new Coordinates(2, 3);
        Damage attack = new Damage(10, DamageType.FIRE);
        World world = new World(5, 5);
        Blob blob = new Blob("Blob1", location, 100, world, attack, 2);

        Coordinates otherLocation = new Coordinates(3, 3);
        Damage otherAttack = new Damage(15, DamageType.PHYSICAL);
        Blob otherBlob = new Blob("Blob2", otherLocation, 150, world, otherAttack, 2);

        int initialMaxHealth = blob.getMaxHealth();
        int initialXP = blob.getXp();

        blob.merge(otherBlob);

        // Test if the blob correctly merged with the other blob
        assertEquals(initialMaxHealth + otherBlob.getMaxHealth(), blob.getMaxHealth());
        assertEquals(250, blob.getCurrentHealth());
        assertEquals(initialXP + otherBlob.getXp(), blob.getXp());
        assertEquals(0,otherBlob.getCurrentHealth());
    }

    @Test
    public void testEncounterAndMerge() {
        Coordinates location = new Coordinates(2, 3);
        Damage attack = new Damage(10, DamageType.FIRE);
        World world = new World(5, 5);
        Blob blob = new Blob("Blob1", location, 100, world, attack, 2);

        Coordinates otherLocation = new Coordinates(3, 3);
        Damage otherAttack = new Damage(15, DamageType.PHYSICAL);
        Blob otherBlob = new Blob("Blob2", otherLocation, 150, world, otherAttack, 2);

        int initialMaxHealth = blob.getMaxHealth();
        int initialXP = blob.getXp();

        blob.encounter(otherBlob);

        // Test if the blob correctly merged with the other blob when encountering
        assertEquals(initialMaxHealth + otherBlob.getMaxHealth(), blob.getMaxHealth());
        assertEquals(250, blob.getCurrentHealth());
        assertEquals(initialXP + otherBlob.getXp(), blob.getXp());
        assertEquals(0,otherBlob.getCurrentHealth());
        assertFalse(otherBlob.conscious);
    }

    @Test
    public void testEncounterSkeleton() {
        Coordinates location = new Coordinates(2, 3);
        Damage attack = new Damage(10, DamageType.FIRE);
        World world = new World(5, 5);
        Blob blob = new Blob("Blob1", location, 100, world, attack, 2);

        WorldEntity adventurer = new Skeleton("Adventurer", new Coordinates(3, 3), 100, world, new Damage(15, DamageType.PHYSICAL),2);

        int initialMaxHealth = blob.getMaxHealth();
        int initialCurrentHealth = blob.getCurrentHealth();
        int initialXP = blob.getXp();

        blob.encounter(adventurer);

        // Test if the blob didn't merge with a non-Blob object
        assertEquals(initialMaxHealth, blob.getMaxHealth());
        assertEquals(initialCurrentHealth, blob.getCurrentHealth());
        assertEquals(initialXP, blob.getXp());
    }

    @Test
    public void testEncounterAdventurer() {
        Coordinates location = new Coordinates(2, 3);
        Damage attack = new Damage(10, DamageType.FIRE);
        World world = new World(5, 5);
        Blob blob = new Blob("Blob1", location, 100, world, attack, 2);

        WorldEntity adventurer = new Adventurer("Adventurer", new Coordinates(3, 3), 100, world, new Damage(15, DamageType.PHYSICAL));

        int initialMaxHealth = blob.getMaxHealth();
        int initialCurrentHealth = blob.getCurrentHealth();
        int initialXP = blob.getXp();

        blob.encounter(adventurer);

        // Test if the blob didn't merge with a non-Blob object
        assertEquals(initialMaxHealth, blob.getMaxHealth());
        assertEquals(initialCurrentHealth, blob.getCurrentHealth());
        assertEquals(initialXP, blob.getXp());
    }
}
package ioop.lab4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


import openworld.Coordinates;
import openworld.Damage;
import openworld.DamageType;
import openworld.World;
import openworld.Adventurer.Adventurer;
import openworld.entityTypes.WorldEntity;
import openworld.monsters.Blob;
import openworld.monsters.Monster;

public class TaskThreeOne {
    
    @Test
    public void testAddAttack() 
    {
        Coordinates location = new Coordinates(2, 3);
        Damage initialAttack = new Damage(10, DamageType.FIRE);
        World world = new World(5, 5);
        Adventurer adventurer = new Adventurer("Adventurer1", location, 100, world, initialAttack);

        Damage newAttack = new Damage(20, DamageType.PHYSICAL);

        adventurer.addAttack(newAttack);

        // Verify that the new attack was added
        assertEquals(newAttack, adventurer.getAttacks()[1]);
    }

    @Test
    public void testAttack() {
        Coordinates location = new Coordinates(2, 3);
        Damage initialAttack = new Damage(10, DamageType.FIRE);
        Damage physicalAttack = new Damage(20, DamageType.PHYSICAL);
        Damage fireAttack = new Damage(15, DamageType.FIRE);
        World world = new World(5, 5);

        Adventurer adventurer = new Adventurer("Adventurer1", location, 100, world, initialAttack);
        Adventurer targetAdventurer = new Adventurer("Target", new Coordinates(3, 3), 100, world, initialAttack);
        adventurer.addAttack(physicalAttack);

        int targetHealthBeforeAttack = targetAdventurer.getCurrentHealth();

        // Perform the attack
        adventurer.attack(targetAdventurer);

        // Verify that the target's health has decreased by the total damage from both attacks
        int totalDamage = initialAttack.getAmount() + physicalAttack.getAmount();
        assertEquals(targetHealthBeforeAttack - totalDamage, targetAdventurer.getCurrentHealth());

        // Verify that the target's health has not been affected by the fire attack
        adventurer.addAttack(fireAttack);
        targetHealthBeforeAttack = targetAdventurer.getCurrentHealth();
        adventurer.attack(targetAdventurer);
        assertEquals(targetHealthBeforeAttack-45, targetAdventurer.getCurrentHealth());
    }

    @Test
    public void testMonsterWinsBattle() {
        Coordinates locationMonster = new Coordinates(2, 3);
        Damage monsterAttack = new Damage(10, DamageType.FIRE);
        Coordinates locationWorldObject = new Coordinates(3, 3);
        Damage worldObjectAttack = new Damage(15, DamageType.PHYSICAL);
        World world = new World(5, 5);

        Monster monster = new Monster("Monster1", locationMonster, 100, world, monsterAttack, 2);
        WorldEntity worldObject = new WorldEntity("WorldObject1", locationWorldObject, 100, world, worldObjectAttack);

        int monsterInitialHealth = monster.getCurrentHealth();


        world.battle(monster, worldObject);

        // Verify that the Monster has won the battle, and its health is less than the initial health
        assertTrue(monster.getCurrentHealth() < monsterInitialHealth);
        // Verify that the WorldObject's health is reduced to 0 or less
        assertTrue(worldObject.getCurrentHealth() == 30);
        assertTrue(monster.getCurrentHealth() == 0);
    }

    @Test
    public void testBlobWinsBattle() {
        Coordinates locationMonster = new Coordinates(2, 3);
        Damage monsterAttack = new Damage(10, DamageType.FIRE);
        Coordinates locationWorldObject = new Coordinates(3, 3);
        Damage blobObjectAttack = new Damage(35, DamageType.PHYSICAL);
        World world = new World(5, 5);

        Monster monster = new Monster("Monster1", locationMonster, 100, world, monsterAttack, 2);
        Blob blob = new Blob("Blob1", locationWorldObject, 100, world,blobObjectAttack, 2);

        world.battle(blob, monster);

        // Verify that the Monster's health is reduced to 0 
        assertTrue(monster.getCurrentHealth() == 0);
        assertTrue(monster.isConscious()==false);
        assertEquals(80, blob.getCurrentHealth());

        monster = new Monster("Monster1", locationMonster, 100, world, monsterAttack, 2);
        blob = new Blob("Blob1", locationWorldObject, 100, world,blobObjectAttack, 2);

        // check one more hit in if the monster goes first
        world.battle(monster, blob);
        assertTrue(monster.getCurrentHealth() == 0);
        assertTrue(monster.isConscious()==false);
        assertEquals(70, blob.getCurrentHealth());
    }

    





}
    


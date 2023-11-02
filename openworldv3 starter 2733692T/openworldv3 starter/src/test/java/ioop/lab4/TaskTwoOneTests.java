package ioop.lab4;
import org.junit.Test;

import openworld.Coordinates;
import openworld.Damage;
import openworld.DamageType;
import openworld.World;
import openworld.entityTypes.TravellingWorldEntity;
import openworld.entityTypes.WorldEntity;

import static org.junit.Assert.*;
public class TaskTwoOneTests 
{
    @Test
    public void testConstructor() {
        Coordinates location = new Coordinates(2, 3);
        Damage attack = new Damage(10, DamageType.FIRE);
        World world = new World(5, 5);
        WorldEntity worldObject = new WorldEntity("TestObject", location, 100, world, attack);

        assertEquals("TestObject", worldObject.getName());
        assertEquals(location, worldObject.getLocation());
        assertEquals(100, worldObject.getMaxHealth());
        assertEquals(100, worldObject.getCurrentHealth());
        assertEquals(world, worldObject.getWorld());
        assertEquals(attack, worldObject.getAttack());
    }

    @Test
    public void testTakeDamage() {
        Coordinates location = new Coordinates(2, 3);
        Damage attack = new Damage(10, DamageType.FIRE);
        World world = new World(5, 5);
        WorldEntity worldObject = new WorldEntity("TestObject", location, 100, world, attack);

        int damageAmount = 30;
        worldObject.takeDamage(new Damage(damageAmount, DamageType.FIRE));

        assertEquals(70, worldObject.getCurrentHealth());
    }

    @Test
    public void testTakeDamageWithNegativeDamage() {
        Coordinates location = new Coordinates(2, 3);
        Damage attack = new Damage(10, DamageType.FIRE);
        World world = new World(5, 5);
        WorldEntity worldObject = new WorldEntity("TestObject", location, 100, world, attack);

        int initialHealth = worldObject.getCurrentHealth();
        int damageAmount = -20;
        worldObject.takeDamage(new Damage(damageAmount, DamageType.FIRE));

        assertEquals(initialHealth, worldObject.getCurrentHealth());
    }

    @Test
    public void testAttack() {
        Coordinates location = new Coordinates(2, 3);
        Damage attack = new Damage(15, DamageType.FIRE);
        World world = new World(5, 5);
        WorldEntity worldObject = new WorldEntity("TestObject", location, 100, world, attack);

        WorldEntity target = new WorldEntity("TargetObject", new Coordinates(3, 3), 100, world, new Damage(15, DamageType.PHYSICAL));
        worldObject.attack(target);

        assertEquals(85, target.getCurrentHealth());
    }

    @Test
    public void testMove() {
        Coordinates initialLocation = new Coordinates(2, 3);
        Damage attack = new Damage(10, DamageType.FIRE);
        World world = new World(5, 5);
        TravellingWorldEntity two = new TravellingWorldEntity("Monster1", initialLocation, 100, world, attack);

        Coordinates vector = new Coordinates(1, 1);
        two.move(vector);

        Coordinates expectedLocation = new Coordinates(3, 4);
        assertEquals(expectedLocation, two.getLocation());
    }

    
}
  

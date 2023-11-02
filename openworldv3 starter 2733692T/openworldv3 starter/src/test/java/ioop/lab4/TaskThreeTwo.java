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
import openworld.characters.Healer;
import openworld.characters.Wizard;
import openworld.monsters.Monster;

public class TaskThreeTwo {


    @Test
    public void testWizardAddsAttackToAdventurer() {
        Coordinates locationWizard = new Coordinates(2, 3);
        Damage wizardAttack = new Damage(10, DamageType.FIRE);
        World world = new World(5, 5);
        
        Coordinates locationAdventurer = new Coordinates(3, 3);
        Damage adventurerAttack = new Damage(15, DamageType.PHYSICAL);
        Adventurer adventurer = new Adventurer("Adventurer1", locationAdventurer, 100, world, adventurerAttack);

        Wizard wizard = new Wizard("Wizard1", locationWizard, 100, world, wizardAttack, new Coordinates(4, 4));
        assertEquals(null,adventurer.getAttacks()[1]);
        assertEquals(null,adventurer.getAttacks()[2]);

        wizard.encounter(adventurer);

        // Verify that the Wizard adds an attack to the Adventurer
        
        
        assertEquals(10, adventurer.getAttacks()[1].getAmount());
        assertEquals(DamageType.FIRE, adventurer.getAttacks()[1].getDamageType());

        wizard.encounter(adventurer);

        // Verify that the Wizard adds an attack to the Adventurer
        
        
        assertEquals(10, adventurer.getAttacks()[2].getAmount());
        assertEquals(DamageType.FIRE, adventurer.getAttacks()[2].getDamageType());
    }

    @Test
    public void testWizardBattlesMonster() {
        Coordinates locationWizard = new Coordinates(2, 3);
        Damage wizardAttack = new Damage(10, DamageType.FIRE);
        World world = new World(5, 5);
        Coordinates locationMonster = new Coordinates(3, 3);
        Damage monsterAttack = new Damage(15, DamageType.PHYSICAL);
        Monster monster = new Monster("Monster1", locationMonster, 100, world, monsterAttack, 2);

        Wizard wizard = new Wizard("Wizard1", locationWizard, 500, world, wizardAttack, new Coordinates(4, 4));

        wizard.encounter(monster);

        // Verify that the Wizard battles the Monster, and the Monster's health has decreased
        assertTrue(monster.getCurrentHealth() == 0);
        assertFalse(monster.isConscious());
        assertEquals(365, wizard.getCurrentHealth());
    }



    @Test
    public void testHealerHealsAdventurer() {
        Coordinates locationHealer = new Coordinates(2, 3);
        Damage healerAttack = new Damage(10, DamageType.PHYSICAL);
        World world = new World(5, 5);
        Coordinates locationAdventurer = new Coordinates(3, 3);
        Damage adventurerAttack = new Damage(15, DamageType.PHYSICAL);
        Adventurer adventurer = new Adventurer("Adventurer1", locationAdventurer, 100, world, adventurerAttack);

        Healer healer = new Healer("Healer1", locationHealer, 100, world, healerAttack, new Coordinates(4, 4));
        adventurer.takeDamage(new Damage(30, DamageType.PHYSICAL));


        healer.encounter(adventurer);

        // Verify that the Healer heals the Adventurer
        assertEquals(100, adventurer.getCurrentHealth());
    }

    @Test
    public void testHealerBattlesMonster() {
        Coordinates locationHealer = new Coordinates(2, 3);
        Damage healerAttack = new Damage(10, DamageType.PHYSICAL);
        World world = new World(5, 5);
        Coordinates locationMonster = new Coordinates(3, 3);
        Damage monsterAttack = new Damage(15, DamageType.PHYSICAL);
        Monster monster = new Monster("Monster1", locationMonster, 100, world, monsterAttack, 2);

        Healer healer = new Healer("Healer1", locationHealer, 100, world, healerAttack, new Coordinates(4, 4)); 
        healer.encounter(monster);
        assertTrue(healer.getCurrentHealth() == 0);
        assertFalse(healer.isConscious());
        assertEquals(30, monster.getCurrentHealth());
    
    }  
    
}

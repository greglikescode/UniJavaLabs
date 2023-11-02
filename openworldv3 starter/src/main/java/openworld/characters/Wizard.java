package openworld.characters;

import openworld.Coordinates;
import openworld.Damage;
import openworld.DamageType;
import openworld.World;
import openworld.Adventurer.Adventurer;
import openworld.entityTypes.TravellingWorldEntity;
import openworld.monsters.Monster;

public class Wizard extends NPC{

    public Wizard(String name, Coordinates location, int maxHealth, World world, Damage attack, Coordinates goal) {
        super(name, location, maxHealth, world, attack, goal);
    }

    public void encounter(TravellingWorldEntity traveller)
    {
        super.encounter(traveller);
        if(traveller instanceof Adventurer)
        {
            Adventurer adventurer = (Adventurer)traveller;
            adventurer.addAttack(new Damage(10, DamageType.FIRE));

            // Printing Adventurers new attacks to confirm...
            System.out.println(this.getName()+" added attack to "+world.getAdventurer().getName()+"\nAdventurers new attacks are...");
            for (int i = 0; i < world.getAdventurer().getAttacks().length; i++) {
                if (world.getAdventurer().getAttacks()[i] != null){
                    System.out.println("Attack "+(i+1)+": "+world.getAdventurer().getAttacks()[i].getDamageType()+" "+world.getAdventurer().getAttacks()[i].getAmount());
                }
            }
        }
        else if(traveller instanceof Monster)
        {
            world.battle(this,traveller);
        }
    }  
}


package openworld.characters;

import openworld.Coordinates;
import openworld.Damage;
import openworld.World;
import openworld.adventurer.Adventurer;
import openworld.entityTypes.WorldEntity;
import openworld.monsters.Monster;

public class Healer extends NPC {
    
    public Healer(String name, Coordinates location, int maxHealth, World world, Damage attack, Coordinates goal) {
        super(name, location, maxHealth, world, attack, goal);
    }

    public void encounter(WorldEntity traveller)
    {
        System.out.println("The healer specific encounter has just been called");
        if(traveller instanceof Adventurer)
        {
            traveller.setCurrentHealth(traveller.getMaxHealth());
            System.out.println("Healed the adventurer");
        }
        else if(traveller instanceof Monster)
        {
            world.battle(this,traveller);
        } else {
            System.out.println(this.getName()+" has no interaction with "+traveller.getName());
        }
    }
}

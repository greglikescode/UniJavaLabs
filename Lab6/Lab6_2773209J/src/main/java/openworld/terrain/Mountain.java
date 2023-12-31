package openworld.terrain;

import openworld.Coordinates;
import openworld.Damage;
import openworld.DamageType;
import openworld.World;
import openworld.Adventurer.Adventurer;
import openworld.entityTypes.TravellingWorldEntity;
import openworld.entityTypes.WorldEntity;


public class Mountain extends Terrain{

    public Mountain(String name, Coordinates location, int maxHealth, World world, Damage attack) {
        super(name, location, maxHealth, world, attack);
        explorable=true;
    }

    @Override
    public void encounter(TravellingWorldEntity traveller)
    {
        traveller.takeDamage(new Damage(10,DamageType.ICE));
    }

    @Override
    public void attack(WorldEntity traveller) {
        System.out.println("The mountain does not fight back!!!");
    }

    public void explore(Adventurer adventurer)
    {
        // return a random encounter or a magic item
    }

    
}

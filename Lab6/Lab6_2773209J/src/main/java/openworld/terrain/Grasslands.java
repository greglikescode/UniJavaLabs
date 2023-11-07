package openworld.terrain;

import openworld.Coordinates;
import openworld.Damage;
import openworld.World;
import openworld.entityTypes.WorldEntity;

public class Grasslands extends Terrain{

    public Grasslands(String name, Coordinates location, int maxHealth, World world, Damage attack) {
        super(name, location, maxHealth, world, attack);
    
    }

    @Override
    public void attack(WorldEntity traveller) {
        System.out.println("The grassland does not fight back!!! I mean its grassland, what do you expect? If you are seeing this, something weird has happened");
    }

    
    
}

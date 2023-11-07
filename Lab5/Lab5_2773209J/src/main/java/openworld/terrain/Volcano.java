package openworld.terrain;

import openworld.Coordinates;
import openworld.Damage;
import openworld.World;
import openworld.entityTypes.TravellingWorldEntity;
import openworld.entityTypes.WorldEntity;

// Task 1.1
public class Volcano extends Terrain{

    
    public Volcano(String name, Coordinates location, int maxHealth, World world, Damage attack) {
        super(name, location, maxHealth, world, attack);
    }

    // Task 1.1
    @Override
    public void encounter(WorldEntity traveller)
    {
        TravellingWorldEntity travellingEntity = ((TravellingWorldEntity)traveller);
        travellingEntity.move(location.findSafeMove(world));
    }
}

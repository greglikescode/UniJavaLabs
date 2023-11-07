package openworld.collectibles;

import openworld.Coordinates;
import openworld.Damage;
import openworld.World;
import openworld.entityTypes.WorldEntity;

public class Items extends WorldEntity {

    public Items(String name, Coordinates location, int maxHealth, World world, Damage attack) {
        super(name, location, maxHealth, world, attack);
    }

}

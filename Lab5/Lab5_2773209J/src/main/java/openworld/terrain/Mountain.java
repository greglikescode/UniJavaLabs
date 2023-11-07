package openworld.terrain;

import openworld.Coordinates;
import openworld.Damage;
import openworld.DamageType;
import openworld.World;
import openworld.entityTypes.WorldEntity;

public class Mountain extends Terrain{

    static Damage mountainDamage = new Damage(10, DamageType.ICE);

    public Mountain(String name, Coordinates location, int maxHealth, World world, Damage attack) {
        super(name, location, maxHealth, world,attack);
        this.attack = mountainDamage;
    }

    // Task 1.1
    @Override
    public void encounter(WorldEntity traveller)
    {
        traveller.takeDamage(mountainDamage);
    }
}

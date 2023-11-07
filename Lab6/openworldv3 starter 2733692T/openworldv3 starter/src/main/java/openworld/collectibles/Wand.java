package openworld.collectibles;

import openworld.Coordinates;
import openworld.Damage;
import openworld.World;
import openworld.Adventurer.Adventurer;
import openworld.entityTypes.TravellingWorldEntity;

public class Wand extends Items {
    public Wand(String name, Coordinates location, int maxHealth, World world, Damage attack) {
        super(name, location, maxHealth, world, attack);
    }

    @Override
    public void encounter(TravellingWorldEntity traveller) {
        super.encounter(traveller);
        if (traveller instanceof Adventurer) {
            ((Adventurer) traveller).addItems(this);
            this.setLocation(new Coordinates(-1, -1));
        }
    }

    private void setLocation(Coordinates location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Damage getWDamage() {
        return attack;
    }

    public int getHealth() {
        return maxHealth;
    }

    public Coordinates getLocation() {
        return location;
    }
}

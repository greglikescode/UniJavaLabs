package openworld.items;

import openworld.Coordinates;
import openworld.Damage;
import openworld.World;

public class Sword extends Item {

    public int damageMultiplier;

    public Sword(String name, Coordinates location, int maxHealth, World world, Damage attack, String description,
            ItemType itemType, int size) {
        super(name, location, maxHealth, world, attack, description, itemType, size);
        // Change this later
        this.damageMultiplier = 3;
    }
    
}

package openworld.items;

import openworld.Coordinates;
import openworld.Damage;
import openworld.World;

public class Armour extends Item {

    public int protectionMultiplier;

    public Armour(String name, Coordinates location, int maxHealth, World world, Damage attack, String description,
            ItemType itemType, int size) {
        super(name, location, maxHealth, world, attack, description, itemType, size);
        this.protectionMultiplier = 3;
    }
    
}

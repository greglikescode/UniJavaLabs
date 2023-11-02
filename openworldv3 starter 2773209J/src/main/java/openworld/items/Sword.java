package openworld.items;

import openworld.Coordinates;
import openworld.Damage;
import openworld.DamageType;
import openworld.World;

public class Sword extends Item {

    public int amount;
    public DamageType damageType;

    public Sword(String name, Coordinates location, int maxHealth, World world, Damage attack, String description,
            ItemType itemType, int size, DamageType damageType) {
        super(name, location, maxHealth, world, attack, description, itemType, size);
        // Change this later
        this.damageType = damageType;
        this.amount = 20;
    }

    public int getAmount() {
        return amount;
    }

    public DamageType getDamageType() {
        return damageType;
    }

}

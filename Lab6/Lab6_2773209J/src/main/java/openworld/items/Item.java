package openworld.items;

import openworld.Coordinates;
import openworld.Damage;
import openworld.DamageType;
import openworld.Adventurer.Adventurer;
import openworld.World;
import openworld.entityTypes.TravellingWorldEntity;
import openworld.entityTypes.WorldEntity;

public class Item extends WorldEntity{
    public String description;
    public ItemType itemType;
    public int size;

    public Item(String name, Coordinates location, int maxHealth,  World world, Damage attack, String description, ItemType itemType, int size){
        super(name,location,maxHealth,world,attack);
        this.description = description;
        this.itemType = itemType;
    }

    @Override
    public void encounter(TravellingWorldEntity traveller)
    {
        super.encounter(traveller);
        if(traveller instanceof Adventurer){

            Adventurer adventurer = (Adventurer)traveller;
            adventurer.addToInventory(this);

        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public int getSize() {
        return size;
    }

    public Coordinates getLocation() {
        return location;
    }

    public void setLocation(Coordinates location){
        this.location = location;
    }

}

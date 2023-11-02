package openworld.characters;

import openworld.Coordinates;
import openworld.Damage;
import openworld.World;
import openworld.entityTypes.TravellingWorldEntity;
import openworld.entityTypes.WorldEntity;


public class NPC extends TravellingWorldEntity {

    protected Coordinates goal;
    public NPC(String name, Coordinates location, int maxHealth, World world, Damage attack, Coordinates goal) {
        super(name, location, maxHealth, world, attack);
        this.goal=goal;
    }

    public void takeTurn()
    {
        move(location.getNextStepTo(goal));
    }



    public Coordinates getGoal() {
        return goal;
    }
    

    public boolean equals(Object obj)
    {
        if(obj instanceof NPC)
        {
            if(((NPC)obj).getName().equals(super.name))
            {
                return true;
            }
        }
        return false;
    }

    





}

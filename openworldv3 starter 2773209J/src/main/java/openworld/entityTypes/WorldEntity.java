package openworld.entityTypes;


import openworld.Coordinates;
import openworld.Damage;
import openworld.DamageType;
import openworld.World;
import openworld.Adventurer.Adventurer;
import openworld.items.Item;
import openworld.items.Sword;

public class WorldEntity {
    protected String name;
    protected Coordinates location;
    protected int maxHealth;
    protected int currentHealth;
    protected Damage attack;

    protected World world;
    public boolean conscious=true;
    int[] vulnerabilities;



    public WorldEntity(String name, Coordinates location, int maxHealth,  World world, Damage attack) {
        this.name = name;
        this.location = location;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.world = world;
        this.attack = attack;
        vulnerabilities=new int[(DamageType.values().length)];
        for (int i=0;i<vulnerabilities.length;i++)
        {
            vulnerabilities[i]=100;
        }
    }
    

    public void takeDamage(Damage damage) {
        System.out.println("TAKE DAMAGE HAS BEEN CALLED");
        int amount = damage.getAmount()*getDamageVulnerability(damage.getDamageType())/100;
        if (amount > 0) {
            this.currentHealth -= amount;
            if (this.currentHealth < 0) {
                this.currentHealth = 0;
                conscious=false;
            }
        }
    }

    public void takeDamageSpecial(Damage damage,Item[] inventory) {
        System.out.println("TAKE SPECIAL DAMAGE HAS BEEN CALLED");
        Item equippedItem = null;
        // Scan the inventory for weapons
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null){
                if (inventory[i] instanceof Sword) {
                    equippedItem = inventory[i];
                }
            }
        }

        int amount = damage.getAmount()*getDamageVulnerability(damage.getDamageType())/100;
        if (amount > 0) {
            if (equippedItem != null) {
                this.currentHealth -= (amount + ((Sword)equippedItem).getAmount());
                System.out.println(name+" takes "+(amount + ((Sword)equippedItem).getAmount())+" damage");
            } else {
                this.currentHealth -= amount;
                System.out.println(name+" takes "+amount+" damage");
            }
            if (this.currentHealth < 0) {
                this.currentHealth = 0;
                conscious=false;
            }
        }
    }


    public int getDamageVulnerability(DamageType damageType)
    {
        switch (damageType){
         case FIRE:
            return vulnerabilities[0];
        case ICE: 
            return vulnerabilities[1];
        case ELECTRIC: 
            return vulnerabilities[2];
        case PHYSICAL:
            return vulnerabilities[3];
        }
        return -1;
    }


    public void setDamageVulnerability(DamageType damageType, int newVulnerabilty)
    {
        switch (damageType){
         case FIRE:
            vulnerabilities[0] = newVulnerabilty;
            break;
        case ICE: 
            vulnerabilities[1]=newVulnerabilty;
            break;
        case ELECTRIC: 
             vulnerabilities[2]= newVulnerabilty;
             break;
        case PHYSICAL:
             vulnerabilities[3]= newVulnerabilty;
             break;
        }
    }


    
    public void attack(WorldEntity victim, WorldEntity attacker){

        System.out.println("attacker: "+attacker);
        System.out.println("victim: "+victim);
        System.out.println(attacker instanceof Adventurer);

        if (attacker instanceof Adventurer) {
            System.out.println("CALLING SPECIAL ATTACK");
            victim.takeDamageSpecial(attack,((Adventurer) attacker).getInventory());
        } else {
            System.out.println("CALLING NORMAL ATTACK");
            victim.takeDamage(attack);
        }
    } 

    
    public void encounter(TravellingWorldEntity traveller) {
        if (traveller instanceof Adventurer)
        {
            System.out.println("You encounter: " + toString() );
        }
        else
        {
            System.out.println(traveller.getName() +" encounters: " + toString() );
        }
    }

    @Override
    public String toString()
    {
        return name + " at " + location.toString()+ " Max Health: " + maxHealth + " Current Health: " + currentHealth;
    }

    public String getName() {
        return name;
    }

    public Coordinates getLocation() {
        return location;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public Damage getAttack() {
        return attack;
    }

    public World getWorld() {
        return world;
    }

    public boolean isConscious() {
        return conscious;
    }

    public void setCurrentHealth(int health) {
        currentHealth=health;
    }
}

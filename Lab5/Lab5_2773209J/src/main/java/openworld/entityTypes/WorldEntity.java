package openworld.entityTypes;

import openworld.Coordinates;
import openworld.Damage;
import openworld.DamageType;
import openworld.World;

public class WorldEntity {
    private String name;
    protected Coordinates location;
    protected int maxHealth;
    protected int currentHealth;
    protected Damage attack;

    protected World world;
    public boolean conscious;
    //                                      FIRE ICE  ELEC PHYS
    public int[] vulnerability = new int[] {100, 100, 100, 100};

    // Task 3.3
    public WorldEntity(String name, Coordinates location, int maxHealth,  World world, Damage attack) {
        this.name = name;
        this.location = location;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.world = world;
        this.attack = attack;

    }
    
    // Task 3.3
    public void takeDamage(Damage damage) {

        int currentVulnerabilty = getVulnerability(damage.getDamageType());
        double actualDamage = (damage.getAmount() * currentVulnerabilty) / 100;
        currentHealth -= actualDamage;
        if (currentHealth <= 0){
            currentHealth = 0;
            conscious = false;
        }
    }

    // Task 3.3
    // IN THE VULNERABILITY ARRAY:
    // FIRE = index 0
    // ICE = index 1
    // ELECTRIC = index 2
    // PHYSICAL = index 3
    public void changeVulnerability(DamageType damageType, int newVulnerabilty){
        int typeArrayIndex = damageTypeToIndex(damageType);
        vulnerability[typeArrayIndex] = newVulnerabilty;
    }

    // Task 3.3
    public int getVulnerability(DamageType damageType){
        int typeArrayIndex = damageTypeToIndex(damageType);
        return vulnerability[typeArrayIndex];
    }

    // Task 3.3
    public int damageTypeToIndex(DamageType damageType){
        int selectedVuln = 0;
        if (damageType == DamageType.FIRE) {
            selectedVuln = 0;
        } else if (damageType == DamageType.ICE) {
            selectedVuln = 1;
        } else if (damageType == DamageType.ELECTRIC) {
            selectedVuln = 2;
        } else if (damageType == DamageType.PHYSICAL) {
            selectedVuln = 3;
        }
        return selectedVuln;
    }

    public void attack(WorldEntity traveller) {
        traveller.takeDamage(attack);
    }
   
    public void encounter(WorldEntity traveller) {
        System.out.println("You encounter name: " + name);
        System.out.println("Coordinate: [" + location.toString()+ "]");
        System.out.println("Max Health: " + maxHealth);
        System.out.println("Current Health: " + currentHealth);
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


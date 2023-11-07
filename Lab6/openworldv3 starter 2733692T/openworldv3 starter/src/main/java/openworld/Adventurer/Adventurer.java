package openworld.Adventurer;

import java.util.ArrayList;
import java.util.Scanner;

import openworld.Coordinates;
import openworld.Damage;
import openworld.World;
import openworld.characters.Healer;
import openworld.characters.Wizard;
import openworld.collectibles.Items;
import openworld.collectibles.Potion;
import openworld.entityTypes.TravellingWorldEntity;
import openworld.entityTypes.WorldEntity;
import openworld.terrain.Mountain;

public class Adventurer extends TravellingWorldEntity {

    private Items[] item = new Items[3];
    private int totalItems = 0;
    private Damage[] attacks = new Damage[3];
    private int totalAttacks = 1;
    private boolean healBoolean = false;
    private boolean spellBoolean = false;

    public Adventurer(String name, Coordinates location, int maxHealth, World world, Damage attack) {
        super(name, location, maxHealth, world, attack);
        attacks[0] = attack;
    }

    public Items[] getItem() {
        return item;
    }

    public Items getSingleItem(int index) {
        return item[index];
    }

    public void setItem(Items[] item) {
        this.item = item;
    }

    public boolean getHealBoolean() {
        return healBoolean;
    }

    public boolean getSpellBoolean() {
        return spellBoolean;
    }

    public void addAttack(Damage attack) {
        if (totalAttacks < attacks.length) {
            attacks[totalAttacks] = attack;
            totalAttacks++;
        }
    }

    public void addItems(Items items) {
        if (totalItems < item.length) {
            item[totalItems] = items;

            System.out.println(java.util.Arrays.toString(item));
            totalItems++;
        }
    }

    public void attack(WorldEntity target) {
        for (int i = 0; i < totalAttacks; i++) {
            target.takeDamage(attacks[i]);
        }
    }

    public Damage[] getAttacks() {
        return attacks;
    }

    public void takeTurn() {
        boolean valid = false;
        printOptions();
        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter your choice here: ");
        while (valid != true) {
            try {
                while (!userInput.hasNext())
                    ;
                int selection = Integer.parseInt(userInput.nextLine());
                resolveTurn(selection);
                valid = true;
            } catch (Exception e) {
                System.out.println("\nInvalid input. Please enter a valid integer.");
            }
        }
    }

    public void printOptions() {

        ArrayList<String> moves = this.getLocation().printSafeMove(world);

        System.out.println("\nThe adventurers options are: ");
        for (int i = 0; i < moves.size(); i++) {
            System.out.println("[" + (i + 1) + "] - " + moves.get(i).toString());
        }
        System.out.println("\nLocation of all the NPC's on the map: ");
        for (int i = 0; i < world.getNonPlayerCharacters().size(); i++) {
            System.out.println(world.getNonPlayerCharacters().get(i).getName() + " at "
                    + world.getNonPlayerCharacters().get(i).getLocation().toString());
        }
    }

    public void resolveTurn(int selection) {
        System.out.println("\n------The adventurers move was resolved!!!------");
        ArrayList<String> safeMoves = this.getLocation().printSafeMove(world);
        String option = safeMoves.get((selection - 1));
        System.out.println("\nBob has selected to " + option + "\n");

        if (option == "NORTH") {
            this.move(Coordinates.NORTH_VECTOR);
        } else if (option == "EAST") {
            this.move(Coordinates.EAST_VECTOR);
        } else if (option == "SOUTH") {
            this.move(Coordinates.SOUTH_VECTOR);
        } else if (option == "WEST") {
            this.move(Coordinates.WEST_VECTOR);
        } else if (option == "HEAL") {
            healBoolean = true;
            this.move(Coordinates.STATIONARY);
        } else if (option == "USE POTION") {
            this.addHealth(50);
            System.out.println("Bob healed himself by 50hp!");
            for (int i = 0; i < item.length; i++) {
                if (item[i] != null && item[i] instanceof Potion) {
                    // Replace the matching value with null
                    item[i] = null;
                    break;
                }
            }
        }
    }

    public void setAttacks(Damage[] attacks) {
        this.attacks = attacks;
    }

    public void setTotalAttacks(int totalAttacks) {
        this.totalAttacks = totalAttacks;
    }

    public void setHealBoolean(boolean healBoolean) {
        this.healBoolean = healBoolean;
    }

    public void setSpellBoolean(boolean spellBoolean) {
        this.spellBoolean = spellBoolean;
    }

}
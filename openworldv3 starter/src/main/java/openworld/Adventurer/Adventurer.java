package openworld.Adventurer;

import java.util.ArrayList;
import java.util.Scanner;

import openworld.Coordinates;
import openworld.Damage;
import openworld.World;
import openworld.characters.Healer;
import openworld.characters.Wizard;
import openworld.entityTypes.TravellingWorldEntity;
import openworld.entityTypes.WorldEntity;
import openworld.items.Item;

public class Adventurer extends TravellingWorldEntity {

    private Damage[] attacks = new Damage[3];
    // Adventurer has an inventory space of 3...
    public Item[] inventory = new Item[3];
    private int totalAttacks = 1;
    public boolean healerInteraction = false;
    public boolean wizardInteraction = false;
    public boolean itemInteraction = false;

    public Adventurer(String name, Coordinates location, int maxHealth, World world, Damage attack) {
        super(name, location, maxHealth, world, attack);
        attacks[0] = attack;
    }

    public void addAttack(Damage attack) {
        if (totalAttacks < attacks.length) {
            attacks[totalAttacks] = attack;
            totalAttacks++;
        }
    }

    @Override
    public void attack(WorldEntity target) {
        for (int i = 0; i < totalAttacks; i++) {
            target.takeDamage(attacks[i]);
        }
    }

    public Damage[] getAttacks() {
        return attacks;
    }

    public ArrayList<String> printOptions() {
        // First, we get the moves that the adventurer is able to go on the map...
        ArrayList<String> options = world.getAdventurer().getLocation().printSafeMove(world);

        // Next, we add to this list the NPC's that are currently on the same square as adventurer...
        for (int i = 0; i < world.getNonPlayerCharacters().size(); i++) {
            if (world.getNonPlayerCharacters().get(i).getLocation().equals(world.getAdventurer().getLocation())){
                if (world.getNonPlayerCharacters().get(i) instanceof Healer){
                    options.add("Heal");
                } else if (world.getNonPlayerCharacters().get(i) instanceof Wizard){
                    options.add("Get Spell");
                }
            }
        }

        // Finally, we add all items on the same square as the adventurer...
        for (int i = 0; i < world.getItems().size(); i++) {
            if (world.getItems().get(i).getLocation().equals(world.getAdventurer().getLocation())){
                options.add("Pick Up Item");
            }
        }

        System.out.println("The adventurers options are...");
        for (int i = 0; i < options.size(); i++) {
            System.out.println("["+(i+1)+"] - "+options.get(i).toString());
        }

        System.out.println("Location of all the NPC's on the map...");
        for (int i = 0; i < world.getNonPlayerCharacters().size(); i++) {
            System.out.println(world.getNonPlayerCharacters().get(i).getName()+" at "+world.getNonPlayerCharacters().get(i).getLocation().toString());
        }

        return options;
    }

    public void resolveTurn(int selection, ArrayList<String> options){

        String move = options.get((selection-1));
        System.out.println(this.getName()+" has selected "+move);

        // If the adventurer chose to move...
            if (move == "North"){
                this.move(new Coordinates(1, 0));
            } else if (move == "East"){
                this.move(new Coordinates(0, 1));
            } else if (move == "South"){
                this.move(new Coordinates(-1, 0));
            } else if (move == "West"){
                this.move(new Coordinates(0, -1));
            } else if (move == "Heal"){
                this.setHealerInteraction(true);;
                this.move(new Coordinates(0, 0));
            } else if (move == "Get Spell"){
                this.setWizardInteraction(true);;
                this.move(new Coordinates(0, 0));
            } else if (move == "Pick up Item"){
                this.setItemInteraction(true);
                this.move(new Coordinates(0, 0));
            }
            
    }

    public void takeTurn() {
        boolean valid = false;
        ArrayList<String> options = printOptions();
        System.out.println("Enter your input here: ");
        Scanner userInput = new Scanner(System.in);
        while (!userInput.hasNext());

        while (valid == false) {
            try{
                int selection=(Integer.parseInt(userInput.nextLine()));
                System.out.println("The users input was: "+selection);
                resolveTurn(selection,options);
                valid = true;
            } catch (Exception e) {
                System.out.println("The input could not be read. "+e.getMessage());
            }
        }
        if (world.gameOver == true){
            userInput.close();
        }
    }

    public boolean isHealerInteraction() {
        return healerInteraction;
    }

    public void setHealerInteraction(boolean healerInteraction) {
        this.healerInteraction = healerInteraction;
    }

    public boolean isWizardInteraction() {
        return wizardInteraction;
    }

    public void setWizardInteraction(boolean wizardInteraction) {
        this.wizardInteraction = wizardInteraction;
    }

    public boolean isItemInteraction() {
        return itemInteraction;
    }

    public void setItemInteraction(boolean itemInteraction) {
        this.itemInteraction = itemInteraction;
    }

}
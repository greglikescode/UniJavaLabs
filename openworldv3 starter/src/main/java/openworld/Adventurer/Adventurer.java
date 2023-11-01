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
import openworld.terrain.Mountain;

public class Adventurer extends TravellingWorldEntity {

    private Damage[] attacks = new Damage[3];
    private int totalAttacks = 1;

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

    public void printOptions() {
        ArrayList<String> safeMoves = world.getAdventurer().getLocation().printSafeMove(world);
        int j = 0;

        System.out.println("The adventurers safe moves are...");
        for (int i = 0; i < safeMoves.size(); i++) {
            System.out.println("["+(i+1)+"] - "+safeMoves.get(i).toString());
            j = i+1;
        }
        System.out.println("Location of all the NPC's on the map...");
        for (int i = 0; i < world.getNonPlayerCharacters().size(); i++) {
            System.out.println("["+(j+1)+"] - "+world.getNonPlayerCharacters().get(i).getName()+" at "+world.getNonPlayerCharacters().get(i).getLocation().toString());
            j++;
        }

    }

    public void resolveTurn(int selection){
        System.out.println("The adventurers move was resolved!!!");
        ArrayList<String> safeMoves = world.getAdventurer().getLocation().printSafeMove(world);

        String move = safeMoves.get((selection-1));
        System.out.println("Steve has selected to move "+move);

        if (move == "North"){
            world.getAdventurer().move(new Coordinates(1, 0));
        } else if (move == "East"){
            world.getAdventurer().move(new Coordinates(0, 1));
        } else if (move == "South"){
            world.getAdventurer().move(new Coordinates(-1, 0));
        } else if (move == "West"){
            world.getAdventurer().move(new Coordinates(0, -1));
        }
    }
    public void takeTurn() {
        printOptions();
        System.out.println("Enter your input here: ");
        Scanner userInput = new Scanner(System.in);
        while (!userInput.hasNext());
        try{
            int selection=(Integer.parseInt(userInput.nextLine()));
            System.out.println("The users input was: "+selection);
            resolveTurn(selection);
        } catch (Exception e) {
            System.out.println("The input could not be read. "+e.getMessage());
        }
        
     
    }

   
  
    
    

}
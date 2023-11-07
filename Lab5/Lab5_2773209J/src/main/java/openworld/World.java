package openworld;

import java.util.ArrayList;
import java.util.Collections;

import openworld.adventurer.Adventurer;
import openworld.characters.Healer;
import openworld.characters.NPC;
import openworld.characters.Wizard;

import openworld.terrain.Grasslands;
import openworld.terrain.Mountain;
import openworld.terrain.Terrain;
import openworld.terrain.Volcano;
import openworld.entityTypes.WorldEntity;
import openworld.monsters.Blob;
import openworld.monsters.Monster;
import openworld.monsters.Skeleton;

public class World {

    private int xDimension;
    private int yDimension;
    private ArrayList<Terrain> terrain = new ArrayList<>();
    private ArrayList<Monster> monsters = new ArrayList<>();

    private ArrayList<NPC> nonPlayerCharacters = new ArrayList<>();
    private Adventurer adventurer;

    public World(int xDimension, int yDimension) {
        this.xDimension = xDimension;
        this.yDimension = yDimension;
    }

    // Task 1.2
    public void initaliseWorld() {
        generateTerrain();
        generateMonsters();
        generateCharacters();
        adventurer = new Adventurer("Bob", new Coordinates(0, 0), 100, this, new Damage(10, DamageType.PHYSICAL));
    }

    // Task 3.1
    public void run() {
        adventurer.takeTurn();
        nonPlayerCharactersMove();
        monsterMove();
    }
    
    // Task 1.2
    public void generateMonsters() {
        int coordsCount = 1;
        boolean skeleton = true;
        int skeletonNum = 1;
        int blobNum = 1;
        // while x and y are within the world boundaries.
        for (int x = 0; x <= xDimension; x++) {
            for (int y = 0; y <= yDimension; y++) {
                if (coordsCount == 7){
                    if (skeleton){
                        monsters.add(new Skeleton("Ske"+skeletonNum,new Coordinates(x, y), 100,
                            this, new Damage(10, DamageType.PHYSICAL), 1));
                            skeletonNum++;
                        skeleton = false;
                    } else {
                        monsters.add(new Blob("Blb"+blobNum,new Coordinates(x, y), 100,
                            this, new Damage(5, DamageType.PHYSICAL), 1));
                            blobNum++;
                        skeleton = true;
                    }
                coordsCount = 0;
                }
                coordsCount++;
            }
        }

    }

    // Task 1.2
    public void generateCharacters() {
        int coordsCount = 1;
        boolean wizard = true;
        int wizardNum = 1;
        int healerNum = 1;
        // while x and y are within the world boundaries.
        for (int x = 0; x <= xDimension; x++) {

            for (int y = 0; y <= yDimension; y++) {
                if (coordsCount == 15){
                    if (wizard){
                        nonPlayerCharacters.add(new Wizard("Wiz"+wizardNum,new Coordinates(x, y), 100,
                            this, new Damage(30, DamageType.FIRE), new Coordinates(6, 2)));
                        wizardNum++;
                        wizard = false;
                    } else {
                        nonPlayerCharacters.add(new Healer("Hlr"+healerNum,new Coordinates(x, y), 100,
                            this, new Damage(5, DamageType.PHYSICAL), new Coordinates(6, 2)));
                        healerNum++;
                        wizard = true;
                    }
                coordsCount = 0;
                }
                coordsCount++;
            }
        }
    }

    public void generateTerrain() {
        // while x and y are within the world boundaries.
        for (int x = 0; x <= xDimension; x++) {
            for (int y = 0; y <= yDimension; y++) 
            // Create a grassland on every single coordinate of the world.
            {
                terrain.add(new Grasslands("Grasslands", new Coordinates(x, y), 1000, this,
                        new Damage(0, DamageType.PHYSICAL)));
            }
        }
    }

    // Task 2.1
    public void printWorld() {
        for (int x = xDimension-1; x >= 0;x--) {
            String yLine = "";
            for (int y = 0; y <= yDimension-1; y++) {
                String coord = "";
                for(Terrain land : terrain) {
                    if (land.getLocation().getX()==x && land.getLocation().getY()==y) {
                        char terrainChar = land.getName().charAt(0);
                        coord += terrainChar;
                        break;
                    }
                }
                for(Monster googly : monsters) {
                    if (googly.getLocation().getX()==x && googly.getLocation().getY()==y) {
                        coord += ":" + googly.getName();
                        break;
                    }
                }
                for (NPC npc : nonPlayerCharacters) {
                    if (npc.getLocation().getX()==x && npc.getLocation().getY()==y) {
                        coord += ":"+npc.getName();
                        break;
                    }
                }
                if (adventurer.getLocation().getX() == x && adventurer.getLocation().getY() == y) {
                    coord += ":"+adventurer.getName();
                }
                int spacing = 20 - coord.length();
                String spaces = String.format("%" + spacing + "s","");
                yLine += coord + spaces;
            }
            System.out.println(yLine);
            System.out.println(); 
        }
    }

    // Task 3.1
    private void monsterMove() {
        Collections.sort(monsters, new LevelSort().thenComparing(new AlphabeticalSort()));
        
        for (Monster monster : monsters) {
            monster.takeTurn();
        }
    }

    // Task 3.1
    public void nonPlayerCharactersMove() {
        Collections.sort(nonPlayerCharacters, new AlphabeticalSort());

        for (NPC npc : nonPlayerCharacters) {
            npc.takeTurn();
        }
    }

    // Task 3.2
    public void resolveMove(WorldEntity traveller) {
        Coordinates currentLocation = new Coordinates(traveller.getLocation().getX(), traveller.getLocation().getY());
        Terrain currentTerrain = null;
        Monster currentMonster = null;
        NPC currentNPC = null;

        // There will always be terrain on every square
        for (Terrain lands : terrain) {
            if (lands.getLocation().getX() == currentLocation.getX() && lands.getLocation().getY() == currentLocation.getY()){
                currentTerrain = lands;
            }      
        }

        // There may not be a monster at the coordinate
            for (Monster googly : monsters) {
            if (googly.getLocation().getX() == currentLocation.getX() && googly.getLocation().getY() == currentLocation.getY()){
                currentMonster = googly;
            }      
        }   

        // There may not be an NPC at the coordinate
            for (NPC goodie: nonPlayerCharacters) {
            if (goodie.getLocation().getX() == currentLocation.getX() && goodie.getLocation().getY() == currentLocation.getY()){
                if (goodie != traveller) {
                    currentNPC = goodie;
                }
            }
        }   
        currentTerrain.encounter(traveller);

        if (currentMonster != null){
            currentMonster.encounter(traveller);
        }
        if (currentNPC != null){
            currentNPC.encounter(traveller);
        }

    }

    public void battle(WorldEntity location, WorldEntity traveller) {
        while (location.getCurrentHealth() > 0 && traveller.getCurrentHealth() > 0) {
            location.attack(traveller);
            if (traveller.getCurrentHealth() > 0) {
                traveller.attack(location);
            }
        }
    }

    // Task 2.2
    public static void main(String[] args) {
        // Task 2.2 Creating a 7 by 7 world and printing it out
        World zorpWorld = new World(7, 7);
        zorpWorld.initaliseWorld();
        zorpWorld.printWorld();
    }

// GETTERS AND SETTERS

    public int getxDimension() {
        return xDimension;
    }

    public int getyDimension() {
        return yDimension;
    }

    public Adventurer getAdventurer() {
        return adventurer;
    }

    public void setAdventurer(Adventurer adventurer) {
        this.adventurer = adventurer;
    }

    public ArrayList<Terrain> getTerrain() {
        return terrain;
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public ArrayList<NPC> getNonPlayerCharacters() {
        return nonPlayerCharacters;
    }

}

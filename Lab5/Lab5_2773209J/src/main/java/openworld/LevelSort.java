package openworld;

import java.util.Comparator;

import openworld.monsters.Monster;

// Task 3.1
public class LevelSort implements Comparator<Monster>{

    @Override
    public int compare(Monster o1, Monster o2) {
        return o1.getLevel() - o2.getLevel();
    }

    

}

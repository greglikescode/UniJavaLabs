package openworld;

import java.util.Comparator;

import openworld.entityTypes.WorldEntity;

// Task 3.1
public class AlphabeticalSort implements Comparator<WorldEntity>{

    @Override
    public int compare(WorldEntity o1, WorldEntity o2) {
        return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
        
    }

}

package openworld.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import openworld.Coordinates;
import openworld.characters.Healer;
import openworld.characters.Wizard;
import openworld.entityTypes.WorldEntity;
import openworld.terrain.Mountain;
import openworld.terrain.Terrain;
import openworld.gui.MountainDialog;

public class MovementAdapter extends KeyAdapter {

    private GameWorld gameWorld;
    private GameWindow gameWindow;

    public MovementAdapter(GameWorld gameWorld, GameWindow gameWindow) {
        this.gameWorld = gameWorld;
        this.gameWindow = gameWindow;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        ArrayList options = gameWorld.getAdventurer().getLocation().findAllSafeMoves(gameWorld.getWorld());
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (options.contains(Coordinates.NORTH_VECTOR)) {
                    moveAdventurer(Coordinates.NORTH_VECTOR);
                }
                break;

            case KeyEvent.VK_RIGHT:
                if (options.contains(Coordinates.EAST_VECTOR)) {
                    moveAdventurer(Coordinates.EAST_VECTOR);
                }
                break;

            case KeyEvent.VK_DOWN:
                if (options.contains(Coordinates.SOUTH_VECTOR)) {
                    moveAdventurer(Coordinates.SOUTH_VECTOR);
                }
                break;

            case KeyEvent.VK_LEFT:
                if (options.contains(Coordinates.WEST_VECTOR)) {
                    moveAdventurer(Coordinates.WEST_VECTOR);
                }
                break;
        }
    }

    public void moveAdventurer(Coordinates vector) {
        if (!gameWorld.getAdventurer().isConscious()) {
            return;
        }

        gameWorld.getAdventurer().move(vector);

        if (!gameWorld.getAdventurer().isConscious()) {
            return;
        }

        List<Terrain> terrain = gameWorld.getWorld().getTerrainHere(gameWorld.getAdventurer().getLocation());
        if (terrain.get(0) instanceof Mountain) {
            Mountain mountain = (Mountain) terrain.get(0);

            MountainDialog dlg = new MountainDialog(gameWindow, mountain);
            dlg.setVisible(true);

            if (dlg.getChoice() != null) {
                dlg.getChoice().explore(gameWorld.getAdventurer());
            } else {
                System.out.println("The adventurer decided not to explore the Mountain.");
            }
        }

        gameWindow.getControlPanel().update();
    }
}

package Gameplay.Farm;

import Gameplay.Gameplay.EventSystem.EventSystem;
import Gameplay.Gameplay.EventSystem.Events;
import Utility.IntPoint;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Used as the parent to all tiles in the field
 */
public class Field {

    // the game's tile size is and will remain consistent throughout the game
    // so unless I decided to make it a feature to be able to expand your field
    // this variable will remain final
    final private IntPoint fieldSize = new IntPoint(10, 5);
    private final ArrayList<Tile> tiles;
    private final EventSystem eventSystemRef;

    public Field(EventSystem eventSystemRef) {
        this.eventSystemRef = eventSystemRef;
        tiles = new ArrayList<Tile>();

        File rockFile;
        Scanner scanner;

        try {
            rockFile = new File("src/rocks.txt");
            scanner = new Scanner(rockFile);
            Tile tempTile;
            for (int i = 0; i < fieldSize.y; i++) {
                for (int j = 0; j < fieldSize.x; j++) {
                    boolean bRocked = false;
                    if (scanner.hasNextLine()) {
                        if (scanner.nextInt() > 0) {
                            bRocked = true;
                        }
                    }
                    tempTile = new Tile(this, new IntPoint(j, i), bRocked);
                    eventSystemRef.register(Events.END_DAY, tempTile);
                    tiles.add(tempTile);
                }
            }
        } catch (FileNotFoundException e) {
        }

    }


    /**
     * @return IntPoint
     */
    public IntPoint getFieldSize() {
        return fieldSize;
    }


    /**
     * @return ArrayList<Tile>
     */
    public ArrayList<Tile> getTiles() {
        return tiles;
    }


    /**
     * @return EventSystem
     */
    public EventSystem getEventSystemRef() {
        return eventSystemRef;
    }

    /**
     * Checks if the tile is a valid place to plant a tree
     *
     * @param tile - tile to check
     * @return true if valid plant
     */
    public boolean validTreePlant(Tile tile) {
        IntPoint testCoord = tile.getCoord();
        if (testCoord.x == 0 || testCoord.x == 9 || testCoord.y == 0 || testCoord.y == 4) {
            return false;
        } else {
            if (!getTileAtPoint(testCoord.addRaw(0, 1)).occupied()) {
                if (!getTileAtPoint(testCoord.addRaw(0, -1)).occupied()) {
                    if (!getTileAtPoint(testCoord.addRaw(1, 0)).occupied()) {
                        if (!getTileAtPoint(testCoord.addRaw(-1, 0)).occupied()) {
                            if (!getTileAtPoint(testCoord.addRaw(1, 1)).occupied()) {
                                if (!getTileAtPoint(testCoord.addRaw(1, -1)).occupied()) {
                                    if (!getTileAtPoint(testCoord.addRaw(-1, 1)).occupied()) {
                                        return !getTileAtPoint(testCoord.addRaw(-1, -1)).occupied();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }
    }

    /**
     * Converts point to a valid tile reference MC02
     *
     * @param point point to check
     * @return tile found at point
     */
    public Tile getTileAtPoint(IntPoint point) {
        return tiles.get(((point.y) * fieldSize.x) + (point.x));
    }

    /**
     * Gets a tile from a given index
     *
     * @param index index to check
     * @return tile found at index
     */
    public Tile getTileAtIndex(int index) {
        return tiles.get(index);
    }

    /**
     * Checks if the field is full of withered crops
     *
     * @return true if all tiles are occupied by withered crops
     */
    public boolean fieldOver() {
        /**
         * Iterates through every tile
         */
        for (Tile tile : tiles) {
            /**
             * If the tile is occupied check if it
             * Otherwise, return false as there is still a single vacant tile
             */
            if (tile.occupied()) {
                if (tile.getCrop() != null) {
                    if (tile.getCrop().isWithered()) {
                        //If the iteration proceeds without interuption, the return true
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        //return true if uninterrupted
        return true;
    }
}
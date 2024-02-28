package Gameplay.Farm;

import Gameplay.Farm.Seeds.Seed;
import Gameplay.Gameplay.EventSystem.EventSystemInterface;
import Gameplay.Gameplay.EventSystem.Events;
import Gameplay.Player.Controller;
import UI.TileButton;
import Utility.IntPoint;

/**
 * Class that holds data regarding the tiles in the field
 */
public class Tile implements EventSystemInterface {

    private boolean bPlowed;
    private Crop crop;
    private final Field fieldRef;

    private final IntPoint coord;

    private boolean bRocked;
    private boolean bFertilized;

    private boolean bWatered;

    private TileButton assignedButton;

    /**
     * Constructor
     */
    public Tile(Field fieldRef, IntPoint coord, boolean bRock) {
        this.coord = coord;
        this.bPlowed = false;
        this.crop = null;
        this.bFertilized = false;
        this.bWatered = false;
        this.fieldRef = fieldRef;
        this.bRocked = bRock;
    }

    /**
     * @return boolean
     */
    public boolean isbRocked() {
        return bRocked;
    }

    public void deRock() {
        bRocked = false;
        assignedButton.updateTileButton();
    }


    /**
     * @return IntPoint
     */
    public IntPoint getCoord() {
        return coord;
    }


    /**
     * @param assignedButton
     */
    public void setAssignedButton(TileButton assignedButton) {
        this.assignedButton = assignedButton;
    }


    /**
     * @return boolean
     */
    public boolean isWatered() {
        return bWatered;
    }


    /**
     * @return boolean
     */
    public boolean isFertilized() {
        return bFertilized;
    }


    /**
     * @return boolean
     */
    public boolean isPlowed() {
        return bPlowed;
    }


    /**
     * @return Crop
     */
    public Crop getCrop() {
        return crop;
    }


    /**
     * @return boolean
     */
    public boolean occupied() {
        return bRocked || crop != null;
    }


    /**
     * @return Field
     */
    public Field getFieldRef() {
        return fieldRef;
    }

    public void plowTile() {
        bPlowed = true;
    }

    /**
     * plants a seed into the tile
     *
     * @param seed - seed to plant
     * @return false if planting of seed in unsuccessful
     */
    public boolean plantSeed(Seed seed) {
        if (crop == null && bPlowed) {
            crop = new Crop(seed, this);
            assignedButton.setToolTipText(crop.getCropName());
            assignedButton.updateTileButton();
            return true;
        }
        return false;
    }

    /**
     * Waters the tile
     *
     * @return true if the tile was watered and false if it was not
     */
    public boolean waterTile() {
        if (!bWatered) {
            bWatered = true;
            assignedButton.updateTileButton();
            return true;
        }
        return false;
    }

    /**
     * Fertilizes the tile
     *
     * @return true if the tile was fertilized and false if it was not
     */
    public boolean fertilizeTile() {
        if (!bFertilized) {
            bFertilized = true;
            assignedButton.updateTileButton();
            return true;
        }
        return false;
    }

    /**
     * harvest the crop from the tile
     *
     * @param player
     * @return false if the crop cannot be harvested
     */
    public boolean harvestCrop(Controller player) {
        return crop.harvestCrop(player);
    }

    /**
     * Removes the crop and resets the tile
     */
    public void removeCrop() {
        bPlowed = false;
        crop = null;
        assignedButton.setToolTipText("");
        assignedButton.updateTileButton();
    }


    /**
     * @param event
     * @param days
     * @param payload
     */
    @Override
    public void eventNotify(Events event, int days, Object payload) {
        // TODO Auto-generated method stub
        if (event == Events.END_DAY) {
            if (crop != null) {
                crop.eventNotify(Events.END_DAY, days, payload);
            }
            bFertilized = false;
            bWatered = false;
            assignedButton.updateTileButton();
        }
    }
}

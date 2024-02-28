package Gameplay.Player;

import Gameplay.Farm.Field;
import Gameplay.Farm.Tile;
import Gameplay.GameModel;
import Gameplay.Gameplay.EventSystem.EventSystemInterface;
import Gameplay.Gameplay.EventSystem.Events;
import Gameplay.Gameplay.Shop;
import Gameplay.Items.*;

/**
 * Used to communicate between Model and Viewport
 */
public class Controller implements EventSystemInterface {


    private boolean bHarvest;


    private Field fieldRef;
    private Shop shopRef;
    private final GameModel modelRef;


    public Controller(GameModel model) {


        this.modelRef = model;

        model.getInventory().addItem(new Plow());
        model.getInventory().addItem(new WaterCan());
        model.getInventory().addItem(new Fertilizer());
        model.getInventory().addItem(new Pickaxe());
        model.getInventory().addItem(new Shovel());
    }


    /**
     * @return GameModel
     */
    public GameModel getModelRef() {
        return modelRef;
    }


    /**
     * @return Shop
     */
    public Shop getShopRef() {
        return shopRef;
    }


    /**
     * @return boolean
     */
    public boolean isHarvestTime() {
        return bHarvest;
    }


    /**
     * @return Field
     */
    public Field getFieldRef() {
        return fieldRef;
    }

    /**
     * Increases the player's Objectcoins
     *
     * @param amount amount to increase by
     */
    public void addObjectcoins(Double amount) {
        modelRef.addObjectcoins(amount);
    }

    /**
     * Reduces the player's Objectcoins
     *
     * @param amount amount to reduce by
     */
    public void removeObjectcoins(Double amount) {
        modelRef.removeObjectcoins(amount);
    }

    /**
     * Increases the player's Experience
     *
     * @param value amount to increase by
     */
    public void gainExperience(Double value) {
        modelRef.gainExperience(value);
    }

    /**
     * Adds an item from the player's inventory
     *
     * @param item item to add
     */
    public void giveItem(Item item) {
        modelRef.giveItem(item, this);
    }

    /**
     * Removes an item from the player's inventory
     *
     * @param item item to remove
     */
    public void removeItem(Item item) {
        modelRef.removeItem(item);
    }


    /**
     * @param slot
     */
    public void setSelectedItem(InventorySlot slot) {
        modelRef.setSelectedItem(slot.item);
    }


    /**
     * @param tile
     */
    public void useSelectedItem(Tile tile) {
        if (tile != null && modelRef.getSelectedItem() != null) {
            modelRef.setSelectedTile(tile);
            modelRef.getSelectedItem().useItem(this);
        }
    }


    /**
     * @param event
     * @param days
     * @param payload
     */
    @Override
    public void eventNotify(Events event, int days, Object payload) {
        if (event == Events.HARVEST_READY) {
            bHarvest = true;
            System.out.println("CROPS ARE READY TO BE HARVEST!");
        }
    }
}

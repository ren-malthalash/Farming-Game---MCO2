package Gameplay.Items;

import Gameplay.Player.Controller;
import Gameplay.Player.InventorySlot;

/**
 * Objects used by the user to interact with the game world
 */
public abstract class Item {

    protected String itemName;
    protected double itemCost;

    protected boolean bItemSell;
    protected double itemSellPrice;
    protected String itemDescription;


    /**
     * @return String
     */
    public String getItemName() {
        return itemName;
    }


    /**
     * @return double
     */
    public double getItemCost() {
        return itemCost;
    }


    /**
     * @return double
     */
    public double getItemSellPrice() {
        return itemSellPrice;
    }


    /**
     * @return String
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * Called when the item is used by the player
     *
     * @param player PlayerReference
     */
    public void useItem(Controller player) {

    }

    /**
     * Called before using an item
     *
     * @param player PlayerReference
     * @return true if the player can use the item
     */
    public boolean canUseItem(Controller player) {
        return true;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof InventorySlot) {
            return ((InventorySlot) obj).sameItem(this);
        }
        return super.equals(obj);
    }
} 
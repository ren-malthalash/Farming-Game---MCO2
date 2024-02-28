package Gameplay.Player;

import Gameplay.Items.Item;

import java.util.ArrayList;

/**
 * Holds all data regarding the player's inventory
 */
public class Inventory {

    private final ArrayList<InventorySlot> items;

    public Inventory() {
        items = new ArrayList<InventorySlot>();
    }

    /**
     * Adds and item to the inventory
     *
     * @param item item to add
     */
    public void addItem(Item item) {
        if (items.contains(item)) {
            items.get(items.indexOf(item)).addStack();
            //System.out.println("Increased Stack");
        } else {
            items.add(new InventorySlot(item, 1));
            //System.out.println("Item Added");
        }
    }

    /**
     * Removes an item from the inventory
     *
     * @param item item to remove
     */
    public void removeItem(Item item) {
        if (items.contains(item)) {
            if (items.get(items.indexOf(item)).stack > 1) {
                items.get(items.indexOf(item)).reduceStack();
                //System.out.println("Reduced Stack");
            } else {
                items.remove(item);
                //System.out.println("Item removed");
            }
        }
    }

    /**
     * @return A list of strings containing the item name and description, and amount of items in your inventory
     */
    public ArrayList<String> getInventoryList() {
        ArrayList<String> tempList = new ArrayList<String>();
        for (InventorySlot slot : items) {
            tempList.add(String.format("\t [%d]", slot.stack));
        }
        return tempList;
    }


    /**
     * @return ArrayList<InventorySlot>
     */
    public ArrayList<InventorySlot> getItems() {
        return items;
    }
}

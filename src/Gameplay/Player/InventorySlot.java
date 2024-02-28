package Gameplay.Player;

import Gameplay.Items.Item;

/**
 * Used by the inventory to determine and hold its contents
 */
public class InventorySlot {
    public Item item;
    public int stack;

    public InventorySlot(Item item, int stack) {
        this.item = item;
        this.stack = stack;
    }

    public InventorySlot(Item item) {
        this.item = item;
        this.stack = 1;
    }


    /**
     * @param item
     * @return boolean
     */
    public boolean sameItem(Item item) {
        return this.item.getClass() == item.getClass();
    }


    /**
     * @param amount
     */
    public void addStack(int amount) {
        stack += amount;
    }

    public void addStack() {
        stack++;
    }


    /**
     * @param amount
     */
    public void reduceStack(int amount) {
        stack -= amount;
    }

    public void reduceStack() {
        stack--;
    }
}

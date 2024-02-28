package Utility;

import Gameplay.Items.Item;

import java.util.ArrayList;

/**
 * Function Library
 */
public class Utils {

    /**
     * @param items
     * @return ArrayList<String>
     */
    public static ArrayList<String> getItemsAsList(ArrayList<Item> items) {
        ArrayList<String> tempList = new ArrayList<String>();
        for (Item item : items) {
            tempList.add("COST: " + item.getItemCost() + " | " + item.getItemName());
        }
        return tempList;
    }

    /**
     * Clears the screen
     */
    public static void clrScrn() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

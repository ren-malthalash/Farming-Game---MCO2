package Gameplay.Gameplay;

import Gameplay.Farm.Seeds.*;
import Gameplay.Items.Item;
import Gameplay.Player.Controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Shop {

    //private IntPoint gridSize;
    private final ArrayList<Item> shopList;

    public Shop() {
        //gridSize = new IntPoint(10, 5);
        shopList = new ArrayList<Item>();
        shopList.add(new Turnip());
        shopList.add(new Carrot());
        shopList.add(new Potato());
        shopList.add(new Rose());
        shopList.add(new Turnips());
        shopList.add(new Sunflower());
        shopList.add(new Mango());
        shopList.add(new Apple());
    }


    /**
     * @return ArrayList<Item>
     */
    public ArrayList<Item> getShopList() {
        return shopList;
    }

    /**
     * Purchases an item from the list
     *
     * @param player
     * @param category
     * @param index
     */
    public void purchaseItem(Controller player, int index) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        if (index >= 0) {
            Item item = shopList.get(index).getClass().getDeclaredConstructor().newInstance();
            if (player.getModelRef().getObjectcoins() >= item.getItemCost() - player.getModelRef().getFarmerBonus().getSeedCostReduc()) {
                player.removeObjectcoins(item.getItemCost() - player.getModelRef().getFarmerBonus().getSeedCostReduc());
                player.giveItem(item);
                System.out.println("You purchased a " + item.getItemName() + " for " + (item.getItemCost() - player.getModelRef().getFarmerBonus().getSeedCostReduc()) + " COINS");
            } else {
                System.out.println("Not enough objectcoins");
            }
        }
    }
}

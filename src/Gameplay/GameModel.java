package Gameplay;

import Gameplay.Farm.Field;
import Gameplay.Farm.Seeds.Seed;
import Gameplay.Farm.Tile;
import Gameplay.Gameplay.EventSystem.EventSystem;
import Gameplay.Gameplay.EventSystem.EventSystemInterface;
import Gameplay.Gameplay.EventSystem.Events;
import Gameplay.Gameplay.Shop;
import Gameplay.Items.Item;
import Gameplay.Player.Controller;
import Gameplay.Player.Inventory;
import Utility.FarmerType;
import Gameplay.Player.InventorySlot;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Holds all gameplay-related data
 */
public class GameModel implements EventSystemInterface {

    private double experience;
    private int level;
    private double objectcoins;
    private Item selectedItem;
    private Tile selectedTile;

    private int exitCode;
    private final Inventory inventory;

    private final FarmerType farmerType;
    private final EventSystem eventSystem;

    private final Scanner scanner;
    private final Field field;
    private final Shop shop;
    private Viewport viewportRef;

    public GameModel() {
        eventSystem = new EventSystem();
        scanner = new Scanner(System.in);
        field = new Field(eventSystem);
        shop = new Shop();

        this.experience = 0;
        this.level = ((int) this.experience / 100);
        this.objectcoins = 100;
        this.selectedItem = null;
        this.farmerType = new FarmerType();
        this.inventory = new Inventory();

        eventSystem.register(Events.END_DAY, this);
    }


    /**
     * @return int
     */
    public int getExitCode() {
        return exitCode;
    }


    /**
     * @return Tile
     */
    public Tile getSelectedTile() {
        return selectedTile;
    }


    /**
     * @param selectedTile
     */
    public void setSelectedTile(Tile selectedTile) {
        this.selectedTile = selectedTile;
    }


    /**
     * @param viewport
     */
    public void setViewportRef(Viewport viewport) {
        viewportRef = viewport;
    }


    /**
     * @return EventSystem
     */
    public EventSystem getEventSystem() {
        return eventSystem;
    }


    /**
     * @return Scanner
     */
    public Scanner getScanner() {
        return scanner;
    }


    /**
     * @return Field
     */
    public Field getField() {
        return field;
    }


    /**
     * @return Shop
     */
    public Shop getShop() {
        return shop;
    }


    /**
     * @return double
     */
    public double getExperience() {
        return experience;
    }


    /**
     * @return int
     */
    public int getLevel() {
        return level;
    }


    /**
     * @return double
     */
    public double getObjectcoins() {
        return objectcoins;
    }

    /**
     * @return Item
     */
    public Item getSelectedItem() {
        return selectedItem;
    }

    /**
     * @param selectedItem
     */
    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
        updateDataPanel();
    }

    /**
     * @return Inventory
     */
    public Inventory getInventory() {
        return inventory;
    }


    /**
     * @return FarmerBonus
     */
    public FarmerType getFarmerBonus() {
        return farmerType;
    }

    /**
     * Adds an item from the player's inventory
     *
     * @param item item to add
     */
    public void giveItem(Item item, Controller player) {
        inventory.addItem(item);
        updateInventoryPanel();
    }

    /**
     * Removes an item from the player's inventory
     *
     * @param item item to remove
     */
    public void removeItem(Item item) {
        inventory.removeItem(item);
        if (!inventory.getItems().contains(item)) {
            setSelectedItem(null);
        }
        updateInventoryPanel();
    }


    /**
     * Increases the player's Objectcoins
     *
     * @param amount amount to increase by
     */
    public void addObjectcoins(Double amount) {
        objectcoins += amount;
        updateDataPanel();
    }

    /**
     * Reduces the player's Objectcoins
     *
     * @param amount amount to reduce by
     */
    public void removeObjectcoins(Double amount) {
        objectcoins -= amount;
        if (objectcoins < 0) {
            System.out.println("ERR: Objectcoins below 0");
        }
        updateDataPanel();
    }

    /**
     * Increases the player's Experience
     *
     * @param value amount to increase by
     */
    public void gainExperience(Double value) {
        experience += value;
        if (level < ((int) experience / 100)) {
            level += 1;
        }
        updateDataPanel();
    }

    /**
     * @return List of items in the inventory
     */
    public ArrayList<String> inventoryList() {
        return inventory.getInventoryList();
    }

    /**
     * Gets an item from the player's inventory
     *
     * @param index - item index
     * @return item from the inventory at the index, returns null if none
     */
    public Item getItem(int index) {
        if (index >= 0) {
            return inventory.getItems().get(index).item;
        }
        return null;
    }


    public void checkGameOver() {
        if (field.fieldOver()) {
            System.out.println("FUCK");
            notifyGameOver(0);
        } else {
            boolean bNoSeeds = true, bNoCrops = true, bNoCoins = true;

            if (objectcoins >= 5 - farmerType.getSeedCostReduc()) {
                bNoCoins = false;
                return;
            }
            for (InventorySlot slot : inventory.getItems()) {
                if (slot.item instanceof Seed) {
                    bNoSeeds = false;
                    return;
                }
            }
            for (Tile tile : field.getTiles()) {
                if (tile.getCrop() != null) {
                    if (!tile.getCrop().isWithered()) {
                        bNoCrops = false;
                        return;
                    }
                }
            }
            if (bNoSeeds && bNoCrops && bNoCoins) {
                notifyGameOver(1);
            }
        }
    }


    /**
     * @param event
     * @param days
     * @param payload
     */
    @Override
    public void eventNotify(Events event, int days, Object payload) {
        if (event == Events.END_DAY) {
            updateDataPanel();
            checkGameOver();
        }
    }

    private void updateDataPanel() {
        viewportRef.updateDataPanel();
    }

    private void updateInventoryPanel() {
        viewportRef.updateInventoryPanel();
    }


    /**
     * @param exitCode
     */
    private void notifyGameOver(int exitCode) {
        this.exitCode = exitCode;
        viewportRef.initializeGameOverScreen();
    }
}

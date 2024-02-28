package Gameplay.Items;

import Gameplay.Player.Controller;

public class Plow extends Tool {

    public Plow() {
        this.itemName = "Plow";
        this.useCost = 0.0f;
        this.itemDescription = "Converts an unplowed tile to a plowed tile. Can only be performed on an unplowed tile.";
        this.bItemSell = false;
        this.itemSellPrice = 0;
        this.useExperience = 0.5f;
    }


    /**
     * @param player
     */
    @Override
    public void useItem(Controller player) {
        if (canUseItem(player)) {
            if (!player.getModelRef().getSelectedTile().isPlowed()) {
                super.useItem(player);
                player.getModelRef().getSelectedTile().plowTile();
                System.out.println("Plow!");
            }
        }
    }
}
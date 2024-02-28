package Gameplay.Items;

import Gameplay.Player.Controller;

public class WaterCan extends Tool {

    public WaterCan() {
        this.itemName = "Watering Can";
        this.useCost = 0.0f;
        this.itemDescription = "Waters a plowed tile.";
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
            if (player.getModelRef().getSelectedTile().isPlowed()) {
                if (player.getModelRef().getSelectedTile().waterTile()) {
                    super.useItem(player);
                }
            }
        }
    }
}
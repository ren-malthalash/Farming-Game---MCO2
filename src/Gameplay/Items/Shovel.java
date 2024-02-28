package Gameplay.Items;

import Gameplay.Player.Controller;

public class Shovel extends Tool {

    public Shovel() {
        this.itemName = "Shovel";
        this.useCost = 7.0f;
        this.itemDescription = "Removes withered crops from the field";
        this.bItemSell = false;
        this.itemSellPrice = 0;
        this.useExperience = 2.0f;
    }


    /**
     * @param player
     */
    @Override
    public void useItem(Controller player) {
        if (canUseItem(player)) {
            if (player.getModelRef().getSelectedTile().getCrop() != null) {
                if (player.getModelRef().getSelectedTile().getCrop().isWithered()) {
                    super.useItem(player);
                    player.getModelRef().getSelectedTile().harvestCrop(player);
                }
            }
        }
    }
}
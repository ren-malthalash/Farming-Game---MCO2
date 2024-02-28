package Gameplay.Items;

import Gameplay.Player.Controller;

public class Fertilizer extends Tool {

    public Fertilizer() {
        this.itemName = "Fertilizer";
        this.useCost = 10.0f;
        this.itemDescription = "Fertilizes a plowed tile";
        this.bItemSell = false;
        this.itemSellPrice = 0;
        this.useExperience = 4.0f;
    }


    /**
     * @param player
     */
    @Override
    public void useItem(Controller player) {
        // TODO Auto-generated method stub
        if (canUseItem(player)) {
            if (player.getModelRef().getSelectedTile().isPlowed()) {
                if (player.getModelRef().getSelectedTile().fertilizeTile()) {
                    super.useItem(player);
                }
            }
        }
    }

}
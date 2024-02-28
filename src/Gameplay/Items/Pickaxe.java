package Gameplay.Items;

import Gameplay.Player.Controller;

public class Pickaxe extends Tool {

    public Pickaxe() {
        this.itemName = "Pickaxe";
        this.useCost = 50.0f;
        this.itemDescription = "Removes stones from the field";
        this.bItemSell = false;
        this.itemSellPrice = 0;
        this.useExperience = 15.f;
    }


    /**
     * @param player
     */
    @Override
    public void useItem(Controller player) {
        if (canUseItem(player)) {
            if (player.getModelRef().getSelectedTile().isbRocked()) {
                super.useItem(player);
                player.getModelRef().getSelectedTile().deRock();
            }
        }
    }


    /**
     * @param player
     * @return boolean
     */
    @Override
    public boolean canUseItem(Controller player) {
        return player.getModelRef().getObjectcoins() >= useCost;
    }
}
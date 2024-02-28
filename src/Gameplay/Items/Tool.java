package Gameplay.Items;

import Gameplay.Player.Controller;

public abstract class Tool extends Item {


    protected double useExperience;
    protected double useCost;


    /**
     * @param player
     */
    @Override
    public void useItem(Controller player) {
        // TODO Auto-generated method stub
        super.useItem(player);
        player.gainExperience(useExperience);
        player.removeObjectcoins(useCost);
    }


    /**
     * @return String
     */
    @Override
    public String getItemDescription() {
        // TODO Auto-generated method stub
        return String.format("%s  //  Usage Cost = %.1f  //  Exp gain = %.1f", itemDescription, useCost, useExperience);
    }


    /**
     * @param player
     * @return boolean
     */
    @Override
    public boolean canUseItem(Controller player) {
        return player.getModelRef().getObjectcoins() >= useCost && !player.getModelRef().getSelectedTile().isbRocked();
    }
}
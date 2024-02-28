package UI;

import Gameplay.Gameplay.Shop;
import Gameplay.Player.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;

/**
 * Custom JButton / used to interact with the Shop
 */
public class ShopButton extends JButton {

    private final int assignedIndex;
    private final Shop shopRef;
    private final Controller playerRef;

    public ShopButton(String text, Controller player, Shop shop, int index) {
        super(text);
        this.playerRef = player;
        this.shopRef = shop;
        this.assignedIndex = index;
    }


    /**
     * @param event
     */
    @Override
    protected void fireActionPerformed(ActionEvent event) {
        super.fireActionPerformed(event);
        try {
            shopRef.purchaseItem(playerRef, assignedIndex);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
    }


}
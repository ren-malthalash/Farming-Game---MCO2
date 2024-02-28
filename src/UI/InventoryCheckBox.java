package UI;

import Gameplay.Player.Controller;
import Gameplay.Player.InventorySlot;

import javax.swing.*;
import java.awt.event.ItemEvent;

/**
 * Custom JCheckBox / used to interact with the Inventory
 */
public class InventoryCheckBox extends JCheckBox {

    private final Controller playerRef;
    private InventorySlot slotRef;

    public InventoryCheckBox(String text, Controller player, InventorySlot slot) {
        super(text);
        this.playerRef = player;
        this.slotRef = slot;
    }

    /**
     * Updates the CheckBox during gameplay
     *
     * @param text - new text to display
     * @param slot - new InventorySlot assigned to
     */
    public void updateCheckBox(String text, InventorySlot slot) {
        this.setText(text);
        slotRef = slot;
    }


    /**
     * @param event
     */
    @Override
    protected void fireItemStateChanged(ItemEvent event) {
        super.fireItemStateChanged(event);
        playerRef.setSelectedItem(slotRef);
    }
}

package Gameplay;

import Gameplay.Farm.Field;
import Gameplay.Farm.Tile;
import Gameplay.Items.Item;
import Gameplay.Player.Controller;
import UI.InventoryCheckBox;
import UI.ShopButton;
import UI.TextLabel;
import UI.TileButton;
import Gameplay.Player.InventorySlot;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Visualizes the data from GameModel and accepts the input from the user
 */
public class Viewport implements ActionListener {

    private final GameModel model;
    private final Controller player;

    private final JFrame frmMainmenu;
    private final JFrame frmGame;
    private final JFrame frmRestart;

    private final JPanel pnlInventory;
    private final JPanel pnlShop;
    private final JPanel pnlField;
    private final JPanel pnlPlayer;

    private JButton btnEndDay;
    private JButton btnFarmerUpgrade;

    private final JScrollPane scrlInventory;
    private final JScrollPane scrlShop;

    private TextLabel lblObjectCoins;
    private TextLabel lblDays;
    private TextLabel lblLevel;
    private TextLabel lblExp;
    private TextLabel lblSelectedItem;
    private TextLabel lblFarmerType;

    private final ButtonGroup inventoryButtonGroup;

    private final ArrayList<InventoryCheckBox> inventoryBoxes;
    private final ArrayList<TileButton> tileButtons;

    public Viewport(GameModel model, Controller player) {

        this.player = player;
        this.model = model;
        model.setViewportRef(this);

        this.frmMainmenu = new JFrame("Farming Game");
        this.frmGame = new JFrame("Farming Game!!!");
        this.frmRestart = new JFrame("Game Over!");

        this.pnlPlayer = new JPanel();
        this.pnlField = new JPanel();
        this.pnlInventory = new JPanel();
        this.pnlShop = new JPanel();

        this.scrlShop = new JScrollPane(pnlShop);
        this.scrlInventory = new JScrollPane(pnlInventory);

        this.inventoryButtonGroup = new ButtonGroup();

        this.inventoryBoxes = new ArrayList<InventoryCheckBox>();
        this.tileButtons = new ArrayList<TileButton>();

        this.frmRestart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frmRestart.setLayout(new FlowLayout(1, 0, 10));
        this.frmRestart.setSize(320, 240);

        this.frmMainmenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frmMainmenu.setLayout(new FlowLayout(1, 0, 10));
        this.frmMainmenu.setSize(320, 240);

        this.frmGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frmGame.setSize(1080, 480);
        this.frmGame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        this.frmGame.add(pnlPlayer, c);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0;
        c.weighty = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 3;
        c.gridheight = 2;
        c.ipady = 40;
        this.frmGame.add(pnlField, c);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.weighty = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.gridx = 4;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 2;
        this.frmGame.add(scrlShop, c);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        this.frmGame.add(scrlInventory, c);

        this.frmMainmenu.setVisible(true);
    }


    public void startGame() {
        frmMainmenu.dispose();
        initializeFieldPanel(model.getField());
        initializeShopPanel();
        initializePlayerDataPanel();
        initializeInventoryPanel();
        frmGame.setVisible(true);
    }

    public void initializeGameOverScreen() {
        frmGame.dispose();
        JButton btnExit = new JButton("Exit");
        JButton btnRestart = new JButton("Restart");

        JLabel lblPrompt = new JLabel(model.getExitCode() > 0 ? "You ran out of possible actions!" : "Your entire field has withered!");

        JPanel pnlGameOver = new JPanel(new GridBagLayout());
        JPanel pnlOptions = new JPanel(new FlowLayout(0, 3, 0));

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnRestart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.restart();
                frmRestart.dispose();
            }
        });

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        pnlGameOver.add(lblPrompt, c);

        c.gridx = 0;
        c.gridy = 1;
        pnlGameOver.add(pnlOptions, c);

        pnlOptions.add(btnRestart);
        pnlOptions.add(btnExit);

        frmRestart.add(pnlGameOver);
        frmRestart.setVisible(true);
    }

    public void initializeShopPanel() {
        int i = 0;
        pnlShop.setLayout(new BoxLayout(pnlShop, 1));
        for (Item item : model.getShop().getShopList()) {
            ShopButton temp = new ShopButton(item.getItemName() + ": G" + item.getItemCost(), player, model.getShop(), i);
            pnlShop.add(temp);
            i++;
        }
    }

    public void initializeInventoryPanel() {
        pnlInventory.setLayout(new BoxLayout(pnlInventory, 1));
        for (InventorySlot slot : model.getInventory().getItems()) {
            InventoryCheckBox temp = new InventoryCheckBox(slot.item.getItemName() + "[" + slot.stack + "]", player, slot);
            temp.setToolTipText(slot.item.getItemDescription());
            inventoryBoxes.add(temp);
            pnlInventory.add(temp);
            inventoryButtonGroup.add(temp);
        }
    }

    public void updateInventoryPanel() {
        int i = 0;
        for (InventorySlot slot : model.getInventory().getItems()) {
            if (i >= inventoryBoxes.size()) {
                InventoryCheckBox temp = new InventoryCheckBox(slot.item.getItemName() + "[" + slot.stack + "]", player, slot);
                temp.setToolTipText(slot.item.getItemDescription());
                inventoryBoxes.add(i, temp);
                pnlInventory.add(temp);
                inventoryButtonGroup.add(temp);
            } else {
                inventoryBoxes.get(i).updateCheckBox(slot.item.getItemName() + "[" + slot.stack + "]", slot);
                inventoryBoxes.get(i).setToolTipText(slot.item.getItemDescription());
                inventoryBoxes.get(i).setVisible(true);
            }
            i++;
        }
        for (int j = i; j < inventoryBoxes.size(); j++) {
            inventoryBoxes.get(j).setVisible(false);
        }
    }

    public void initializePlayerDataPanel() {
        pnlPlayer.setLayout(new BoxLayout(pnlPlayer, 1));

        lblObjectCoins = new TextLabel("Coins", String.format("%.2f", model.getObjectcoins()));
        lblDays = new TextLabel("Day", "1");
        lblLevel = new TextLabel("Level", "" + model.getLevel());
        lblExp = new TextLabel("EXP", "" + model.getExperience());
        lblSelectedItem = new TextLabel("Selected Item", "None");
        lblFarmerType = new TextLabel("Farmer Type", model.getFarmerBonus().getFarmerTypeString());

        btnEndDay = new JButton("End Day");

        btnEndDay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.getEventSystem().dayEnd();
            }
        });

        btnFarmerUpgrade = new JButton("");
        btnFarmerUpgrade.setVisible(false);
        btnFarmerUpgrade.addActionListener(this);

        pnlPlayer.add(lblObjectCoins);
        pnlPlayer.add(lblDays);
        pnlPlayer.add(lblFarmerType);
        pnlPlayer.add(lblLevel);
        pnlPlayer.add(lblExp);
        pnlPlayer.add(lblSelectedItem);
        pnlPlayer.add(btnEndDay);
        pnlPlayer.add(btnFarmerUpgrade);
    }

    public void updateDataPanel() {
        lblObjectCoins.setText("Coins", String.format("%.2f", model.getObjectcoins()));
        lblDays.setText("Day", "" + model.getEventSystem().getDays());
        lblLevel.setText("Level", "" + model.getLevel());
        lblExp.setText("EXP", String.format("%.1f", model.getExperience()));
        lblFarmerType.setText("Farmer Type", model.getFarmerBonus().getFarmerTypeString());
        pnlPlayer.setToolTipText(String.format("Bonus per Product: %d  Seed Discount: %d  Water Bonus: %d  Fertilizer Bonus: %d",
                model.getFarmerBonus().getBonusEarnings(),
                model.getFarmerBonus().getSeedCostReduc(),
                model.getFarmerBonus().getWaterBonusLimit(),
                model.getFarmerBonus().getFertilizerBonusLimit()));

        if (model.getLevel() >= model.getFarmerBonus().getMinLevel()) {
            btnFarmerUpgrade.setVisible(true);
            btnFarmerUpgrade.setText("Upgrade Farmer: " + String.format("%.0f", model.getFarmerBonus().getRegisterFee()));
            btnFarmerUpgrade.setActionCommand("Upgrade");
        }

        if (model.getSelectedItem() == null) {
            lblSelectedItem.setText("Selected Item", "None");
        } else {
            lblSelectedItem.setText("Selected Item", model.getSelectedItem().getItemName());
        }
    }


    /**
     * @param field
     */
    public void initializeFieldPanel(Field field) {
        pnlField.setLayout(new GridLayout(field.getFieldSize().y, field.getFieldSize().x, 1, 1));
        for (Tile tile : field.getTiles()) {
            TileButton temp = new TileButton(tile, player);
            temp.setPreferredSize(new Dimension(64, 64));
            tileButtons.add(temp);
            pnlField.add(temp);
        }
    }

    public void mainmenu() {
        JPanel pnlMainmenu = new JPanel();
        pnlMainmenu.setLayout(new BoxLayout(pnlMainmenu, BoxLayout.PAGE_AXIS));

        JLabel lblGameName = new JLabel("FARMING GAME", JLabel.CENTER);

        JButton btnStart = new JButton("Start!!!");
        JButton btnQuit = new JButton("EXIT");

        btnStart.setToolTipText("Start the adventure!");
        btnStart.addActionListener(this);

        btnQuit.setToolTipText("Exits the game");
        btnQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //mainmenuPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        pnlMainmenu.add(lblGameName);
        pnlMainmenu.add(Box.createRigidArea(new DimensionUIResource(0, 20)));
        pnlMainmenu.add(btnStart);
        pnlMainmenu.add(Box.createRigidArea(new DimensionUIResource(0, 10)));
        pnlMainmenu.add(btnQuit);
        lblGameName.setAlignmentX(0.5f);
        btnStart.setAlignmentX(JButton.CENTER_ALIGNMENT);
        btnQuit.setAlignmentX(JButton.CENTER_ALIGNMENT);
        this.frmMainmenu.add(pnlMainmenu, BorderLayout.CENTER);
    }


    /**
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Start!!!":
                startGame();
                break;
            case "Upgrade":
                if (model.getObjectcoins() >= model.getFarmerBonus().getRegisterFee()) {
                    model.removeObjectcoins(model.getFarmerBonus().getRegisterFee());
                    model.getFarmerBonus().increaseState();
                    updateDataPanel();
                }
                break;
            default:
                break;
        }
        System.out.println(e.getActionCommand());
    }
}

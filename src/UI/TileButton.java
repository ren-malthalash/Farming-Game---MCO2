package UI;

import Gameplay.Farm.Tile;
import Gameplay.Player.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Custom JButton / Used to represent the Tiles in Field to the user
 */
public class TileButton extends JButton {

    static private BufferedImage imageTile = null;
    static private BufferedImage imageTileWater = null;
    static private BufferedImage imageTileFertilized = null;
    static private BufferedImage imageTileSeeded = null;
    static private BufferedImage imageTileWithered = null;
    static private BufferedImage imageTilePlow = null;
    static private BufferedImage imageTileRock = null;

    private final Tile assignedTile;
    private final Controller playerRef;


    public TileButton(Tile tile, Controller player) {
        super();

        setPreferredSize(new Dimension(64, 64));

        //assignes button to a tile and vice versa
        assignedTile = tile;
        assignedTile.setAssignedButton(this);

        //saves a reference to the Controller
        playerRef = player;

        try {
            imageTile = ImageIO.read(new File("src/Images/Tile.png"));
            imageTileWater = ImageIO.read(new File("src/Images/Tile_Watered.png"));
            imageTileFertilized = ImageIO.read(new File("src/Images/Tile_Fertilized.png"));
            imageTileSeeded = ImageIO.read(new File("src/Images/Plant.png"));
            imageTileWithered = ImageIO.read(new File("src/Images/Tile_Withered.png"));
            imageTilePlow = ImageIO.read(new File("src/Images/Tile_Plowed.png"));
            imageTileRock = ImageIO.read(new File("src/Images/Obstruction_Rock.png"));
        } catch (Exception e) {
            System.out.println("IMAGE GETTER FAILED");
        }
    }

    /**
     * Updates tile button to match the stated of the assigned tile
     * by repainting it
     */
    public void updateTileButton() {
        repaint();
    }


    /**
     * @param event
     */
    @Override
    protected void fireActionPerformed(ActionEvent event) {
        super.fireActionPerformed(event);
        if (assignedTile.getCrop() != null) {
            if (assignedTile.getCrop().isHarvestTime()) {
                assignedTile.harvestCrop(playerRef);
            }
        }
        playerRef.useSelectedItem(assignedTile);
    }


    /**
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (assignedTile.isWatered()) {
            draw(imageTileWater, g);
        } else {
            draw(imageTile, g);
        }
        if (assignedTile.isbRocked()) {
            draw(imageTileRock, g);
        } else {
            if (assignedTile.getCrop() != null) {
                if (assignedTile.getCrop().isWithered()) {
                    draw(imageTileWithered, g);
                } else {
                    if (assignedTile.isPlowed()) {
                        draw(imageTilePlow, g);
                    }
                    if (assignedTile.isFertilized()) {
                        draw(imageTileFertilized, g);
                    }
                    if (assignedTile.getCrop().isReadyHarvest()) {
                        draw(assignedTile.getCrop().getHarvestImage(), g);
                    } else {
                        if (assignedTile.getCrop() != null) {
                            draw(imageTileSeeded, g);
                        }
                    }
                }
            } else {
                if (assignedTile.isPlowed()) {
                    draw(imageTilePlow, g);
                }
                if (assignedTile.isFertilized()) {
                    draw(imageTileFertilized, g);
                }
                if (assignedTile.getCrop() != null) {
                    draw(imageTileSeeded, g);
                }
            }
        }
    }

    /**
     * Custom draw
     *
     * @param img
     * @param g
     */
    private void draw(BufferedImage img, Graphics g) {
        g.drawImage(img, (this.getWidth() - 32) / 2, (this.getHeight() - 32) / 2, getFocusCycleRootAncestor());
    }
}

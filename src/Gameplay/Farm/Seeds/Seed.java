package Gameplay.Farm.Seeds;

import Gameplay.Farm.CropType;
import Gameplay.Items.Item;
import Gameplay.Player.Controller;
import Utility.IntPoint;

import java.awt.image.BufferedImage;
//import Gameplay.Items.CropProduct;

/**
 * seed class
 * <p>
 * holds data regarding seed types
 * <p>
 * extends from item
 */
public abstract class Seed extends Item {


    protected CropType cropType;
    protected int harvestTime;
    protected IntPoint waterNeeds;
    protected IntPoint fertilizer;
    protected IntPoint productsProduced;
    protected double sellingPrice;
    protected double experienceYield;
    protected BufferedImage harvestImage = null;

    /**
     * @return BufferedImage
     */
    //protected CropProduct produce; Unused until MCO2
    public BufferedImage getHarvestImage() {
        return harvestImage;
    }

    /**
     * @return CropType
     */
    public CropType getCropType() {
        return cropType;
    }

    /**
     * @return int
     */
    public int getHarvestTime() {
        return harvestTime;
    }

    /**
     * @return IntPoint
     */
    public IntPoint getWaterNeeds() {
        return waterNeeds;
    }

    /**
     * @return IntPoint
     */
    public IntPoint getFertilizerNeeds() {
        return fertilizer;
    }

    /**
     * @return IntPoint
     */
    public IntPoint getProductsProduced() {
        return productsProduced;
    }

    /**
     * @return double
     */
    public double getSellingPrice() {
        return sellingPrice;
    }

    /**
     * @return double
     */
    public double getExperienceYield() {
        return experienceYield;
    }


    /**
     * @param player
     */
    @Override
    public void useItem(Controller player) {
        // TODO Auto-generated method stub
        if (canUseItem(player)) {
            if (cropType == CropType.FRUIT_TREE) {
                if (!player.getModelRef().getField().validTreePlant(player.getModelRef().getSelectedTile())) {
                    return;
                }
            }
            if (player.getModelRef().getSelectedTile().plantSeed(this)) {
                player.removeItem(this);
                super.useItem(player);
            }
        }
    }
}



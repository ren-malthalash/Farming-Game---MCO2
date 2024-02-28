package Gameplay.Farm.Seeds;

import Gameplay.Farm.CropType;
import Utility.IntPoint;

import javax.imageio.ImageIO;
import java.io.File;

public class Sunflower extends Seed {

    public Sunflower() {
        this.itemName = "Sunflower";
        this.cropType = CropType.FLOWER;
        this.harvestTime = 3;
        this.waterNeeds = new IntPoint(2, 3);
        this.fertilizer = new IntPoint(1, 2);
        this.productsProduced = new IntPoint(1, 1);
        this.itemCost = 20.0f;
        this.sellingPrice = 19;
        this.experienceYield = 7.5f;
        this.itemDescription = "Sunflower Seeds";
        try {
            this.harvestImage = ImageIO.read(new File("src/Images/Flower_Turnips.png"));
        } catch (Exception e) {
        }
    }
}

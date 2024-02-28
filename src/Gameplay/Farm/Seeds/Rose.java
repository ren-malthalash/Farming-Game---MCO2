package Gameplay.Farm.Seeds;

import Gameplay.Farm.CropType;
import Utility.IntPoint;

import javax.imageio.ImageIO;
import java.io.File;

public class Rose extends Seed {

    public Rose() {
        this.itemName = "Rose";
        this.cropType = CropType.FLOWER;
        this.harvestTime = 1;
        this.waterNeeds = new IntPoint(1, 2);
        this.fertilizer = new IntPoint(0, 1);
        this.productsProduced = new IntPoint(1, 1);
        this.itemCost = 5.0f;
        this.sellingPrice = 5;
        this.experienceYield = 2.5f;
        this.itemDescription = "Rose Seeds";
        try {
            this.harvestImage = ImageIO.read(new File("src/Images/Flower_Rose.png"));
        } catch (Exception e) {
        }
    }
}

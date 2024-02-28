package Gameplay.Farm.Seeds;

import Gameplay.Farm.CropType;
import Utility.IntPoint;

import javax.imageio.ImageIO;
import java.io.File;

public class Turnips extends Seed {

    public Turnips() {
        this.itemName = "Turnips";
        this.cropType = CropType.FLOWER;
        this.harvestTime = 2;
        this.waterNeeds = new IntPoint(2, 3);
        this.fertilizer = new IntPoint(0, 1);
        this.productsProduced = new IntPoint(1, 1);
        this.itemCost = 10.0f;
        this.sellingPrice = 9;
        this.experienceYield = 5.0f;
        this.itemDescription = "Turnips (F) Seeds";
        try {
            this.harvestImage = ImageIO.read(new File("src/Images/Flower_Turnips.png"));
        } catch (Exception e) {
        }
    }
}

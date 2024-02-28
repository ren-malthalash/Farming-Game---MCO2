package Gameplay.Farm.Seeds;

import Gameplay.Farm.CropType;
import Utility.IntPoint;

import javax.imageio.ImageIO;
import java.io.File;

public class Turnip extends Seed {

    public Turnip() {
        this.itemName = "Turnip";
        this.cropType = CropType.ROOTCROP;
        this.harvestTime = 2;
        this.waterNeeds = new IntPoint(1, 2);
        this.fertilizer = new IntPoint(0, 1);
        this.productsProduced = new IntPoint(1, 2);
        this.itemCost = 5.0f;
        this.sellingPrice = 6;
        this.experienceYield = 5.0f;
        this.itemDescription = "Turnip seeds";
        try {
            this.harvestImage = ImageIO.read(new File("src/Images/Crop_Turnip.png"));
        } catch (Exception e) {
        }
    }
}

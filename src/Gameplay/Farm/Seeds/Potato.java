package Gameplay.Farm.Seeds;

import Gameplay.Farm.CropType;
import Utility.IntPoint;

import javax.imageio.ImageIO;
import java.io.File;

public class Potato extends Seed {

    public Potato() {
        this.itemName = "Potato";
        this.cropType = CropType.ROOTCROP;
        this.harvestTime = 5;
        this.waterNeeds = new IntPoint(3, 4);
        this.fertilizer = new IntPoint(1, 2);
        this.productsProduced = new IntPoint(1, 10);
        this.itemCost = 20.0f;
        this.sellingPrice = 3;
        this.experienceYield = 12.5f;
        this.itemDescription = "Potato seeds";
        try {
            this.harvestImage = ImageIO.read(new File("src/Images/Crop_Potato.png"));
        } catch (Exception e) {
        }
    }
}

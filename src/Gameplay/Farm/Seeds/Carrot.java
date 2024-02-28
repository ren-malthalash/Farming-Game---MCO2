package Gameplay.Farm.Seeds;

import Gameplay.Farm.CropType;
import Utility.IntPoint;

import javax.imageio.ImageIO;
import java.io.File;

public class Carrot extends Seed {

    public Carrot() {
        this.itemName = "Carrot";
        this.cropType = CropType.ROOTCROP;
        this.harvestTime = 3;
        this.waterNeeds = new IntPoint(1, 2);
        this.fertilizer = new IntPoint(0, 1);
        this.productsProduced = new IntPoint(1, 2);
        this.itemCost = 10.0f;
        this.sellingPrice = 9;
        this.experienceYield = 7.5f;
        this.itemDescription = "Carrot seeds";
        try {
            this.harvestImage = ImageIO.read(new File("src/Images/Crop_Carrot.png"));
        } catch (Exception e) {
        }
    }
}

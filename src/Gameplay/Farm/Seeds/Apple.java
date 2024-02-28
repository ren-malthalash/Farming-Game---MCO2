package Gameplay.Farm.Seeds;

import Gameplay.Farm.CropType;
import Utility.IntPoint;

import javax.imageio.ImageIO;
import java.io.File;

public class Apple extends Seed {

    public Apple() {
        this.itemName = "Apple";
        this.cropType = CropType.FRUIT_TREE;
        this.harvestTime = 10;
        this.waterNeeds = new IntPoint(7, 7);
        this.fertilizer = new IntPoint(5, 5);
        this.productsProduced = new IntPoint(10, 15);
        this.itemCost = 200.0f;
        this.sellingPrice = 5;
        this.experienceYield = 25.0f;
        this.itemDescription = "Apple Seeds";
        try {
            this.harvestImage = ImageIO.read(new File("src/Images/Tree_Apple.png"));
        } catch (Exception e) {
        }
    }
}

package Gameplay.Farm.Seeds;

import Gameplay.Farm.CropType;
import Utility.IntPoint;

import javax.imageio.ImageIO;
import java.io.File;

public class Mango extends Seed {

    public Mango() {
        this.itemName = "Mango";
        this.cropType = CropType.FRUIT_TREE;
        this.harvestTime = 10;
        this.waterNeeds = new IntPoint(7, 7);
        this.fertilizer = new IntPoint(4, 4);
        this.productsProduced = new IntPoint(5, 15);
        this.itemCost = 100.0f;
        this.sellingPrice = 8;
        this.experienceYield = 25.0f;
        this.itemDescription = "Mango Seeds";
        try {
            this.harvestImage = ImageIO.read(new File("src/Tree_Mango.png"));
        } catch (Exception e) {
        }
    }
}

package Gameplay.Farm;

import Gameplay.Farm.Seeds.Seed;
import Gameplay.Gameplay.EventSystem.EventSystem;
import Gameplay.Gameplay.EventSystem.EventSystemInterface;
import Gameplay.Gameplay.EventSystem.Events;
import Gameplay.Player.Controller;

import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents the Seed in a Tile
 */
public class Crop implements EventSystemInterface {

    private final Seed seed;
    private int span;

    private boolean bReadyHarvest;
    private boolean bWithered;
    private final Tile tileRef;
    private final EventSystem eventSystemRef;
    private int water;
    private int fertilizer;
    private final BufferedImage harvestImage;

    private final String cropName;

    /**
     * default constructor
     *
     * @param seed    - the seed the crop is using / is going to become
     * @param tileRef - tile reference
     */
    public Crop(Seed seed, Tile tileRef) {
        this.seed = seed;
        this.cropName = seed.getItemName();
        this.harvestImage = seed.getHarvestImage();
        this.span = 0;
        this.bWithered = false;
        this.bReadyHarvest = false;
        this.water = 0;
        this.fertilizer = 0;
        this.tileRef = tileRef;
        this.eventSystemRef = tileRef.getFieldRef().getEventSystemRef();
    }


    /**
     * @return String
     */
    public String getCropName() {
        return cropName;
    }


    /**
     * @return BufferedImage
     */
    public BufferedImage getHarvestImage() {
        return harvestImage;
    }


    /**
     * @return boolean
     */
    public boolean isReadyHarvest() {
        return bReadyHarvest;
    }


    /**
     * @return boolean
     */
    public boolean isWithered() {
        return bWithered;
    }

    /**
     * Checks to see if the crop can be harvested
     *
     * @return - returns true if the crop can be harvested
     */
    public boolean isHarvestTime() {
        return seed.getHarvestTime() == span;
    }

    /**
     * Harvests the crop from the tile. Used to also harvest withered crops without the earnings.
     *
     * @param player - player to receive earnings and harvest
     * @return - returns true if the crop is removable and false if otherwise
     */
    public boolean harvestCrop(Controller player) {
        if (!bWithered) {
            if (bReadyHarvest) {
                int random = ThreadLocalRandom.current().nextInt(seed.getProductsProduced().x, seed.getProductsProduced().y + 1);
                double tempEarnings = calculateEarnings(player, random);
                player.addObjectcoins(tempEarnings);
                player.gainExperience(seed.getExperienceYield());
                eventSystemRef.notifyObservers(Events.HARVEST_END, tileRef);
                tileRef.removeCrop();
                return true;
            }
            return false;
        } else {
            eventSystemRef.notifyObservers(Events.HARVEST_END, tileRef);
            tileRef.removeCrop();
            return true;
        }
    }

    /**
     * Calculates the earnings of the crop upon harvest
     *
     * @param player - player to receive earnings / player to retrieve bonuses from
     * @return - returns the coin yield of the crop
     */
    public double calculateEarnings(Controller player, int random) {
        double harvestTotal = random * (seed.getSellingPrice() + player.getModelRef().getFarmerBonus().getBonusEarnings());
        double waterBonus = harvestTotal * 0.2f * (water - 1);
        double fertilizerBonus = harvestTotal * 0.5f * (fertilizer);
        //System.out.println("INCOME: " + harvestTotal + " " + waterBonus + " " + fertilizerBonus);
        if (seed.getCropType() == CropType.FLOWER) {
            return (harvestTotal + waterBonus + fertilizerBonus) * 1.1f;
        }
        return harvestTotal + waterBonus + fertilizerBonus;
    }

    /**
     * Withers the crop
     */
    public void witherCrop() {
        bWithered = true;
    }


    /**
     * @param event
     * @param days
     * @param payload
     */
    @Override
    public void eventNotify(Events event, int days, Object payload) {
        switch (event) {
            case END_DAY:
                /**
                 * Water and fertilizer is calculated at the end of the day
                 * Therefore you can water and fertilize the tile before placing in the crop
                 * Without compromising its benefits
                 */
                span++;
                /**
                 * Clamp water and fertilizer counts to maximum bonus
                 */
                if (tileRef.isWatered()) {
                    water++;
                    if (water > seed.getWaterNeeds().y) {
                        water = seed.getWaterNeeds().y;
                    }
                }
                if (tileRef.isFertilized()) {
                    fertilizer++;
                    if (fertilizer > seed.getFertilizerNeeds().y) {
                        fertilizer = seed.getFertilizerNeeds().y;
                    }
                }
                /**
                 * If the amount of days the crop is alive (span) is less than or equal to the harvest time
                 * Check if its ready to be harvested
                 *
                 * If it is not then the crop withers
                 */
                if (span <= seed.getHarvestTime()) {
                    if (isHarvestTime()) {
                        /**
                         * If the crop did not meet its requirements by the time it has reached
                         * its harvest time, then wither the crop
                         *
                         * Otherwise, the crop becomes harvestable
                         */
                        if (water >= seed.getWaterNeeds().x && fertilizer >= seed.getFertilizerNeeds().x) {
                            bReadyHarvest = true;
                        } else {
                            witherCrop();
                        }
                    }
                } else {
                    witherCrop();
                }
                break;
            case HARVEST_CROP:
                if (payload != null) {
                    if (payload instanceof Controller) {
                        tileRef.harvestCrop((Controller) payload);
                    }
                }
                break;

            default:
                break;
        }
    }
}

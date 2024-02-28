package Utility;

/**
 * Holds data regarding the player and their bonuses
 */
public class FarmerType {

    private int farmerType;

    private int bonusEarnings;
    private int seedCostReduc;
    private int waterBonusLimit;
    private int fertilizerBonusLimit;
    private double registerFee;
    private int minLevel;

    public FarmerType() {
        this.farmerType = 0;
        this.bonusEarnings = 0;
        this.seedCostReduc = 0;
        this.waterBonusLimit = 0;
        this.fertilizerBonusLimit = 0;
        this.registerFee = 200;
        this.minLevel = 5;
    }


    /**
     * @return int
     */
    public int getMinLevel() {
        return minLevel;
    }


    /**
     * @return double
     */
    public double getRegisterFee() {
        return registerFee;
    }

    /**
     * Increments the farmer bonus type
     */
    public void increaseState() {
        setFarmerType(++farmerType);
    }


    /**
     * @return int
     */
    public int getFarmerType() {
        return farmerType;
    }

    /**
     * Sets the current type of the farmer bonus to a certain value
     *
     * @param type new state value
     */
    private void setFarmerType(int type) {
        if (this.farmerType < type) {
            if (type > 3) {
                this.farmerType = 3;
            } else {
                this.farmerType = type;
            }
        }
        switch (farmerType) {
            default:
                this.farmerType = 0;
                this.bonusEarnings = 0;
                this.seedCostReduc = 0;
                this.waterBonusLimit = 0;
                this.fertilizerBonusLimit = 0;
                this.minLevel = 5;
                break;
            case 1:
                this.bonusEarnings = 1;
                this.seedCostReduc = -1;
                this.waterBonusLimit = 0;
                this.fertilizerBonusLimit = 0;
                this.registerFee = 300;
                this.minLevel = 10;
                break;
            case 2:
                this.bonusEarnings = 2;
                this.seedCostReduc = -2;
                this.waterBonusLimit = 1;
                this.fertilizerBonusLimit = 0;
                this.registerFee = 400;
                this.minLevel = 15;
                break;
            case 3:
                this.bonusEarnings = 4;
                this.seedCostReduc = -3;
                this.waterBonusLimit = 2;
                this.fertilizerBonusLimit = 1;
                this.registerFee = 0;
                this.minLevel = 99999999;
                break;
        }
    }

    /**
     * @return String
     */
    public String getFarmerTypeString() {
        switch (farmerType) {
            default:
                return "Farmer";
            case 1:
                return "Registered Farmer";
            case 2:
                return "Distinguished Farmer";
            case 3:
                return "Legendary Farmer";
        }
    }

    /**
     * @return int
     */
    public int getBonusEarnings() {
        return bonusEarnings;
    }

    /**
     * @return int
     */
    public int getSeedCostReduc() {
        return seedCostReduc;
    }

    /**
     * @return int
     */
    public int getWaterBonusLimit() {
        return waterBonusLimit;
    }

    /**
     * @return int
     */
    public int getFertilizerBonusLimit() {
        return fertilizerBonusLimit;
    }

}

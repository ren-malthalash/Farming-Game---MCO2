package Utility;

/**
 * Custom int-based 2d Vector
 */
public class IntPoint {
    public int x;
    public int y;

    public IntPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     * @param x
     * @param y
     * @return IntPoint
     */
    public IntPoint addRaw(int x, int y) {
        return new IntPoint(this.x + x, this.y + y);
    }
}

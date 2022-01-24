package mathengine;

import java.util.Random;

/**
 * GameMath.java
 *
 * Contains methods to calculate the x and y
 * bounds for randomising the coordinates
 * to place the food and poisons.
 */
public class GameMath
{
    /**
     * Generate a random x position for left screen
     *
     * @param rand:     Random object
     * @param center:   Center screen position
     * @param unitSize: Size of unit
     * @return randomly selected x position for left screen
     */
    public static int getRandomLeftX(Random rand, int center, int unitSize)
    {
        return rand.nextInt(calculateXBound(center, unitSize)) * unitSize;
    }

    /**
     * Generate a random x position for right screen
     *
     * @param rand:     Random object
     * @param center:   Center screen position
     * @param unitSize: Size of unit
     * @return randomly selected x position for right screen
     */
    public static int getRandomRightX(Random rand, int center, int unitSize)
    {
        return ((rand.nextInt(calculateXBound(center, unitSize))) + calculateXBound(center, unitSize)) * unitSize;
    }

    /**
     * Generate a random y position for game screen
     *
     * @param rand:     Random object
     * @param yPos:     y position
     * @param height:   Height of game window
     * @param unitSize: Size of unit
     * @return randomly generated y position
     */
    public static int getRandomY(Random rand, int yPos, int height, int unitSize)
    {
        return yPos + (rand.nextInt(GameMath.calculateYBound(height, unitSize)) * unitSize);
    }

    /**
     * Calculate bound for random generating of x position
     *
     * @param center:   Center x position
     * @param unitSize: Size of unit
     * @return bound
     */
    private static int calculateXBound(int center, int unitSize)
    {
        return center / unitSize;
    }

    /**
     * Calculate bound for random generating of y position
     *
     * @param height:   Height of window
     * @param unitSize: Size of unit
     * @return bound
     */
    private static int calculateYBound(int height, int unitSize)
    {
        return (height-unitSize) / unitSize;
    }
}

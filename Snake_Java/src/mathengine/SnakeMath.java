package mathengine;

/**
 * SnakeMath.java
 *
 * Contains methods to calculate the x and y positions
 * for both snake 1 and snake 2.
 */
public class SnakeMath
{
    /**
     * Calculate the starting x position of snake 1
     *
     * @param unitSize: Size of unit
     * @return Snake 1 starting x position
     */
    public static int calculateSnake1X(int unitSize)
    {
        return 5 * unitSize;
    }

    /**
     * Calculate the starting y position of snake 1
     *
     * @param gameYPos: Y position of game screen
     * @param unitSize: Size of unit
     * @return Snake 1 starting y position
     */
    public static int calculateSnake1Y(int gameYPos, int unitSize)
    {
        return gameYPos + 10 * unitSize;
    }

    /**
     * Calculate the starting x position of snake 2
     *
     * @param center:   Y position of center screen
     * @param unitSize: Size of unit
     * @return Snake 2 starting x position
     */
    public static int calculateSnake2X(int center, int unitSize)
    {
        return center + (18 * unitSize);
    }

    /**
     * Calculate the starting y position of snake 2
     *
     * @param gameYPos: Y position of game screen
     * @param unitSize: Size of unit
     * @return Snake 2 starting y position
     */
    public static int calculateSnake2Y(int gameYPos, int unitSize)
    {
        return gameYPos + 10 * unitSize;
    }

}

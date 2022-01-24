package mathengine;

/**
 * SettingsMath.java
 *
 * Contains methods to do calculations for
 * the screen sizes and x and y positions
 * for the game window.
 */
public class SettingsMath
{
    /**
     * Calculate width of window
     *
     * @param unitSize: Size of one unit
     * @return Window width
     */
    public static int calculateWindowWidth(int unitSize)
    {
        return unitSize * 48;
    }

    /**
     * Calculate height of window
     *
     * @param unitSize: Size of one unit
     * @return Window height
     */
    public static int calculateWindowHeight(int unitSize)
    {
        return unitSize * 28;
    }

    /**
     * Calculate the height of game screen
     *
     * @param windowHeight: Height of window
     * @param unitSize:     Size of one unit
     * @return Height of game screen
     */
    public static int calculateGameHeight(int windowHeight, int unitSize)
    {
       return windowHeight - 3*unitSize;
    }

    /**
     * Calculate the x position of center screen
     *
     * @param windowWidth: Width of window
     * @return Center position of window
     */
    public static int calculateCenterPosition(int windowWidth)
    {
        return windowWidth / 2;
    }

    /**
     * Calculate the starting y position of game screen
     *
     * @param windowHeight: Height of window
     * @param gameHeight:   Height of game screen
     * @return Starting y position of game screen
     */
    public static int calculateGameYPosition(int windowHeight, int gameHeight)
    {
        return windowHeight - gameHeight;
    }
}

package mathengine;

import java.awt.*;

/**
 * DisplayMath.java
 *
 * Contains methods that deals with calculating
 * the center positions for x and y coordinates
 * for text display purposes.
 */
public class DisplayMath
{
    /**
     * Calculate center x position based on text and window size
     *
     * @param width:   width of window
     * @param metrics: metrics of font
     * @param text:    string text
     * @return calculated x center position
     */
    public static int getXCenter(int width, FontMetrics metrics, String text)
    {
        return (width - metrics.stringWidth(text)) / 2;
    }

    /**
     * Calculate center y position based on text and window size
     *
     * @param metrics: metrics of font
     * @param height:  height of window
     * @return calculated y center position
     */
    public static int getYCenter(FontMetrics metrics, int height)
    {
        return ((height - metrics.getHeight()) / 2) + metrics.getAscent();
    }
}

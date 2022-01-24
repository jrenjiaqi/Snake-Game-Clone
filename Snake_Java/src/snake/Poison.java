package snake;

import javax.swing.*;
import java.awt.*;

/**
 * Poison.java
 *
 * Contains attributes and methods for drawing
 * and displaying of poisons
 */
public class Poison extends Component implements Drawable
{
    private int xPos, yPos;
    private static final String imgPath ="images/poison.png";

    public Poison(int x, int y)
    {
        xPos = x;
        yPos = y;
    }

    /**
     * Draws an image of a poison
     *
     * @param g: Graphics object
     */
    public void draw(Graphics g)
    {
        ImageIcon img = new ImageIcon(imgPath);
        img.paintIcon(this, g, xPos, yPos);
    }

    /**
     * @return x position of a poison
     */
    public int getXPos()
    {
        return xPos;
    }

    /**
     * @param xPos:	x position to set
     */
    public void setXPos(int xPos)
    {
        this.xPos = xPos;
    }

    /**
     * @return y position of a poison
     */
    public int getYPos()
    {
        return yPos;
    }

    /**
     * @param yPos: y position to set
     */
    public void setYPos(int yPos)
    {
        this.yPos = yPos;
    }
}

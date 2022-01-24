package snake;

import javax.swing.*;
import java.awt.*;

/**
 * Food.java
 *
 * Contains attributes and methods for drawing
 * and displaying of food.
 */
public class Food extends Component implements Drawable
{
    private int xPos, yPos;
    private static final String foodImg = "images/donut.png";

    public Food(int xPos, int yPos)
    {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * Draws an image of a donut
     *
     * @param g: Graphics object
     */
    public void draw(Graphics g)
    {
        ImageIcon img = new ImageIcon(foodImg);
        img.paintIcon(this, g, xPos, yPos);
    }

    /**
     * @return x position of food
     */
    public int getXPos()
    {
        return xPos;
    }

    /**
     * @param xPos: x position to set
     */
    public void setXPos(int xPos)
    {
        this.xPos = xPos;
    }

    /**
     * @return y position of food
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

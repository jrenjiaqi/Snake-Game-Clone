package snake;

import javax.swing.*;
import java.awt.*;

/**
 * Body.java
 *
 * Contains attributes and methods for drawing and displaying of
 * the snake body parts.
 */
public class Body extends Component implements Movable
{
    private int xPos;
    private int yPos;
    private final int width;
    private final int height;

    private Color color;

    private final String downImg = "images/downmouth.png";
    private final String rightImg = "images/rightmouth.png";
    private final String upImg = "images/upmouth.png";
    private final String leftImg = "images/leftmouth.png";

    public Body(int xPos, int yPos, int tileSize, Color color)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        width = tileSize;
        height = tileSize;
        this.color = color;
    }

    /**
     * Method to draw the body of a snake
     *
     * @param g: Graphics object
     */
    public void drawBody(Graphics g)
    {
        g.setColor(this.color);
        g.fillRect(xPos , yPos, width, height);
    }

    /**
     * Method to draw the head of a snake
     * @param g: Graphics object
     */
    public void drawHead(Graphics g)
    {
        g.setColor(this.color);
        g.fillOval(xPos , yPos, width, height);
    }

    /**
     * Method to draw the head of a snake depending
     * on the direction of where the snake is facing
     *
     * @param g:         Graphics object
     * @param direction: Direction of where the snake is facing
     */
    public void drawHead(Graphics g, char direction)
    {
        ImageIcon img;
        switch (direction)
        {
            case 'U':
                img = new ImageIcon(upImg);
                break;
            case 'L':
                img = new ImageIcon(leftImg);
                break;
            case 'R':
                img = new ImageIcon(rightImg);
                break;
            case 'D':
                img = new ImageIcon(downImg);
                break;
            default:
                img = new ImageIcon();
                break;
        }

        img.paintIcon(this, g, xPos, yPos);
    }
    
    public void draw(Graphics g) {/* No implementation is necessary */}

    /**
     * @return x position of a body part
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
     * @return y position of a body part
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

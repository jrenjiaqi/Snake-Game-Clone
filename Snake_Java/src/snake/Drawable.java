package snake;

import java.awt.Graphics;

/**
 * Drawable.java
 *
 * Interface class for drawables like food and body
 */
public interface Drawable
{
	/**
	 * For drawing graphics like images
	 * @param g: Graphics object
	 */
    void draw(Graphics g);

	/**
	 * Getter for x positions
	 *
	 * @return x position
	 */
	int getXPos();

	/**
	 * Getter for y positions
	 *
	 * @return y position
	 */
    int getYPos();

	/**
	 * Setters for x positions
	 *
	 * @param xPos:	x position to set
	 */
	void setXPos(int xPos);

	/**
	 * Setters for y positions
	 *
	 * @param yPos: y position to set
	 */
    void setYPos(int yPos);
}

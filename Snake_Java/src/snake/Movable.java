package snake;

import java.awt.Graphics;

/**
 * Movable.java
 *
 * Interface for movables like head or body parts.
 */
public interface Movable extends Drawable
{
    /**
     * For drawing of snake body parts
     * @param g: Graphics object
     */
    void drawBody(Graphics g);

    /**
     * For drawing of snake head
     * @param g: Graphics object
     */
    void drawHead(Graphics g);

    /**
     * For drawing of snake head
     * with image
     *
     * @param g:         Graphics object
     * @param direction: Direction of where the snake is facing
     */
    void drawHead(Graphics g, char direction);
}

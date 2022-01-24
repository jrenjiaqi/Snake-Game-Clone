package physicsengine;

import snake.Drawable;
import snake.Movable;

/**
 * SnakePhysics.java
 *
 * Contains methods to check if the snake hit the food/poisons
 * and it the snake ate itself.
 */
public class SnakePhysics
{
    /**
     * Checks if the coordinates of the snake's is the same as
     * the coordinates of food and poison
     *
     * @param food: Food/Poison objects
     * @param x:    x coordinate of snake head
     * @param y:    y coordinate of snake head
     * @return true if coordinates of snake head and food is the same
     */
    public static boolean hitFood(Drawable food, int x, int y)
    {
        return x == food.getXPos() && y == food.getYPos();
    }

    /**
     * Check if the coordinates of the snake's head is the same as the body
     * part
     *
     * @param body: Body object
     * @param x:    x coordinate of snake head
     * @param y:    y coordinate of snake head
     * @return true if coordinates of snake head and body is the same
     */
    public static boolean hitBody(Movable body, int x, int y)
    {
        return x == body.getXPos() && y == body.getYPos();
    }
}

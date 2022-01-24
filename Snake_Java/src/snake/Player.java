package snake;

/**
 * Player.java
 *
 * Contains attributes and methods for calculating
 * a player's lives and score.
 */
public class Player extends AbstractPlayer
{
    private int lives;
    private int score;
    private boolean lose;

    public Player()
    {
        lives = defaultLives;
        score = 0;
        lose = false;
    }

    /**
     * Increment score by a set amount
     */
    public void addScore()
    {
        score += scoreIncrement;
    }

    /**
     * Deduct score by a set amount
     */
    public void minusScore()
    {
        score -= scoreDecrement;
    }

    /**
     * Deduct a live
     */
    public void minusLives()
    {
        lives--;
    }

    /**
     * @return lives left for a player
     */
	public int getLives()
    {
        return lives;
    }

    /**
     * @param lives: lives to set
     */
    public void setLives(int lives)
    {
        this.lives = lives;
    }

    /**
     * @return score of a player
     */
    public int getScore()
    {
        return score;
    }

    /**
     * @param score: score to set for a player
     */
    public void setScore(int score)
    {
        this.score = score;
    }

    /**
     * @return true if a player lost
     */
    public boolean isLose()
    {	
    	
        return lose;
    }

    /**
     * @param lose: set to true to indicate lost
     */
    public void setLose(boolean lose)
    {
        this.lose = lose;
    }
}

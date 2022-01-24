package snake;

/**
 * AbstractPlayer.java
 *
 * Abstract class for player that contains
 * attributes and methods necessary for all player objects
 */
public abstract class AbstractPlayer
{
	
    protected final int scoreIncrement = 10;
    protected final int scoreDecrement = 5;
    protected final int defaultLives = 3;
	
	abstract public void addScore();
	abstract public void minusScore();
	abstract public void minusLives();
	abstract public int getLives();
	abstract public void setLives(int lives);
	abstract public int getScore();
	abstract public void setScore(int score);
	abstract public boolean isLose();
	abstract public void setLose(boolean lose);
}

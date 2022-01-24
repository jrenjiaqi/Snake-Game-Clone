package snake;

import javax.swing.*;
import java.awt.*;


/**
 * MultipleGameCallException.java
 *
 * Contains custom exception which indicates there is code that is trying to create new Game() instance.
 * Defensive exception: highlight and prevent unwanted behaviour via raising exceptions.
 */
class MultipleGameCallException extends Exception
{
	MultipleGameCallException(String msg)
    {
        super(msg);
    }
}

/**
 * Game.java
 *
 * Class that contains the main method.
 * Set up frame for the program.
 */
public class Game
{
    // Game is a singleton class.
    // Lazy initialisation of singleton.
    private static Game game;

    private Game()
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setPreferredSize(new Dimension(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT));
        frame.pack();
        frame.setTitle("Black Mamba");
        frame.setBackground(Color.WHITE);
        frame.add(new GamePanel());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Checks if game is already created
     * @throws MultipleGameCallException when try to create another instance of game object
     */
    public static void getGame() throws MultipleGameCallException
    {
        if (game == null)
            game = new Game();
        else
            throw new MultipleGameCallException("Cannot have multiple instance of Game() class!");
	}

    /**
     * Main method to setup and run the game
     * @param args: Arguments if needed
     */
    public static void main(String[] args)
    {
        try
        {
            Game.getGame();
        }
        catch (MultipleGameCallException e)
        {
            // find the code that is calling another instance of Game() to debug.
            System.out.println(e.toString());
            e.printStackTrace();
            System.exit(0);
        }
    }
}
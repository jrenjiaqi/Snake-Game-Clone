package snake;

import javax.swing.*;
import java.io.*;

/**
 * HighScore.java
 *
 * Contains methods for checking and inserting
 * new high scores into a DAT file.
 */
public class HighScore
{
    private static final String FILE_PATH = "highscore.dat";

    /**
     * Get player's name when a new high score is hit
     * and insert the score into highscore.dat file
     */
    public static void checkScore(Timer timer, String highScore, Player player1, Player player2)
    {
        // Stop timer so that game stops looping
        timer.stop();
        String name;

        if (highScore.equals("")) return;

        // Split name and score
        if (player1.getScore() > Integer.parseInt((getHighScore().split(":")[1])))
        {
            // Player 1 has set a new record
            name = JOptionPane.showInputDialog("You set a new high score. What is your name?");
            highScore = name + ":" + player1.getScore();
        }
        else if (player2.getScore() > Integer.parseInt((getHighScore().split(":")[1])))
        {
            // Player 2 has set a new record
            name = JOptionPane.showInputDialog("You set a new high score. What is your name?");
            highScore = name + ":" + player2.getScore();
        }

        createFile(highScore);
        timer.start();
    }

    /**
     * Helper method to check if highscore.dat file exist.
     * If it doesn't exist, create a new file and insert the new
     * high score into it.
     */
    private static void createFile(String highScore)
    {
        File scoreFile = new File(FILE_PATH);
        // Create file if not exist
        if (!scoreFile.exists())
        {
            try
            {
                scoreFile.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        // Created a file writer which will store the file to write to
        FileWriter writeFile;
        // Allows us to write to file
        BufferedWriter writer = null;
        try
        {
            // To check if the file exists
            writeFile = new FileWriter(scoreFile);
            // Turn the file into a writing file
            writer = new BufferedWriter(writeFile);
            // Let buffered writer write the high score into file
            writer.write(highScore);
        }
        catch (Exception e)
        {
            // Debug error
            System.out.println(e.toString());
            e.printStackTrace();
        }
        finally
        {
            // Close the writer
            try
            {
                if (writer != null) writer.close();
            }
            catch (Exception e)
            {
                // Debug error
                System.out.println(e.toString());
                e.printStackTrace();
            }
        }
    }

    /**
     * Load the high score from file
     *
     * @return high score recorded in file
     */
    public static String getHighScore()
    {
        // format: Teck Seng: 100
        FileReader readFile;
        BufferedReader reader = null;
        try
        {
            readFile = new FileReader(FILE_PATH);
            reader = new BufferedReader(readFile);
            return reader.readLine();
        }
        catch (Exception e)
        {
            return "No HighScore:0";
        }
        finally
        {
            try
            {
                if (reader != null) reader.close();
            }
            catch (IOException e)
            {
                // Debug error
                System.out.println(e.toString());
                e.printStackTrace();
            }
        }
    }
}

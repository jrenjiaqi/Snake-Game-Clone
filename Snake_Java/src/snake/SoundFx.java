package snake;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * SoundFx.java
 *
 * Contains attributes and methods that handles
 * music and sound effects.
 */
public class SoundFx
{
    Clip clip;
    boolean isPlaying;
    boolean bgmPlaying;

    public SoundFx(boolean isPlaying)
    {
        this.isPlaying = isPlaying;
    }

	/**
	 * Helper method to create and setup audio object
	 *
	 * @param soundFileName: Path of file
	 */
	public void setFile(String soundFileName)
    {
        try
        {
            File file = new File(soundFileName);
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        }
        catch (Exception e)
        {
        	// Debug if exception is caught
			System.out.println(e.toString());
			e.printStackTrace();
        }
    }

	/**
	 * Set up the background music
	 */
	public void playBGM()
    {
        if (!isPlaying)
        {
            this.setFile(".//res//bgm.wav");
            clip.setFramePosition(0);
            clip.start();
            isPlaying = true;
            bgmPlaying = true;
        }
    }

	/**
	 * Set up the Food sound file
 	 */
    public void playGOODFOOD()
    {
        this.setFile(".//res//goodFood.wav");
        clip.setFramePosition(0);
        clip.start();
    }

	/**
	 * Set up the Game over sound file
	 */
    public void playGAMEOVER()
    {
        this.setFile(".//res//gameover.wav");
        clip.setFramePosition(0);
        clip.start();
    }

	/**
	 * Set up the Poison sound file
	 */
    public void playBADFOOD()
    {
        this.setFile(".//res//badFood.wav");
        clip.setFramePosition(0);
        clip.start();

    }

	/**
	 * Set up the winning sound file
	 */
    public void playWINNER()
    {
        this.setFile(".//res//winner.wav");
        clip.setFramePosition(0);
        clip.start();

    }

	/**
	 * Set up the draw sound file
	 */
    public void playDraw()
    {
        this.setFile(".//res//draw.wav");
        clip.setFramePosition(0);
        clip.start();
    }

	/**
	 * Stop the background music
	 */
	public void stopBGM()
    {
        isPlaying = false;
        clip.stop();
    }
}
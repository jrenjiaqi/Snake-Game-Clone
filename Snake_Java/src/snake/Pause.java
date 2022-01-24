package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Pause.java
 *
 * Contains attributes and methods for displaying
 * and processing mouse click events for pause menu
 */
public class Pause extends MouseAdapter
{
    GamePanel game;
    boolean isPlaying;

    public Pause(GamePanel game)
    {
        isPlaying = true;
        this.game = game;
    }

    /**
     * Method to display and handle the pause menu
     *
     * @param g: Graphics object
     */
    public void paint(Graphics g)
    {
        if (game.gameState == GamePanel.STATE.Pause)
        {
            // Setup fonts
            Font fnt = new Font("Arial Bold", Font.ITALIC, 50);
            Font fnt2 = new Font("Times New Roman BOLD", Font.ITALIC, 30);

            // Title
            g.setFont(fnt);
            g.setColor(Color.red);
            g.drawString("Paused", 490, 150);

            ImageIcon mode = new ImageIcon("images/rectangle.png");
            ImageIcon mode1 = new ImageIcon(mode.getImage().getScaledInstance(300, 111, 0));

            g.setFont(fnt2);
            g.setColor(Color.black);

            // Change Mode button no function yet
            mode1.paintIcon(game, g, 430, 170);
            g.drawString("RESUME", 520, 260);

            // Controls button
            mode1.paintIcon(game, g, 430, 290);
            g.drawString("CONTROLS", 500, 380);

            // Exit Button
            mode1.paintIcon(game, g, 430, 420);
            g.drawString("EXIT GAME", 500, 510);

            // Options button image
            ImageIcon i3 = new ImageIcon("images/gear.png");
            ImageIcon img3 = new ImageIcon(i3.getImage().getScaledInstance(50, 52, 0));
            img3.paintIcon(game, g, 1060, 580);

        }
        else if (game.gameState == GamePanel.STATE.PauseHelp)
        {
            // Setup fonts
            Font fnt2 = new Font("Arial Bold", Font.BOLD, 30);
            Font fnt3 = new Font("Arial Bold", Font.BOLD, 20);
            g.setFont(fnt2);

            // Draw rectangle
            g.setColor(Color.cyan);
            g.drawRect(210, 500, 200, 64);

            // Display button texts
            g.drawString("Back", 270, 540);
            g.drawString("Instructions:", 290, 150);
            g.drawString("Key Controls:", 290, 230);
            g.drawString("Player 1", 290, 270);
            g.drawString("Player 2", 520, 270);

            // Display instructions
            g.setFont(fnt3);
            g.setColor(Color.white);
            g.drawString("Eat as many donuts and avoid the poisons!", 290, 190);

            // Display wasd image
            ImageIcon wasd = new ImageIcon("images/wasd.png");
            ImageIcon wasdResize = new ImageIcon(wasd.getImage().getScaledInstance(300, 184, 0));
            wasdResize.paintIcon(game, g, -10, 200);

            // Display wasd texts
            g.drawString("W - Move Up", 290, 310);
            g.drawString("A - Move Left", 290, 350);
            g.drawString("S - Move Down", 290, 390);
            g.drawString("D - Move Right", 290, 430);

            // Display arrow images
            ImageIcon arrowKeys = new ImageIcon("images/arrow.png");
            ImageIcon arrowKeysResize = new ImageIcon(arrowKeys.getImage().getScaledInstance(300, 200, 0));
            arrowKeysResize.paintIcon(game, g, 780, 200);

            // Display arrow keys text
            g.drawString("Up Arrow - Move Up", 520, 310);
            g.drawString("Left Arrow - Move Left", 520, 350);
            g.drawString("Down Arrow - Move Down", 520, 390);
            g.drawString("Right Arrow - Move Right", 520, 430);

        }
        else if (game.gameState == GamePanel.STATE.PauseOption)
        {
            // Display option image
            ImageIcon options = new ImageIcon("images/options_name.png");
            ImageIcon options1 = new ImageIcon(options.getImage().getScaledInstance(300, 90, 0));
            options1.paintIcon(game, g, 250, 130);

            // Display sound image
            ImageIcon sound = new ImageIcon("images/sound.png");
            ImageIcon sound1 = new ImageIcon(sound.getImage().getScaledInstance(100, 78, 0));
            sound1.paintIcon(game, g, 300, 240);

            // Display switch button image
            ImageIcon switcher = new ImageIcon("images/switch_button.png");
            ImageIcon switcher1 = new ImageIcon(switcher.getImage().getScaledInstance(200, 91, 0));
            switcher1.paintIcon(game, g, 570, 230);

            // Display back button image
            ImageIcon back = new ImageIcon("images/back_button.png");
            ImageIcon back1 = new ImageIcon(back.getImage().getScaledInstance(200, 95, 0));
            back1.paintIcon(game, g, 230, 500);
        }
    }

    /**
     * Mouse pressed event handler
     *
     * @param e: MouseEvent object
     */
    public void mousePressed(MouseEvent e)
    {
        int mx = e.getX();
        int my = e.getY();
        if (game.gameState == GamePanel.STATE.Pause)
        {
            // Change Mode
            if (mouseOver(mx, my, 500, 190, 200, 64))
            {
                game.repaint();
                game.gameState = GamePanel.STATE.Game;
                game.setRunning(true);
            }

            // Help button
            if (mouseOver(mx, my, 500, 350, 200, 64))
            {
                game.repaint();
                game.gameState = GamePanel.STATE.PauseHelp;
            }

            // Option button
            if (mouseOver(mx, my, 1060, 580, 200, 64))
            {
                game.setStart(true);
                game.repaint();
                game.gameState = GamePanel.STATE.PauseOption;

            }

            // Quit
            if (mouseOver(mx, my, 500, 480, 200, 64))
            {
                System.exit(1);
            }
        }
        // Back button for help
        else if (game.gameState == GamePanel.STATE.PauseHelp)
        {
            if (mouseOver(mx, my, 210, 500, 200, 64))
            {
                game.repaint();
                game.gameState = GamePanel.STATE.Pause;
                return;
            }
        }
        // Back button for option
        else if (game.gameState == GamePanel.STATE.PauseOption)
        {
            if (mouseOver(mx, my, 210, 500, 200, 64))
            {
                game.repaint();
                game.gameState = GamePanel.STATE.Pause;
                return;
            }
        }

        // On and off sound
        if (game.gameState == GamePanel.STATE.PauseOption)
        {
            // Clicked on On button
            if (mouseOver(mx, my, 560, 240, 100, 64))
            {
                System.out.println("Sound is on");
                return;
            }
            // Clicked on Off button
            if (mouseOver(mx, my, 690, 240, 100, 64))
            {
                System.out.println("Sound is off");
                return;
            }
        }
    }

    /**
     * Method to handle mouse over events
     *
     * @param mx:       Mouse x position
     * @param my:       Mouse y position
     * @param x:        Button x position
     * @param y:        Button y position
     * @param width:    Width of button
     * @param height:   Height of button
     * @return true if mouse is clicked on specified positions
     */
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height)
    {
        if (mx > x && mx < x + width)
            return my > y && my < y + height;
        else
            return false;
    }

    public void mouseReleased(MouseEvent e) {/* No implementation is necessary */}
}
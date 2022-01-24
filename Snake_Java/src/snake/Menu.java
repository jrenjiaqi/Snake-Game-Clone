package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Menu.java
 *
 * Contains attributes and methods to handle the different menus.
 */
public class Menu extends MouseAdapter
{

    private GamePanel game;
    private boolean isPlaying;
    private SoundFx sf = new SoundFx(isPlaying);

    public Menu(GamePanel game)
    {
        sf.playBGM();
        isPlaying = true;
        this.game = game;
    }

    /**
     * Display the menu based on where mouse is clicked
     *
     * @param e: MouseEvent object
     */
    public void mousePressed(MouseEvent e)
    {
        int mx = e.getX();
        int my = e.getY();
        if (game.gameState == GamePanel.STATE.Menu)
        {
            // Play button
            if (mouseOver(mx, my, 270, 450, 200, 64))
            {
                game.gameState = GamePanel.STATE.Select;
                game.repaint();
            }
            // Help button
            if (mouseOver(mx, my, 480, 450, 200, 64))
            {
                game.repaint();
                game.gameState = GamePanel.STATE.Help;
            }
            // Option button
            if (mouseOver(mx, my, 1060, 580, 200, 64))
            {
                game.setStart(true);
                game.repaint();
                game.gameState = GamePanel.STATE.Option;
            }
            // Quit
            if (mouseOver(mx, my, 690, 450, 200, 64))
            {
                System.exit(1);
            }
        }
        // Back button for help
        else if (game.gameState == GamePanel.STATE.Help)
        {
            if (mouseOver(mx, my, 210, 500, 200, 64))
            {
                game.repaint();
                game.gameState = GamePanel.STATE.Menu;
                return;
            }
        }
        else if (game.gameState == GamePanel.STATE.Select)
        {
            // Normal button
            if (mouseOver(mx, my, 340, 470, 200, 64))
            {
                game.gameState = GamePanel.STATE.Game;
                game.setDifficulty(0);
                game.start();
            }
            // Hard button
            if (mouseOver(mx, my, 540, 470, 200, 64))
            {
                game.gameState = GamePanel.STATE.Game;
                game.setDifficulty(1);
                game.start();
            }
            // Back button for difficulty
            if (mouseOver(mx, my, 450, 550, 200, 64))
            {
                game.repaint();
                game.gameState = GamePanel.STATE.Menu;
                return;
            }

        }

        // Back button for Options
        if (game.gameState == GamePanel.STATE.Option)
        {
            if (mouseOver(mx, my, 210, 500, 200, 64))
            {
                game.repaint();
                game.gameState = GamePanel.STATE.Menu;
                return;
            }
        }

        // On and off sound
        if (game.gameState == GamePanel.STATE.Option)
        {
            if (mouseOver(mx, my, 560, 240, 100, 64))
            {
                sf.playBGM();
                return;
            }
            if (mouseOver(mx, my, 690, 240, 100, 64))
            {
                sf.stopBGM();
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
        {
            return my > y && my < y + height;
        }
        else
            return false;
    }

    /**
     * Method to display and handle the main menu
     *
     * @param g: Graphics object
     */
    public void paint(Graphics g)
    {
        if (game.gameState == GamePanel.STATE.Menu)
        {
            Font fnt = new Font("Arial Bold", Font.ITALIC, 50);

            // Title
            g.setFont(fnt);
            g.setColor(Color.yellow);
            g.drawString("Multiplayer Snake Game", 280, 40);

            // Snake image
            ImageIcon i = new ImageIcon("images/mamba.png");
            i.paintIcon(game, g, 370, 130);

            // Snake statue
            ImageIcon statue = new ImageIcon("images/king_cobra.png");
            ImageIcon statue1 = new ImageIcon(statue.getImage().getScaledInstance(200, 228, 0));
            statue1.paintIcon(game, g, 800, 130);
            statue1.paintIcon(game, g, 140, 130);


            // Play button image
            ImageIcon i1 = new ImageIcon("images/start_button.png");
            i1.paintIcon(game, g, 230, 450);

            // Help button image
            ImageIcon i2 = new ImageIcon("images/help_button.png");
            i2.paintIcon(game, g, 455, 450);

            // Options button image
            ImageIcon i3 = new ImageIcon("images/gear.png");
            ImageIcon img3 = new ImageIcon(i3.getImage().getScaledInstance(50, 52, 0));
            img3.paintIcon(game, g, 1060, 580);

            // Exit Button image
            ImageIcon i4 = new ImageIcon("images/exit_button.png");
            i4.paintIcon(game, g, 690, 450);

        }
        else if (game.gameState == GamePanel.STATE.Help)
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

            // Display wasd text
            g.drawString("W - Move Up", 290, 310);
            g.drawString("A - Move Left", 290, 350);
            g.drawString("S - Move Down", 290, 390);
            g.drawString("D - Move Right", 290, 430);

            // Display arrow keys image
            ImageIcon arrowKeys = new ImageIcon("images/arrow.png");
            ImageIcon arrowKeysResize = new ImageIcon(arrowKeys.getImage().getScaledInstance(300, 200, 0));
            arrowKeysResize.paintIcon(game, g, 780, 200);

            // Display arrow key texts
            g.drawString("Up Arrow - Move Up", 520, 310);
            g.drawString("Left Arrow - Move Left", 520, 350);
            g.drawString("Down Arrow - Move Down", 520, 390);
            g.drawString("Right Arrow - Move Right", 520, 430);

        }
        else if (game.gameState == GamePanel.STATE.Select)
        {
            // Difficulty Image
            ImageIcon difficulty = new ImageIcon("images/difficulty.png");
            ImageIcon difficulty1 = new ImageIcon(difficulty.getImage().getScaledInstance(500, 158, 0));
            difficulty1.paintIcon(game, g, 320, -30);
            Font fnt = new Font("Times New Roman", 1, 30);
            Font fnt2 = new Font("Times New Roman BOLD", 2, 30);

            // Normal Snake Mode
            ImageIcon snakeNormal = new ImageIcon("images/normal_snake.JPG");
            ImageIcon snakeNormal1 = new ImageIcon(snakeNormal.getImage().getScaledInstance(300, 244, 0));
            snakeNormal1.paintIcon(game, g, 250, 106);
            g.setFont(fnt);
            g.setColor(Color.red);
            g.drawString("- No Poison", 335, 360);
            g.drawString("- Low Speed", 335, 390);

            // Hard Snake Mode
            ImageIcon snakeHard = new ImageIcon("images/hard_snake.JPG");
            ImageIcon snakeHard1 = new ImageIcon(snakeHard.getImage().getScaledInstance(408, 260, 0));
            snakeHard1.paintIcon(game, g, 550, 100);
            g.drawString("- With Poison", 655, 360);
            g.drawString("- High Speed", 655, 390);

            // Normal button image
            ImageIcon normal = new ImageIcon("images/rectangle.png");
            ImageIcon normal1 = new ImageIcon(normal.getImage().getScaledInstance(300, 111, 0));
            normal1.paintIcon(game, g, 250, 400);

            // Normal button
            g.setFont(fnt2);
            g.setColor(Color.black);
            g.drawString("NORMAL", 335, 490);

            // Hard button image
            ImageIcon hard = new ImageIcon("images/rectangle.png");
            ImageIcon hard1 = new ImageIcon(hard.getImage().getScaledInstance(300, 111, 0));
            hard1.paintIcon(game, g, 580, 400);
            g.drawString("HARD", 680, 490);

            // Back button image
            ImageIcon back = new ImageIcon("images/back_button.png");
            ImageIcon back1 = new ImageIcon(back.getImage().getScaledInstance(200, 95, 0));
            back1.paintIcon(game, g, 450, 550);

        }
        else if (game.gameState == GamePanel.STATE.Option)
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

    public void tick() {/* No implementation needed */}
}
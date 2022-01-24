package snake;

import mathengine.DisplayMath;
import mathengine.GameMath;
import mathengine.SnakeMath;
import physicsengine.SnakePhysics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * GamePanel.java
 *
 * Contains all attributes and methods to handle the entire logic of the game.
 * Player 1 is on the left side of the screen.
 * Player 2 is on the right side of the screen.
 */
public class GamePanel extends JPanel implements ActionListener
{
    // Player number
    private final int PLAYER1 = 1;
    private final int PLAYER2 = 2;

    // Controls for player1 and player2
    private boolean snake1Right = true, snake1Left = false, snake1Up = false, snake1Down = false;
    private boolean snake2Right = false, snake2Left = true, snake2Up = false, snake2Down = false;

    // Game controls
    private Timer timer;
    private boolean running = false, start = false, pause = false;

    // Snake, food and poison objects
    private Drawable snake1Food;
    private Drawable snake2Food;
    private Drawable snake1Poison;
    private Drawable snake2Poison;
    private final ArrayList<Body> snake1;
    private final ArrayList<Body> snake2;

    // For random food coordinates
    private final Random rand;

    // X and Y coordinates for each snake
    private int snake1X = SnakeMath.calculateSnake1X(Settings.UNIT_SIZE);
    private int snake1Y = SnakeMath.calculateSnake1Y(Settings.GAME_YPOS, Settings.UNIT_SIZE);
    private int snake2X = SnakeMath.calculateSnake2X(Settings.CENTER, Settings.UNIT_SIZE);
    private int snake2Y = SnakeMath.calculateSnake2Y(Settings.GAME_YPOS, Settings.UNIT_SIZE);

    // Player stats
    Player player1 = new Player();
    Player player2 = new Player();

    // Default snake size
    private final int DEFAULT_SIZE = 6;
    private int snake1Size = DEFAULT_SIZE, snake2Size = DEFAULT_SIZE;

    // Game settings
    private int difficulty = 0;
    private boolean isPlaying = false;
    private boolean isWinning = false;
    private String highScore = "0";

    // Miscellaneous setting variables
    private int ticks = 0;
    private final Menu menu;
    private final Pause pauseScreen;

    public enum STATE
    {
        Menu, Help, Game, Select, Option, Pause, PauseHelp, PauseOption, PauseSelect
    }

    public STATE gameState = STATE.Menu;

    // Image and sound
    Image bgImg;
    SoundFx sf = new SoundFx(isPlaying);

    public GamePanel()
    {
        // Focus window when run
        setFocusable(true);
        requestFocusInWindow();

        // Image source for background
        bgImg = Toolkit.getDefaultToolkit().createImage("images/background.jpg");

        // Listen to mouse click on menu button
        menu = new Menu(this);
        this.addMouseListener(menu);

        // Listen to mouse click on pause menu button
        pauseScreen = new Pause(this);
        this.addMouseListener(pauseScreen);

        // Listen for key press to navigate snakes
        addKeyListener(new Player1Listener());
        addKeyListener(new Player2Listener());

        // Initialise random object
        rand = new Random();

        // Initialise snakes and food list
        snake1 = new ArrayList<Body>();
        snake2 = new ArrayList<Body>();

        // Setup food
        snake1Food = createFood(PLAYER1);
        snake2Food = createFood(PLAYER2);

        // Setup poisons
        // Make sure poison is not the same position as food
        do
        {
            snake1Poison = createPoison(PLAYER1);
        } while (snake1Food.getXPos() == snake1Poison.getXPos() && snake1Food.getYPos() == snake1Poison.getYPos());
        do
        {
            snake2Poison = createPoison(PLAYER2);
        } while (snake2Food.getXPos() == snake2Poison.getXPos() && snake2Food.getYPos() == snake2Poison.getYPos());

        if (gameState == STATE.Game)
        {
            // Setup snake
            snake1.add(new Body(snake1X, snake1Y, Settings.UNIT_SIZE, Color.green));
            snake2.add(new Body(snake2X, snake2Y, Settings.UNIT_SIZE, Color.green));
        }
    }

    /**
     * Method to display game text and visuals
     *
     * @param g: Graphics object
     */
    public void paint(Graphics g)
    {
        // Top Panel
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Settings.WINDOW_WIDTH, Settings.GAME_YPOS);

        // Background
        g.fillRect(0, Settings.GAME_YPOS, Settings.WINDOW_WIDTH, Settings.GAME_HEIGHT);

        if (gameState == STATE.Game)
        {
            // Display pause game text
            g.setColor(Color.red);
            g.drawString("Press 'spacebar' to pause", 10, 60);

            // Draw image at background x pos y pos width height
            g.drawImage(bgImg, 0, 70, 1200, 600, null);

            // Border
            g.setColor(Color.WHITE);
            g.drawRect(0, Settings.GAME_YPOS, Settings.WINDOW_WIDTH - 1, Settings.GAME_HEIGHT);

            // Uncomment to display grids
            // drawGrid(g);

            // Set divider
            g.setColor(Color.WHITE);
            g.drawLine(Settings.CENTER, Settings.GAME_YPOS, Settings.CENTER, Settings.WINDOW_HEIGHT);

            // If highscore.dat got value inside then load the high score
            if (!highScore.equals(""))
            {
                highScore = HighScore.getHighScore();
            }

            // Setup snake and food
            if (!player1.isLose())
            {
                this.drawSnake(g, snake1, PLAYER1);
                snake1Food.draw(g);

                if (difficulty == 1) snake1Poison.draw(g);
            }
            if (!player2.isLose())
            {
                this.drawSnake(g, snake2, PLAYER2);
                snake2Food.draw(g);

                if (difficulty == 1) snake2Poison.draw(g);
            }

            // Both player lost
            if (player1.isLose() && player2.isLose())
            {
                // Display game over screen when both player loses
                gameOver(g);
            }
            // Only player 1 lost
            else if (player1.isLose())
            {
                // Clear left screen
                g.setColor(Color.BLACK);
                g.fillRect(1, 1 + Settings.GAME_YPOS, (Settings.WINDOW_WIDTH / 2) - 1, Settings.GAME_HEIGHT - 3);

                // Display game over text
                g.setColor(Color.red);
                Font font = new Font(Settings.FONT, Font.BOLD, 20);
                FontMetrics metrics = g.getFontMetrics(font);
                String txt = "Player 1 game over";
                int x = DisplayMath.getXCenter(Settings.CENTER, metrics, txt);
                g.setFont(font);
                g.drawString(txt, x, DisplayMath.getYCenter(metrics, Settings.WINDOW_HEIGHT));

                // Play Game over sound
                if (!isPlaying)
                {
                    sf.playGAMEOVER();
                    isPlaying = true;
                }
            }
            // Only player 2 lost
            else if (player2.isLose())
            {
                // Clear right screen
                g.setColor(Color.BLACK);
                g.fillRect(Settings.CENTER + 1, 1 + Settings.GAME_YPOS, (Settings.WINDOW_WIDTH / 2) - 2,
                        Settings.GAME_HEIGHT - 3);

                // Display game over text
                g.setColor(Color.red);
                Font font = new Font(Settings.FONT, Font.BOLD, 20);
                FontMetrics metrics = g.getFontMetrics(font);
                String txt = "Player 2 game over";
                int x = DisplayMath.getXCenter(Settings.CENTER, metrics, txt) + Settings.CENTER;
                g.setFont(font);
                g.drawString(txt, x, DisplayMath.getYCenter(metrics, Settings.WINDOW_HEIGHT));

                // Play Game over sound
                if (!isPlaying)
                {
                    sf.playGAMEOVER();
                    isPlaying = true;
                }
            }

            // Display score text
            g.setColor(Color.WHITE);
            g.setFont(new Font(Settings.FONT, Font.BOLD, 20));
            g.drawString("Score: " + player1.getScore(), 10, 20);
            g.drawString("Score: " + player2.getScore(), Settings.CENTER + 450, 20);

            // If highScore string is not No HighScore:0 then high score text will display
            if (!highScore.equals("No HighScore:0"))
            {
                // Display high score text in the center
                g.drawString("High Score", Settings.CENTER - 50, 20);
                g.drawString(highScore, Settings.CENTER - 50, 20 + 25);
            }

            // Display lives text
            if (difficulty == 1)
            {
                g.drawString("Lives: " + player1.getLives(), 10, 20 + 25);
                g.drawString("Lives: " + player2.getLives(), Settings.CENTER + 450, 20 + 25);
            }

            // Display Player 1 and Player 2 text
            g.setColor(new Color(255, 102, 153));
            Font font = new Font(Settings.FONT, Font.BOLD, 30);
            FontMetrics metrics = g.getFontMetrics(font);
            g.setFont(font);
            String player1Txt = "Player 1", player2Txt = "Player 2";
            int player1TxtX = DisplayMath.getXCenter(Settings.CENTER, metrics, player1Txt);
            int player2TxtX = DisplayMath.getXCenter(Settings.CENTER, metrics, player2Txt) + Settings.CENTER;
            int playerTxtY = DisplayMath.getYCenter(metrics, Settings.GAME_YPOS);
            g.drawString(player1Txt, player1TxtX, playerTxtY);
            g.drawString(player2Txt, player2TxtX, playerTxtY);
        }
        else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.Select || gameState == STATE.Option)
        {
            menu.paint(g);
        }
        else if (gameState == STATE.Pause || gameState == STATE.PauseHelp || gameState == STATE.PauseOption || gameState == STATE.PauseSelect)
        {
            pauseScreen.paint(g);
        }

        g.dispose();
    }

    /**
     * Controls the flow of the game.
     * Method will keep looping once timer starts.
     */
    public void tick()
    {
        if (gameState == STATE.Game)
        {
            // Start game
            running = true;
            start = true;

            // Check lives
            checkLives(PLAYER1);
            checkLives(PLAYER2);

            // Check poison
            try
            {
                if (difficulty == 1)
                {
                    repositionPoisons();
                    checkPoison(snake1Poison, snake1X, snake1Y, PLAYER1);
                    checkPoison(snake2Poison, snake2X, snake2Y, PLAYER2);
                }
                // Check food
                checkFood(snake1Food, snake1X, snake1Y, PLAYER1);
                checkFood(snake2Food, snake2X, snake2Y, PLAYER2);
            } catch (ClassCastException e)
            {
                e.printStackTrace();
                System.exit(0);
            }

            // Ate itself
            checkSnake(snake1, snake1X, snake1Y, PLAYER1);
            checkSnake(snake2, snake2X, snake2Y, PLAYER2);

            // Hit borders
            if (snake1X < 0 || snake1X > Settings.CENTER - Settings.UNIT_SIZE || snake1Y < Settings.GAME_YPOS || snake1Y >= Settings.WINDOW_HEIGHT)
            {
                player1.setLose(true);
            }
            if (snake2X < Settings.CENTER || snake2X > Settings.WINDOW_WIDTH - Settings.UNIT_SIZE || snake2Y < Settings.GAME_YPOS || snake2Y >= Settings.WINDOW_HEIGHT)
            {
                player2.setLose(true);
            }

            // Draw moving snake
            Color rainbow = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            Color rainbow1 = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            snake1.add(new Body(snake1X, snake1Y, Settings.UNIT_SIZE, rainbow));
            snake2.add(new Body(snake2X, snake2Y, Settings.UNIT_SIZE, rainbow1));

            try
            {
                // Remove tail of snake as it moves forward
                if (snake1.size() > snake1Size) snake1.remove(0);
                if (snake2.size() > snake2Size) snake2.remove(0);
            }
            catch (IndexOutOfBoundsException e)
            {
                // Print debugging information
                System.out.println(e.toString());
                System.out.println("snake1.size(): " + snake1.size());
                System.out.println("snake1Size: " + snake1Size);
                System.out.println("snake2.size(): " + snake2.size());
                System.out.println("snake2Size: " + snake2Size);
                // Then exit
                System.exit(0);
            }

            // Move snake 1
            if (snake1Right) snake1X += Settings.UNIT_SIZE;
            if (snake1Left) snake1X -= Settings.UNIT_SIZE;
            if (snake1Up) snake1Y -= Settings.UNIT_SIZE;
            if (snake1Down) snake1Y += Settings.UNIT_SIZE;

            // Move snake 2
            if (snake2Right) snake2X += Settings.UNIT_SIZE;
            if (snake2Left) snake2X -= Settings.UNIT_SIZE;
            if (snake2Up) snake2Y -= Settings.UNIT_SIZE;
            if (snake2Down) snake2Y += Settings.UNIT_SIZE;

            // Increment ticks variable
            ticks++;
        }
        else
        {
            menu.tick();
        }
    }

    /**
     * Loop through snake list and draw the snake
     *
     * @param g:      Graphics object
     * @param snake:  Snake list
     * @param player: Player number
     */
    private void drawSnake(Graphics g, ArrayList<Body> snake, int player)
    {
        for (Body value : snake)
        {
            // Draw head
            if (value == snake.get(snake.size() - 1))
            {
                char direction = 'A';
                if (player == PLAYER1)
                {
                    if (snake1Right) direction = 'R';
                    if (snake1Left) direction = 'L';
                    if (snake1Down) direction = 'D';
                    if (snake1Up) direction = 'U';
                }
                else
                {
                    if (snake2Right) direction = 'R';
                    if (snake2Left) direction = 'L';
                    if (snake2Down) direction = 'D';
                    if (snake2Up) direction = 'U';
                }
                value.drawHead(g, direction);
            }
            // Draw body
            else
            {
                value.drawBody(g);
            }
        }
    }

    /**
     * Check if food has been eaten by snake. Remove food from list if eaten.
     *
     * @param food:   List of food
     * @param x:      X position of snake
     * @param y:      Y position of snake
     * @param player: Player number
     */
    private void checkFood(Drawable food, int x, int y, int player)
    {
        if (SnakePhysics.hitFood(food, x, y))
        {
            // Play sound
            sf.playGOODFOOD();

            // Increase size and score of player 1
            if (player == PLAYER1)
            {
                // Reposition new food for player 1
                // Make sure new food does not appear in same position as poison
                do
                {
                    snake1Food = createFood(PLAYER1);
                } while (snake1Food.getXPos() == snake1Poison.getXPos());

                // Increase snake size and player 1 score
                snake1Size++;
                player1.addScore();
            }
            // Increase size and score of player 2
            else
            {
                // Reposition new poison for player 2
                // Make sure new food does not appear in same position as poison
                do
                {
                    snake2Food = createFood(PLAYER2);
                } while (snake2Food.getXPos() == snake2Poison.getXPos());

                // Increase snake size and player 2 score
                snake2Size++;
                player2.addScore();
            }
        }
    }

    /**
     * Check if poison has been eaten. Reposition new poison if eaten.
     *
     * @param poison: Poison object
     * @param x:      X position of snake
     * @param y:      Y position of snake
     * @param player: Player number
     */
    private void checkPoison(Drawable poison, int x, int y, int player)
    {
        if (SnakePhysics.hitFood(poison, x, y))
        {
            sf.playBADFOOD();
            if (player == PLAYER1)
            {
                // Reposition new poison for player 1
                snake1Poison = createPoison(PLAYER1);

                // Increase snake size greatly and minus score and life
                snake1Size += 10;
                player1.minusScore();
                player1.minusLives();
            }
            else
            {
                // Reposition new poison for player 2
                snake2Poison = createPoison(PLAYER2);

                // Increase snake size greatly and minus score and life
                snake2Size += 10;
                player2.minusScore();
                player2.minusLives();
            }
        }
    }

    /**
     * Reposition poisons every x ticks
     */
    private void repositionPoisons()
    {
        if (ticks >= 30)
        {
            ticks = 0;
            snake1Poison = createPoison(PLAYER1);
            snake2Poison = createPoison(PLAYER2);
        }
    }

    /**
     * Create food object based on player number
     *
     * @param player: Player number
     * @return New food object
     */
    private Food createFood (int player)
    {
        if (player == PLAYER1)
        {
            return new Food(GameMath.getRandomLeftX(rand, Settings.CENTER, Settings.UNIT_SIZE),
                    GameMath.getRandomY(rand, Settings.GAME_YPOS, Settings.GAME_HEIGHT, Settings.UNIT_SIZE));
        }
        else
        {
            return new Food(GameMath.getRandomRightX(rand, Settings.CENTER, Settings.UNIT_SIZE),
                    GameMath.getRandomY(rand, Settings.GAME_YPOS, Settings.GAME_HEIGHT, Settings.UNIT_SIZE));
        }
    }

    /**
     * Create poison object based on player number
     *
     * @param player: Player number
     * @return New poison object
     */
    private Poison createPoison(int player)
    {
        if (player == PLAYER1)
        {
            return new Poison(GameMath.getRandomLeftX(rand, Settings.CENTER, Settings.UNIT_SIZE),
                GameMath.getRandomY(rand, Settings.GAME_YPOS, Settings.GAME_HEIGHT, Settings.UNIT_SIZE));
        }
        else
        {
            return new Poison(GameMath.getRandomRightX(rand, Settings.CENTER, Settings.UNIT_SIZE),
                    GameMath.getRandomY(rand, Settings.GAME_YPOS, Settings.GAME_HEIGHT, Settings.UNIT_SIZE));
        }
    }

    /**
     * Check if snake has eaten itself.
     *
     * @param snake:  Snake list
     * @param x:      X position of snake
     * @param y:      Y position of snake
     * @param player: Player number
     */
    private void checkSnake(ArrayList<Body> snake, int x, int y, int player)
    {
        for (int i = 0; i < snake.size(); i++)
        {
            Body body = snake.get(i);
            if (SnakePhysics.hitBody(body, x, y))
            {
                if (i != snake.size() - 1)
                {
                    if (player == PLAYER1)
                        player1.setLose(true);
                    else
                        player2.setLose(true);
                }
            }
        }
    }

    /**
     * Check if player life has reached zero
     *
     * @param player: Player number
     */
    private void checkLives(int player)
    {
        if (player == PLAYER1)
        {
            if (player1.getLives() == 0) player1.setLose(true);
        }
        else
        {
            if (player2.getLives() == 0) player2.setLose(true);
        }
    }

    /**
     * Display game over screen.
     * Set running to false to stop game.
     *
     * @param g: Graphics object
     */
    private void gameOver(Graphics g)
    {
        // Clear game screen
        g.setColor(Color.BLACK);
        g.fillRect(0, Settings.GAME_YPOS + 1, Settings.WINDOW_WIDTH, Settings.GAME_HEIGHT);

        // Display Game over text
        g.setColor(new Color(255, 102, 102));
        Font font = new Font(Settings.FONT, Font.BOLD, 30);
        FontMetrics metrics = g.getFontMetrics(font);
        g.setFont(font);
        String gameOverTxt = "Game Over", restartTxt = "Press Enter to Restart",
                menuTxt = "Press Escape to return to Main Menu";
        int x = DisplayMath.getXCenter(Settings.WINDOW_WIDTH, metrics, gameOverTxt);
        int y = DisplayMath.getYCenter(metrics, Settings.WINDOW_HEIGHT);
        g.drawString(gameOverTxt, x, y);

        // Display result text
        g.setColor(new Color(102, 255, 153));
        String result;

        // Check winner
        if (player1.getScore() > player2.getScore())
        {
            result = "Player 1 Won!!";

            // Play winning sound
            if (!isWinning)
            {
                sf.playWINNER();
                isWinning = true;
            }
        }
        else if (player2.getScore() > player1.getScore())
        {
            result = "Player 2 Won!!";

            // Play winning sound
            if (!isWinning)
            {
                sf.playWINNER();
                isWinning = true;
            }
        }
        else
        {
            result = "Draw";
        }

        // Display results
        x = DisplayMath.getXCenter(Settings.WINDOW_WIDTH, metrics, result);
        g.drawString(result, x, y + 50);

        // Play winning sound
        if (!isWinning)
        {
            sf.playDraw();
            isWinning = true;
        }

        // Stop game
        running = false;

        // Check if high score is beaten
        HighScore.checkScore(timer, highScore, player1, player2);

        // Display Restart game text
        g.setColor(new Color(255, 77, 77));
        Font font1 = new Font(Settings.FONT, Font.BOLD, 20);
        FontMetrics metrics1 = g.getFontMetrics(font1);
        g.setFont(font1);
        x = DisplayMath.getXCenter(Settings.WINDOW_WIDTH, metrics1, restartTxt);
        g.drawString(restartTxt, x, y + 100);

        // Display Return to Menu text
        g.setColor(new Color(255, 44, 88));
        Font font2 = new Font(Settings.FONT, Font.BOLD, 20);
        FontMetrics metrics2 = g.getFontMetrics(font2);
        g.setFont(font2);
        x = DisplayMath.getXCenter(Settings.WINDOW_WIDTH, metrics1, menuTxt);
        g.drawString(menuTxt, x, y + 150);
    }

    /**
     * Reset everything back to initial state
     */
    public void reset()
    {
        // Re-initialize player
        player1 = new Player();
        player2 = new Player();

        // Clear previous snake
        snake1.clear();
        snake2.clear();

        // Reset food position
        snake1Food = new Food(GameMath.getRandomLeftX(rand, Settings.CENTER, Settings.UNIT_SIZE),
                GameMath.getRandomY(rand, Settings.GAME_YPOS, Settings.GAME_HEIGHT, Settings.UNIT_SIZE));
        snake2Food = new Food(GameMath.getRandomRightX(rand, Settings.CENTER, Settings.UNIT_SIZE),
                GameMath.getRandomY(rand, Settings.GAME_YPOS, Settings.GAME_HEIGHT, Settings.UNIT_SIZE));

        // Reset poison position
        snake1Poison = new Poison(GameMath.getRandomLeftX(rand, Settings.CENTER, Settings.UNIT_SIZE),
                GameMath.getRandomY(rand, Settings.GAME_YPOS, Settings.GAME_HEIGHT, Settings.UNIT_SIZE));
        snake2Poison = new Poison(GameMath.getRandomRightX(rand, Settings.CENTER, Settings.UNIT_SIZE),
                GameMath.getRandomY(rand, Settings.GAME_YPOS, Settings.GAME_HEIGHT, Settings.UNIT_SIZE));

        // Set default positions
        snake1X = SnakeMath.calculateSnake1X(Settings.UNIT_SIZE);
        snake1Y = SnakeMath.calculateSnake1Y(Settings.GAME_YPOS, Settings.UNIT_SIZE);
        snake2X = SnakeMath.calculateSnake2X(Settings.CENTER, Settings.UNIT_SIZE);
        snake2Y = SnakeMath.calculateSnake2Y(Settings.GAME_YPOS, Settings.UNIT_SIZE);

        // Set default snake size
        snake1Size = DEFAULT_SIZE;
        snake2Size = DEFAULT_SIZE;

        // Set default directions
        snake1Right = true;
        snake1Left = false;
        snake1Up = false;
        snake1Down = false;
        snake2Right = false;
        snake2Left = true;
        snake2Up = false;
        snake2Down = false;

        // Reset sound
        isPlaying = false;
        isWinning = false;
    }

    /**
     * Start timer and set running to true
     */
    public void start()
    {
        if (!running)
        {
            // Easy mode
            if (difficulty == 0) timer = new Timer(Settings.DELAY, this);
            // Hard mode
            if (difficulty == 1) timer = new Timer(Settings.FastDELAY, this);
            // Start game
            running = true;
            timer.start();
        }
    }

    /**
     * Helper method to draw grid lines for better visualisation
     *
     * @param g: Graphics object
     */
    private void drawGrid(Graphics g)
    {
        g.setColor(Color.white);
        // Draw horizontal lines
        for (int i = 0; i < Settings.WINDOW_WIDTH / Settings.UNIT_SIZE; i++)
        {
            g.drawLine(i * Settings.UNIT_SIZE, Settings.GAME_YPOS, i * Settings.UNIT_SIZE, Settings.WINDOW_HEIGHT);
        }
        // Draw vertical lines
        for (int i = 0; i < Settings.WINDOW_HEIGHT / Settings.UNIT_SIZE; i++)
        {
            g.drawLine(0, i * Settings.UNIT_SIZE + Settings.GAME_YPOS, Settings.WINDOW_WIDTH,
                    i * Settings.UNIT_SIZE + Settings.GAME_YPOS);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        timer.start();
        if (running)
        {
            tick();
        }
        repaint();
    }

    /**
     * @param running: running boolean to be set
     */
    public void setRunning(boolean running)
    {
        this.running = running;
    }

    /**
     * @param start: start boolean to be set
     */
    public void setStart(boolean start)
    {
        this.start = start;
    }

    /**
     * @param difficulty: difficulty level to be set
     */
    public void setDifficulty(int difficulty)
    {
        this.difficulty = difficulty;
    }

    /**
     * Listen for key press for player 1
     * WASD, Space, Enter and Esc keys.
     */
    private class Player1Listener implements KeyListener
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            int key = e.getKeyCode();
            // Space bar pressed
            if (key == KeyEvent.VK_SPACE
                    && gameState != STATE.Menu
                    && gameState != STATE.Select
                    && gameState != STATE.PauseSelect
                    && gameState != STATE.PauseOption)
            {
                // Pause game
                if (running && !pause)
                {
                    gameState = STATE.Pause;
                    running = false;

                }
                // Unpause game
                else
                {
                    timer.start();
                    pause = false;
                }
            }
            // Enter key pressed
            if (key == KeyEvent.VK_ENTER)
            {
                // Restart game
                if (!running && !pause)
                {
                    reset();
                    running = true;
                    repaint();
                }
            }
            // Escape key pressed
            if (key == KeyEvent.VK_ESCAPE)
            {
                if (!running && !pause)
                {
                    gameState = STATE.Menu;
                    timer.stop();
                    reset();
                    repaint();
                }
            }
            // D key pressed
            if (key == KeyEvent.VK_D && !snake1Left)
            {
                snake1Up = false;
                snake1Down = false;
                snake1Left = false;
                snake1Right = true;
            }
            // A key pressed
            if (key == KeyEvent.VK_A && !snake1Right)
            {
                snake1Up = false;
                snake1Down = false;
                snake1Right = false;
                snake1Left = true;
            }
            // W key pressed
            if (key == KeyEvent.VK_W && !snake1Down)
            {
                snake1Left = false;
                snake1Right = false;
                snake1Down = false;
                snake1Up = true;
            }
            // S key pressed
            if (key == KeyEvent.VK_S && !snake1Up)
            {
                snake1Left = false;
                snake1Right = false;
                snake1Up = false;
                snake1Down = true;
            }
        }

        @Override
        public void keyTyped(KeyEvent e)
        {/* No implementation needed */}

        @Override
        public void keyReleased(KeyEvent e)
        {/* No implementation needed */}
    }

    /**
     * Listen for key press for player 2.
     * Up down left right keys.
     */
    private class Player2Listener implements KeyListener
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            int key = e.getKeyCode();
            // Right key pressed
            if (key == KeyEvent.VK_RIGHT && !snake2Left)
            {
                snake2Up = false;
                snake2Down = false;
                snake2Left = false;
                snake2Right = true;
            }
            // Left key pressed
            if (key == KeyEvent.VK_LEFT && !snake2Right)
            {
                snake2Up = false;
                snake2Down = false;
                snake2Right = false;
                snake2Left = true;
            }
            // Up key pressed
            if (key == KeyEvent.VK_UP && !snake2Down)
            {
                snake2Left = false;
                snake2Right = false;
                snake2Down = false;
                snake2Up = true;
            }
            // Down key pressed
            if (key == KeyEvent.VK_DOWN && !snake2Up)
            {
                snake2Left = false;
                snake2Right = false;
                snake2Up = false;
                snake2Down = true;
            }
        }

        @Override
        public void keyTyped(KeyEvent e)
        {/* No implementation needed */}

        @Override
        public void keyReleased(KeyEvent e)
        {/* No implementation needed */}
    }
}
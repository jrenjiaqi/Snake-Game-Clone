package snake;

import mathengine.SettingsMath;

/**
 * Settings.java
 *
 * Contains the settings to configure
 */
public class Settings
{
    protected static final int DELAY = 70;  // Lower number means faster
    protected static final int FastDELAY = 45;  // Lower number means faster
    protected static final String FONT = "helvetica";  // Default font
    protected static final int UNIT_SIZE = 24;  // Grid size
    protected static final int WINDOW_WIDTH = SettingsMath.calculateWindowWidth(UNIT_SIZE);  // Width of window
    protected static final int WINDOW_HEIGHT = SettingsMath.calculateWindowHeight(UNIT_SIZE);  // Height of window
    protected static final int GAME_HEIGHT = SettingsMath.calculateGameHeight(WINDOW_HEIGHT, UNIT_SIZE);  // Height of game screen
    protected static final int CENTER = SettingsMath.calculateCenterPosition(WINDOW_WIDTH);  // Center width position
    protected static final int GAME_YPOS = SettingsMath.calculateGameYPosition(WINDOW_HEIGHT, GAME_HEIGHT);  // Game position screen y position
}

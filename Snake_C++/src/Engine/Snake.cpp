#include "../../include/Snake.hpp"
#include "../../include/Settings.hpp"
#include "../../include/Player.hpp"
#include "../../include/SnakeMath.hpp"

#include <iostream>
#include <exception>
//#include <windows.h> // for error dialogue box.

using namespace Engine;
using namespace Math;
using namespace std;

Snake::Snake() = default;

Snake::Snake(std::shared_ptr<Context> &context, int player) :context(context), player(player)
{
}

/**
 * Initialises the snake based on player number to determine position
 */
void Snake::init()
{
    int x, y;
    switch (player)
    {
        case PLAYER1:
            x = SnakeMath::calculateSnake1X(Settings::UNIT_SIZE);
            y = SnakeMath::calculateSnake1Y(Settings::GAME_YPOS, Settings::UNIT_SIZE);
            break;
        case PLAYER2:
            x = SnakeMath::calculateSnake2X(Settings::CENTER, Settings::UNIT_SIZE);
            y = SnakeMath::calculateSnake2Y(Settings::GAME_YPOS, Settings::UNIT_SIZE);
            break;
        default:
            x = 0, y = 0;
            break;
    }
    xPos = x;
    yPos = y;
}

/**
 * Adds new coordinates of the body part and remove the tail to move the snake
 */
void Snake::move()
{
    try
    {
        body.emplace_back(context, xPos, yPos);
        if (body.size() > size) body.erase(body.begin());
    } catch (const std::out_of_range& oor)
    {
        std::cerr << "Out of Range error at Snake::move(): " << oor.what() << '\n';
//        MessageBox(NULL, "Out of Range error: Snake::move()",
//                   "Exception has occurred!", MB_ICONERROR); // windows only.
        std::terminate(); // aborts program.
    }
}

/**
 * Draws the snake head and body
 * @param dir: Direction the snake is facing
 */
void Snake::draw(char dir)
{
    for (auto part = body.begin(); part != body.end(); ++part)
    {
        if (part == body.end()-1)
            part->drawHead(dir);
        else
            part->drawBody();
    }
}

/**
 * Checks if snake hits the borders
 * @return true if hit
 */
bool Snake::hitBorder() const
{
    switch (player)
    {
        case PLAYER1:
            return xPos < 0 || xPos > Settings::CENTER - Settings::UNIT_SIZE
            || yPos < Settings::GAME_YPOS || yPos >= Settings::WINDOW_HEIGHT;
        case PLAYER2:
            return xPos < Settings::CENTER || xPos > Settings::WINDOW_WIDTH - Settings::UNIT_SIZE
            || yPos < Settings::GAME_YPOS || yPos >= Settings::WINDOW_HEIGHT;
        default:
            return false;
    }
}

/**
 * Check if snake hit its own body part
 * @return true if hit
 */
bool Snake::hitItself()
{
    for (auto part = body.begin(); part != body.end(); ++part)
    {
        if (xPos == part->getXPos() && yPos == part->getYPos())
        {
            if (part != body.end()-1) return true;
        }
    }
    return false;
}

/**
 * Checks if snake hits either food or poison
 * @param food: Drawable objects
 * @return true if hit
 */
bool Snake::hitFood(Drawable &food) const
{
    return xPos == food.getXPos() && yPos == food.getYPos();
}

/**
 * Increase size of snake based on given value
 * @param increment: How much to increase snake size
 */
void Snake::increaseSize(int increment)
{
    size += increment;
}

/**
 * @param x: X position to set
 */
void Snake::setXPos(int x)
{
    xPos = x;
}

/**
 * @return x position of snake
 */
int Snake::getXPos() const
{
    return xPos;
}

/**
 * @param y: Y position to set
 */
void Snake::setYPos(int y)
{
    yPos = y;
}

/**
 * @return y position of snake
 */
int Snake::getYPos() const
{
    return yPos;
}

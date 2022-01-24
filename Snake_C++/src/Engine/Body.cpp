#include <iostream>
#include "../../include/Body.hpp"
#include "../../include/Settings.hpp"

using namespace Engine;

Body::Body() = default;

Body::Body(std::shared_ptr<Context> &context, int xPos, int yPos)
    :context(context), xPos(xPos), yPos(yPos), color(sf::Color(rand() % 256, rand() % 256, rand() % 256))
{
}

/**
 * Method to draw the body of the snake
 */
void Body::drawBody()
{
    body.setRadius(Settings::UNIT_SIZE/2.0f);
    body.setPosition((float)xPos, (float)yPos);
    body.setFillColor(color);
    body.setOutlineThickness(2);
    body.setOutlineColor(sf::Color::White);
    context->window->draw(body);
}

/**
 * Method to initialise head image depending on the direction it is facing
 * and draw it to the given position
 */
void Body::drawHead(char direction)
{
    int id;
    switch (direction)
    {
        case 'U':
            context->assets->addTexture(UP_MOUTH, "../assets/images/upmouth.png");
            id = UP_MOUTH;
            break;
        case 'D':
            context->assets->addTexture(DOWN_MOUTH, "../assets/images/downmouth.png");
            id = DOWN_MOUTH;
            break;
        case 'L':
            context->assets->addTexture(LEFT_MOUTH, "../assets/images/leftmouth.png");
            id = LEFT_MOUTH;
            break;
        case 'R':
            context->assets->addTexture(RIGHT_MOUTH, "../assets/images/rightmouth.png");
            id = RIGHT_MOUTH;
            break;
        default:
            id = UP_MOUTH;
            break;
    }

    head.setTexture(context->assets->getTexture(id));
    head.setPosition((float)xPos, (float)yPos);
    context->window->draw(head);
}

/**
 * @return x position of a body part
 */
int Body::getXPos() const
{
    return xPos;
}

/**
 * @param x: x position to set
 */
void Body::setXPos(int x)
{
    this->xPos = x;
}

/**
 * @return y position of a body part
 */
int Body::getYPos() const
{
    return yPos;
}

/**
 * @param y: y position to set
 */
void Body::setYPos(int y)
{
    this->yPos = y;
}

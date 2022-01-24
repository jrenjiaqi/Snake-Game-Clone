#include "../../include/Poison.hpp"

using namespace Engine;

Poison::Poison() = default;

Poison::Poison(std::shared_ptr<Context> &context, int xPos, int yPos)
      : context(context), xPos(xPos), yPos(yPos)
{
}

/**
 * Initialise poison image and draws it to the current position
 */
void Poison::draw()
{
    context->assets->addTexture(POISON, "../assets/images/poison_skull.png");
    poison.setTexture(context->assets->getTexture(POISON));
    poison.setPosition((float)xPos, (float)yPos);
    context->window->draw(poison);
}

/**
 * @return x position of a poison
 */
int Poison::getXPos()
{
    return xPos;
}

/**
 * @param x:	x position to set
 */
void Poison::setXPos(int x)
{
    this->xPos = x;
}

/**
 * @return y position of a poison
 */
int Poison::getYPos()
{
    return yPos;
}

/**
 * @param y: y position to set
 */
void Poison::setYPos(int y)
{
    this->yPos = y;
}

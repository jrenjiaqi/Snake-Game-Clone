#ifndef BLACKMAMBA_BODY_HPP
#define BLACKMAMBA_BODY_HPP
#pragma once
#include <memory>
#include <SFML/Graphics/Sprite.hpp>
#include <SFML/Graphics/CircleShape.hpp>
#include "Game.hpp"

namespace Engine
{
    class Body
    {
    private:
        int xPos{};
        int yPos{};
        sf::Sprite head;
        sf::CircleShape body;
        sf::Color color;
        std::shared_ptr<Context> context;
    public:
        Body();
        Body(std::shared_ptr<Context> &, int, int);

        void drawBody();
        void drawHead(char);
        int getXPos() const;
        void setXPos(int);
        int getYPos() const;
        void setYPos(int);
    };
}


#endif //BLACKMAMBA_BODY_HPP

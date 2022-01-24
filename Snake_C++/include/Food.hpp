#ifndef BLACKMAMBA_FOOD_HPP
#define BLACKMAMBA_FOOD_HPP
#pragma once
#include <SFML/Graphics/Sprite.hpp>
#include "Game.hpp"
#include "Drawable.hpp"

namespace Engine
{
    class Food : public Drawable
    {
    private:
        int xPos{};
        int yPos{};
        sf::Sprite food;
        std::shared_ptr<Context> context;
    public:
        Food();
        Food(std::shared_ptr<Context> &, int, int);

        void draw() override;
        int getXPos() override;
        void setXPos(int) override;
        int getYPos() override;
        void setYPos(int) override;
    };
}


#endif //BLACKMAMBA_FOOD_HPP

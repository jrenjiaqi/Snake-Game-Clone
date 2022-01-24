#ifndef BLACKMAMBA_POISON_HPP
#define BLACKMAMBA_POISON_HPP
#pragma once
#include <memory>
#include <SFML/Graphics/Sprite.hpp>
#include "Drawable.hpp"
#include "Game.hpp"

namespace Engine
{
    class Poison : public Drawable
    {
    private:
        int xPos{};
        int yPos{};
        sf::Sprite poison;
        std::shared_ptr<Context> context;
    public:
        Poison();
        Poison(std::shared_ptr<Context> &, int, int);
        void draw() override;
        int getXPos() override;
        void setXPos(int) override;
        int getYPos() override;
        void setYPos(int) override;
    };
}

#endif //BLACKMAMBA_POISON_HPP

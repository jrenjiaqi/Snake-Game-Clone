#ifndef BLACKMAMBA_SNAKE_HPP
#define BLACKMAMBA_SNAKE_HPP
#pragma once
#include <vector>
#include "Body.hpp"
#include "Food.hpp"

namespace Engine
{
    class Snake
    {
    private:

        std::shared_ptr<Context> context;
        std::vector<Body> body;

        int xPos{};
        int yPos{};
        int player{};
        int size = 5;
    public:
        Snake();
        Snake(std::shared_ptr<Context> &, int);

        void init();
        void move();
        void increaseSize(int = 1);
        void draw(char);

        bool hitBorder() const;
        bool hitItself();
        bool hitFood(Drawable &) const;

        void setXPos(int);
        int getXPos() const;
        void setYPos(int);
        int getYPos() const;
    };
}


#endif //BLACKMAMBA_SNAKE_HPP

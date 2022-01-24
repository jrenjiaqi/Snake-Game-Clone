#ifndef BLACKMAMBA_PLAYER_HPP
#define BLACKMAMBA_PLAYER_HPP
#pragma once
#include "Food.hpp"
#include "Snake.hpp"
#include "Poison.hpp"

namespace Engine
{
    enum PlayerID
    {
        PLAYER1,
        PLAYER2
    };

    class Player
    {
    private:
        std::shared_ptr<Context> context;

        int lives;
        int score;
        int difficulty;
        int player;
        bool lose;

        bool up;
        bool down;
        bool left;
        bool right;

        Food food;
        Snake snake;
        std::vector<Poison> poisons;

        static const int SCORE_INCREMENT = 10;
        static const int SCORE_DECREMENT = 5;
    public:
        Player() = default;
        Player(std::shared_ptr<Context> &, int, int);
        void init();
        void draw();
        void moveSnake();
        void changeDirection(bool, bool, bool, bool);

        Food createFood();
        Poison createPoison();

        void checkHit();
        void checkEat();
        void repositionPoison();

        bool getUp() const;
        bool getDown() const;
        bool getLeft() const;
        bool getRight() const;

        void setLose(bool);
        bool isLose() const;

        int getScore() const;
        int getLives() const;
    };
}


#endif //BLACKMAMBA_PLAYER_HPP

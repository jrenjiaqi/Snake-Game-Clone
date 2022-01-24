#ifndef BLACKMAMBA_GAMEMATH_HPP
#define BLACKMAMBA_GAMEMATH_HPP
#pragma once
namespace Math
{
    class GameMath
    {
    private:
        static int calculateXBound(int, int);
        static int calculateYBound(int, int);
    public:
        static int getRandomLeftX(int, int);
        static int getRandomRightX(int, int);
        static int getRandomY(int, int, int);
    };
}

#endif //BLACKMAMBA_GAMEMATH_HPP

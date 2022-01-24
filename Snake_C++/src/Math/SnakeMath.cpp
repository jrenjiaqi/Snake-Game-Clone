#include <exception>
#include <iostream>
#include "../../include/SnakeMath.hpp"

using namespace Math;


struct MultiplyByZero : public std::exception {
    const char * what () const throw () {
        return "MultiplyByZero Exception";
    }
};


/**
 * Calculate the starting x position of snake 1
 *
 * @param unitSize: Size of unit
 * @return Snake 1 starting x position
 */
int SnakeMath::calculateSnake1X(int unitSize)
{
    try {
        if (unitSize == 0) {
            throw MultiplyByZero();
        }
    } catch(MultiplyByZero& e) {
        std::cerr << "MultiplyByZero exception caught at SnakeMath::calculateSnake1X()! unitSize == 0" << std::endl;
        std::cerr << e.what() << std::endl;
//        MessageBox(NULL, "MultiplyByZero error!",
//                   "Exception has occurred!", MB_ICONERROR); // windows only.
        std::terminate(); // aborts program.
    }
    return 5 * unitSize;
}

/**
 * Calculate the starting y position of snake 1
 *
 * @param gameYPos: Y position of game screen
 * @param unitSize: Size of unit
 * @return Snake 1 starting y position
 */
int SnakeMath::calculateSnake1Y(int gameYPos, int unitSize)
{
    try {
        if (unitSize == 0) {
            throw MultiplyByZero();
        }
    } catch(MultiplyByZero& e) {
        std::cerr << "MultiplyByZero exception caught at SnakeMath::calculateSnake1Y()! unitSize == 0" << std::endl;
        std::cerr << e.what() << std::endl;
//        MessageBox(NULL, "MultiplyByZero error!",
//                   "Exception has occurred!", MB_ICONERROR); // windows only.
        std::terminate(); // aborts program.
    }
    return gameYPos + 10 * unitSize;
}

/**
 * Calculate the starting x position of snake 2
 *
 * @param center:   Y position of center screen
 * @param unitSize: Size of unit
 * @return Snake 2 starting x position
 */
int SnakeMath::calculateSnake2X(int center, int unitSize)
{
    try {
        if (unitSize == 0) {
            throw MultiplyByZero();
        }
    } catch(MultiplyByZero& e) {
        std::cerr << "MultiplyByZero exception caught at SnakeMath::calculateSnake2X()! unitSize == 0" << std::endl;
        std::cerr << e.what() << std::endl;
//        MessageBox(NULL, "MultiplyByZero error!",
//                   "Exception has occurred!", MB_ICONERROR); // windows only.
        std::terminate(); // aborts program.
    }
    return center + 18 * unitSize;
}

/**
 * Calculate the starting y position of snake 2
 *
 * @param gameYPos: Y position of game screen
 * @param unitSize: Size of unit
 * @return Snake 2 starting y position
 */
int SnakeMath::calculateSnake2Y(int gameYPos, int unitSize)
{
    try {
        if (unitSize == 0) {
            throw MultiplyByZero();
        }
    } catch(MultiplyByZero& e) {
        std::cerr << "MultiplyByZero exception caught at SnakeMath::calculateSnake2Y()! unitSize == 0" << std::endl;
        std::cerr << e.what() << std::endl;
//        MessageBox(NULL, "MultiplyByZero error!",
//                   "Exception has occurred!", MB_ICONERROR); // windows only.
        std::terminate(); // aborts program.
    }
    return gameYPos + 10 * unitSize;
}

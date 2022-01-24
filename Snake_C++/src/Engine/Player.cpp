#include "../../include/Player.hpp"
#include "../../include/GameMath.hpp"
#include "../../include/Settings.hpp"
#include "../../include/Sound.hpp"

using namespace Engine;
using namespace Math;
Sound sfxP;

Player::Player(std::shared_ptr <Context> &context, int player, int difficulty)
        :context(context), player(player), lives(3), score(0), lose(false), difficulty(difficulty) {
    food = createFood();
    if (difficulty == HARD) {
        // Create 10 poisons
        for (int i = 0; i < Settings::POISON_COUNT; i++) {
            poisons.push_back(createPoison());
        }
    }
    switch (player) {

        case PLAYER1:
            snake = Snake(context, PLAYER1);
            right = true, left = false, up = false, down = false;
            break;
        case PLAYER2:
            snake = Snake(context, PLAYER2);
            right = false, left = true, up = false, down = false;
            break;
        default:
            break;
    }
}

/**
 * Initialises snake
 */
void Player::init() {
    snake.init();
}

/**
 * Draws snake and edibles for player
 */
void Player::draw() {
    char dir = 'A';
    if (up) dir = 'U';
    else if (down) dir = 'D';
    else if (left) dir = 'L';
    else if (right) dir = 'R';

    snake.draw(dir);
    food.draw();
    if (difficulty == HARD) {
        for (Poison poison : poisons) poison.draw();
    }
}

/**
 * Move snake based on direction it is facing
 */
void Player::moveSnake() {
    int x = snake.getXPos();
    int y = snake.getYPos();

    if (right) x += Settings::UNIT_SIZE;
    if (left) x -= Settings::UNIT_SIZE;
    if (up) y -= Settings::UNIT_SIZE;
    if (down) y += Settings::UNIT_SIZE;

    snake.setXPos(x);
    snake.setYPos(y);
    snake.move();
}

/**
 * Change direction of snake
 * @param up: true to indicate up
 * @param down: true to indicate down
 * @param left: true to indicate left
 * @param right: true to indicate right
 */
void Player::changeDirection(bool up, bool down, bool left, bool right) {
    this->up = up;
    this->down = down;
    this->left = left;
    this->right = right;
}

/**
 * Check if snake hits border/itself or when life count becomes zero
 */

void Player::checkHit() {
    if (snake.hitBorder() || snake.hitItself() || lives == 0) {
        lose = true;
        sfxP.playGameOver();

    }
}

/**
 * Increase snake size and score when snake eats food.
 * Increase snake size by 10 and minus lives and score when ate poison.
 */
void Player::checkEat() {
    if (snake.hitFood(food)) {
        score += SCORE_INCREMENT;
        snake.increaseSize();
        food = createFood();

        sfxP.playGoodFood();
    }
    if (difficulty == HARD) {
        for (Poison &poison : poisons) {
            if (snake.hitFood(poison)) {
                score -= SCORE_DECREMENT;
                lives--;
                snake.increaseSize(10);
                poison = createPoison();
                sfxP.playBadFood();
                break;
            }
        }

    }
}

/**
 * Regenerate position for poison
 */

void Player::repositionPoison() {
    poisons[rand() % Settings::POISON_COUNT] = createPoison();
}

/**
 * @return new food object
 */
Food Player::createFood() {
    switch (player) {
        case PLAYER1:
            return Food(context, GameMath::getRandomLeftX(Settings::CENTER, Settings::UNIT_SIZE),
                        GameMath::getRandomY(Settings::GAME_YPOS, Settings::GAME_HEIGHT, Settings::UNIT_SIZE));
        case PLAYER2:
            return Food(context, GameMath::getRandomRightX(Settings::CENTER, Settings::UNIT_SIZE),
                        GameMath::getRandomY(Settings::GAME_YPOS, Settings::GAME_HEIGHT, Settings::UNIT_SIZE));
        default:
            return Food();
    }
}

/**
 * @return new poison object
 */
Poison Player::createPoison() {
    Poison newPoison;
    switch (player) {
        case PLAYER1:
            do {
                newPoison = Poison(context, GameMath::getRandomLeftX(Settings::CENTER, Settings::UNIT_SIZE),
                                   GameMath::getRandomY(Settings::GAME_YPOS, Settings::GAME_HEIGHT,
                                                        Settings::UNIT_SIZE));
            } while (food.getXPos() == newPoison.getXPos());
            break;
        case PLAYER2:
            do {
                newPoison = Poison(context, GameMath::getRandomRightX(Settings::CENTER, Settings::UNIT_SIZE),
                                   GameMath::getRandomY(Settings::GAME_YPOS, Settings::GAME_HEIGHT,
                                                        Settings::UNIT_SIZE));
            } while (food.getXPos() == newPoison.getXPos());
            break;
    }
    return newPoison;
}

/**
 * @return up boolean
 */
bool Player::getUp() const {
    return up;
}

/**
 * @return down boolean
 */
bool Player::getDown() const {
    return down;
}

/**
 * @return left boolean
 */
bool Player::getLeft() const {
    return left;
}

/**
 * @return right boolean
 */
bool Player::getRight() const {
    return right;
}

/**
 * @param lose: boolean to set
 */
void Player::setLose(bool lose) {
    this->lose = lose;
}

/**
 * @return lose boolean
 */
bool Player::isLose() const {
    return lose;
}

/**
 * @return score count
 */
int Player::getScore() const {
    return score;
}

/**
 * @return lives count
 */
int Player::getLives() const {
    return lives;
}
#include "../../include/Game.hpp"
#include "../../include/GamePanel.hpp"
#include "../../include/Settings.hpp"
#include "../../include/Menu.hpp"
#include "../../include/Sound.hpp"

#include <SFML/Window/Event.hpp>
#include <SFML/Graphics/RenderWindow.hpp>
#include <SFML/Graphics/CircleShape.hpp>
#include <iostream>
#include <string>
using namespace Engine;
Sound bgmSound;

// Initialise pointer to null so that it can be initialised
// in first call to getInstance
Game *Game::instance = nullptr;

// Private constructor for Game class.
Game::Game() : context(std::make_shared<Context>())
{
    // Setup window and show first screen to display
    context->window->create(sf::VideoMode(Settings::WINDOW_WIDTH, Settings::WINDOW_HEIGHT), "Black Mamba", sf::Style::Close);
    context->states->addState(std::make_unique<Menu>(context));
}

/**
 * Start timer and run game loop
 */
void Game::run()
{
    sf::Clock clock;
    sf::Time timeSinceLastFrame = sf::Time::Zero;

    while (context->window->isOpen())
    {
        timeSinceLastFrame += clock.restart();
        while (timeSinceLastFrame > TIME_PER_FRAME)
        {
            timeSinceLastFrame -= TIME_PER_FRAME;
            context->states->processStateChange();
            context->states->GetCurrent()->processInput();
            context->states->GetCurrent()->update(TIME_PER_FRAME);
            context->states->GetCurrent()->draw();
        }
    }
}

int main()
{
    Game *singleton_game = Engine::Game::getInstance();
    singleton_game->run();


    return 0;
}
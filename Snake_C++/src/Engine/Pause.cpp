//
// Created by danial on 24/3/2021.
//
#include <SFML/Graphics/CircleShape.hpp>
#include <SFML/Window/Event.hpp>
#include <windows.h>
#include "../../include/Game.hpp"
#include "../../include/Pause.hpp"
#include "../../include/Menu.hpp"
#include "../../include/GamePanel.hpp"
#include <iostream>

using namespace Engine;

Pause::Pause(std::shared_ptr<Context> &context, int difficulty) : context(context), normalPressed(false), difficulty(difficulty), exitPressed(false) {

}

Pause::~Pause() {

}

void Pause::init() {
    context->assets->addFont(MAIN_FONT, "../assets/fonts/Helvetica.ttf");
    context->assets->addTexture(PAUSE_SCREEN, "../assets/images/pause_screen.jpg", true);


    // snake image
    pause.setTexture(context->assets->getTexture(PAUSE_SCREEN));
    pause.setScale(0.4,0.4);
    pause.setPosition(450, 100);

    context->assets->addTexture(PAUSE_RESET_BUTTON, "../assets/images/reset_button.png", true);
    //Normal button
    rectangle.setTexture(context->assets->getTexture(PAUSE_RESET_BUTTON));
    rectangle.setScale(0.1,0.1);
    rectangle.setPosition(200,400);

    context->assets->addTexture(PAUSE_EXIT, "../assets/images/pause_exit.png", true);
    exitButton.setTexture(context->assets->getTexture(PAUSE_EXIT));
    exitButton.setScale(0.73,0.73);
    exitButton.setPosition(650,400);

}

void Pause::processInput() {
    // Keyboard menu
    sf::Event event;
    while (context->window->pollEvent(event)) {
        switch (event.type) {
            case sf::Event::Closed:
                context->window->close();
                break;
            case sf::Event::KeyPressed:
                if (event.key.code == sf::Keyboard::Space) context->states->popCurrent();
                break;
            case sf::Event::MouseMoved:
                std::cout << "X: " << event.mouseMove.x << "Y: " << event.mouseMove.y << std::endl;
                break;
            case sf::Event::MouseButtonPressed:
                if (event.mouseButton.button == sf::Mouse::Left) {
                    // reset
                    if (event.mouseButton.x >= 300 and event.mouseButton.x <= 450) {
                        if (event.mouseButton.y >= 400 and event.mouseButton.y <= 500)
                            normalPressed = true;
                    }
                    // exit
                    if (event.mouseButton.x >= 700 and event.mouseButton.x <= 800) {
                        if (event.mouseButton.y >= 400 and event.mouseButton.y <= 500)
                            exitPressed = true;
                    }
                }
                break;
        }
    }
}


void Pause::update(sf::Time) {
    if (normalPressed) {
        context->states->addState(std::make_unique<GamePanel>(context));
    }
    if (normalPressed && difficulty == HARD) {
        context->states->addState(std::make_unique<GamePanel>(context), true);
    }
    if (exitPressed){
        context->states->addState(std::make_unique<Menu>(context), true);
    }
}

void Pause::draw() {
    context->window->draw(pause);
    context->window->draw(gameTitle);
    context->window->draw(rectangle);
    context->window->draw(exitButton);
    context->window->display();
}


